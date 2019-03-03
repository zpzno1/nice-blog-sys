package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.BlogApiApplicationTests;
import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import com.alibaba.fastjson.JSON;
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
    public void selectByPage() {
        Page<BlogInfoVO> page = new Page<>(1, 3);
        //List<BlogInfoVO> blogInfoVOS = blogMapper.selectByPage(page, null, null);
        //List<BlogInfoVO> blogInfoVOS = blogMapper.selectByPage(page, "1013", null);
        List<BlogInfoVO> blogInfoVOS = blogMapper.selectByPage(page, null, "Oracle");
        log.info("total:{}", page.getTotal());
    }

    @Test
    public void selectNextPrevious() {
        Blog nextBlog = blogMapper.selectNextBlog("106");
        Blog previousBlog = blogMapper.selectPreviousBlog("106");
        System.out.println(JSON.toJSONString(nextBlog));
        System.out.println(JSON.toJSONString(previousBlog));
    }
}