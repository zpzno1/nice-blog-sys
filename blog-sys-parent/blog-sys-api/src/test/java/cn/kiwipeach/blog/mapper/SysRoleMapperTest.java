package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.BlogApiApplicationTests;
import cn.kiwipeach.blog.domain.vo.UserRoleVO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class SysRoleMapperTest extends BlogApiApplicationTests {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Test
    public void selectUserRole() {
        UserRoleVO userRoleVO = sysRoleMapper.selectUserRole("1");
        log.warn("数据:{}", JSON.toJSONString(userRoleVO));
    }

}