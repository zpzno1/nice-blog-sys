package cn.kiwipeach.blog.quartz;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.util.List;

/**
 * 大屏展示统计类
 */
@Repository
public class StatisticAnalysisDao {

    /**
     * 保存或者更新实体类
     * areaStatisticPercent
     *
     * @return
     */
    public boolean saveOrUpdate(String areaCode, String todayCount, String historyCount) {
        Connection connection = DBTools.getConnection();
        DBTools.beginTransaction(connection);
        //更新
        String updateSql = "update tmp_statistic_analysis set today_count=?,history_count=?, update_time=sysdate where area_code = ?";
        int updateRow = DBTools.update(connection, updateSql, todayCount, historyCount, areaCode);
        if (updateRow > 0) {
            DBTools.commit(connection);
            return true;
        } else {
            DBTools.rollback(connection);
            return false;
        }
    }

    /**
     * 查询所有的地市
     *
     * @return 返回所有的地市
     */
    public List<AreaStatisticPercent> selectAll() {
        String sql = "select * from tmp_statistic_analysis order by area_code asc";
        List<AreaStatisticPercent> areaStatisticPercents = DBTools.queryList(AreaStatisticPercent.class, sql);
        return areaStatisticPercents;
    }

    /**
     * 获取22个地区配置参数
     *
     * @return
     */
    public List<AreaStatisticConfig> selectAreaConfig() {
        String sql = "select * from TMP_STATISTIC_CONFIG";
        List<AreaStatisticConfig> areaStatisticPercents = DBTools.queryList(AreaStatisticConfig.class, sql);
        return areaStatisticPercents;
    }

    /**
     * 获取数据库小时
     *
     * @return
     */
    public String selectSysHour() {
        String sql = "select to_char(sysdate,'hh24') from dual";
        return String.valueOf(DBTools.getValue(sql));
    }

    /**
     * 获取数据库分钟
     *
     * @return
     */
    public String selectSysMin() {
        String sql = "select to_char(sysdate,'mi') from dual";
        return String.valueOf(DBTools.getValue(sql));
    }
}
