package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.BlogApiApplicationTests;
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class BlogMapperTest extends BlogApiApplicationTests {

    @Autowired
    private BlogMapper blogMapper;

    @Test
    public void selectByPage(){
        Page<BlogInfoVO> page = new Page<>(2, 3);
        List<BlogInfoVO> blogInfoVOS = blogMapper.selectByPage(page);
        log.info("total:{}", page.getTotal());
    }
}