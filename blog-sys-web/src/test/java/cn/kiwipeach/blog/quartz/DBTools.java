package cn.kiwipeach.blog.quartz;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * Create Date: 2017/12/02
 * Description: JDBC工具类:可支持当前主流数据MySQL、Oracle
 *
 * @author kiwipeach
 */
public class DBTools {

    /**
     * 数据源
     */
    private static DataSource dataSource;

    /**
     * 事务启停标志
     */
    private static boolean tranOpen = false;


    /**
     * sl4f日志工具
     */
    private static Logger logger = LoggerFactory.getLogger(DBTools.class);


    public static DataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(DataSource dataSource) {
        DBTools.dataSource = dataSource;
    }


    /**
     * 从数据源中获取数据库连接
     *
     * @return 返回数据库连接对象
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            if (dataSource==null){
                throw new NullPointerException("空指针异常: dataSource can't be null please invoke 'DBTools.initDataSource' method first!");
            }
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("从数据源中获取数据库连接对象失败:", e);
        }
        return connection;
    }

    /**
     * 在此方法中，可执行添加、删除、更新操作
     *
     * @param connection 数据库连接（事务属性）
     * @param sql        sql语句
     * @param args       sql参数
     * @return 返回影响结果集
     */
    public static int update(Connection connection, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                //设置sql预参数
                preparedStatement.setObject(i + 1, args[i]);
            }
            int rowCounts = preparedStatement.executeUpdate();
            return rowCounts;
        } catch (SQLException e) {
            logger.error("数据库持久化失败:", e);
            return -1;
        } finally {
            if (!tranOpen) {
                //如果没有开启事务，那么更新执行完毕后，需立即释放资源
                close(null, preparedStatement, connection);
            }
        }
    }

    /**
     * 此方法与插入，并且该方法能够返回对象的主键值
     *
     * @param connection 数据库连接对象
     * @param sql        SQL语句
     * @param args       SQL预参数
     * @param <T>        插入结果
     * @return 返回插入的主键值
     */
    public static <T> Object insert(Connection connection, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    //设置sql预参数
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            int rowCounts = preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                Object key = rs.getObject(1);
                return key;
            }
        } catch (SQLException e) {
            logger.error("数据库持久化失败:", e);
        } finally {
            if (!tranOpen) {
                //如果没有开启事务，那么更新执行完毕后，需立即释放资源
                close(null, preparedStatement, connection);
            }
        }
        return -1;
    }



    /**
     * 获取字段属性值或者统计值
     *
     * @param sql  SQL执行语句
     * @param args SQL执行参数
     * @return
     */
    public static Object getValue(String sql, Object... args) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                //设置sql预参数
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getObject(1);
            }
        } catch (SQLException e) {
            logger.error("getValue方法异常:", e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
        return -1;
    }

    /**
     * 根据主键查询一个对象
     *
     * @param sql SQL执行语句
     * @param key 主键
     * @param <T> 泛型变量
     * @return 返回对象
     */
    public static <T> T queryByPrimaryKey(Class<T> cla, String sql, Object key) {
        List<T> queryList = queryList(cla, sql, key);
        if (queryList == null || queryList.size() == 0) {
            return null;
        } else {
            return queryList(cla, sql, key).get(0);
        }
    }


    /**
     * 支持所有的SQL查询语句，例如：条件查询、连接查询、模糊查询等
     *
     * @param cla  查询结果对象
     * @param sql  SQL语句
     * @param args SQL预参数
     * @param <T>  泛型
     * @return 返回T类型的多条数据
     */
    public static <T> List<T> queryList(Class<T> cla, String sql, Object... args) {
        //FIXME 使用jdk自带的PropertyDescriptor来去实现，并且入参最好实体类型
        Connection connection = getConnection();
        List<T> resultList = dealQueryList(connection, cla, sql, args);
        close(null, null, connection);
        return resultList;
    }

    /**
     * 支持所有的SQL查询语句，通知该查询支持在事务中进行，需要传入Connection对象
     *
     * @param cla  查询结果对象
     * @param sql  SQL语句
     * @param args SQL预参数
     * @param <T>  泛型
     * @return 返回T类型的多条数据
     */
    public static <T> List<T> queryList(Connection connection, Class<T> cla, String sql, Object... args) {
        //FIXME 使用jdk自带的PropertyDescriptor来去实现，并且入参最好实体类型
        return dealQueryList(connection, cla, sql, args);
    }

    /**
     * 支持所有的SQL查询语句，通知该查询支持在事务中进行，需要传入Connection对象
     *
     * @param connection 数据库连接对象
     * @param cla        查询结果对象
     * @param sql        SQL语句
     * @param args       SQL预参数
     * @param <T>        泛型
     * @return 返回T类型的多条数据
     */
    private static <T> List<T> dealQueryList(Connection connection, Class<T> cla, String sql, Object[] args) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //0.设置参数
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            //1.把结果集变成List<Map<String,Object>>
            List<Map<String, Object>> listMap = getListMap(preparedStatement);
            //2.把List<Map<String,Object>>变成List<Bean>
            return tranferToListBean(cla, listMap);
        } catch (SQLException e) {
            logger.error("dbtools dealQueryList method occur problem.", e);
        }
        return null;
    }


    /**
     * 开启事务
     *
     * @param connection 数据库连接对象
     */
    public static void beginTransaction(Connection connection) {
        try {
            if (connection != null) {
                logger.info("--->JDBC事务开启:{}", connection);
                connection.setAutoCommit(false);
                tranOpen = true;
            }
        } catch (SQLException e) {
            logger.error("开启事务失败:", e);
        }
    }

    /**
     * 提交事务
     *
     * @param connection 数据库连接对象
     */
    public static void commit(Connection connection) {
        try {
            if (connection != null) {
                logger.info("--->JDBC事务提交:{}", connection);
                connection.commit();
                tranOpen = false;
            }
        } catch (SQLException e) {
            logger.error("事务提交失败:", e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 事务回滚操作
     *
     * @param connection 数据库连接对象
     */
    public static void rollback(Connection connection) {
        try {
            if (connection != null) {
                logger.info("LBR--->JDBC事务回滚:{}", connection);
                connection.rollback();
            }
        } catch (SQLException e) {
            logger.error("事务回滚失败:", e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 把List<Map<String, Object>> 装换成 List<T>类型
     *
     * @param listMap 需要转换的ListMap对象那个
     * @param <T>     返回转换结果List<T>
     * @return 返回List的Bean集合对象
     */
    private static <T> List<T> tranferToListBean(Class<T> cla, List<Map<String, Object>> listMap) {
        List<T> resultList = new ArrayList<T>();
        try {
            for (Map<String, Object> beanMap : listMap) {
                Set<Map.Entry<String, Object>> entries = beanMap.entrySet();
                T tempBean = cla.newInstance();
                Field[] declaredFields = cla.getDeclaredFields();
                //设置JavaBean的属性值
                for (Map.Entry<String, Object> bean : entries) {
                    String attrDBName = bean.getKey();
                    String attrBeanName = returnContainAttr(declaredFields,attrDBName);
                    if (attrBeanName!=null){
                        Object attrValue = bean.getValue();
                        //TODO  使用jdk自带的PropertyDescriptor实现
                        BeanUtils.setProperty(tempBean, attrBeanName, attrValue);
                    }
                }
                resultList.add(tempBean);
            }
        } catch (Exception e) {
            logger.error("tranferToListBean方法调用异常:", e);
        }
        return resultList;
    }


    /**
     * 获取 List<Map<String,Object>> 查询数据
     *
     * @param preparedStatement SQL执行对象
     * @return 返回List<Map<String,Object>>对象
     */
    private static List<Map<String, Object>> getListMap(PreparedStatement preparedStatement) {

        List<Map<String, Object>> resultListMap = new ArrayList<Map<String, Object>>();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> recordMap = new HashMap<String, Object>(resultListMap.size());
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    String columnName = metaData.getColumnLabel(i + 1);
                    Object columnValue = resultSet.getObject(columnName);
                    recordMap.put(columnName, columnValue);
                }
                resultListMap.add(recordMap);
            }
        } catch (SQLException e) {
            logger.error("getListMap方法异常", e);
        }
        return resultListMap;
    }

    /**
     * 释放数据库相关资源
     *
     * @param resultSet  结果集对象
     * @param statement  SQL执行对象
     * @param connection 数据库连接对象
     */
    private static void close(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("resultSet释放异常:", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("statement释放异常:", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("connection释放异常:", e);
            }
        }
    }


    /**
     * 查询别名与JavaBean的映射返回判断
     *
     * @param fields javabean属性数组
     * @param dbAttr 目标数据库属性
     * @return
     */
    private static String returnContainAttr(Field[] fields, String dbAttr) {
        for (Field field : fields) {
            String fieldName = field.getName();
            if (fieldName.equalsIgnoreCase(dbAttr.replace("_", ""))) {
                return fieldName;
            }
        }
        return null;
    }
}
