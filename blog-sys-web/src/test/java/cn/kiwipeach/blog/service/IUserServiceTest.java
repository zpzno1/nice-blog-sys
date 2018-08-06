package cn.kiwipeach.blog.service;

import cn.kiwipeach.blog.BlogWebApplicationTests;
//import cn.kiwipeach.blog.domain.SysUser;
import cn.kiwipeach.blog.domain.SysUser;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;

public class IUserServiceTest extends BlogWebApplicationTests {

    @Autowired
    private ISysUserService iSysUserService;
    /**
     * 测试1：简单的service方法调用
     *
     * @throws SQLException
     */
    @Test
    public void contextLoads() throws SQLException {
        SysUser user = iSysUserService.selectById("1099501218");
        System.out.println(user);
    }

    /**
     * 测试2:springboot+mybatis-plus中的事务测试[通过√]
     */
    @Test
    public void testTransactionMethod() {
        //iSysUserService.testTransactionMethod();
    }
}