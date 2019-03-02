package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.BlogApiApplicationTests;
import cn.kiwipeach.blog.domain.vo.CategoryTreeVO;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class BlogCategoryMapperTest extends BlogApiApplicationTests {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Test
    public void selectCategoryTree() {
        List<CategoryTreeVO> blogCategories = blogCategoryMapper.selectCategoryTree();
        log.info("博客分类树:{}", JSON.toJSONString(blogCategories));
    }

}