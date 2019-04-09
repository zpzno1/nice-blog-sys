package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.BlogApiApplicationTests;
import cn.kiwipeach.blog.domain.BlogCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class BlogCategoryMapperTest  extends BlogApiApplicationTests {

    @Autowired
    private BlogCategoryMapper blogCategoryMapper;

    @Test
    public void 递归查询所有的博客分类信息() {
        List<BlogCategory> blogCategories= blogCategoryMapper.selectList(null);
        for (BlogCategory blogCategory : blogCategories) {

        }
    }

}