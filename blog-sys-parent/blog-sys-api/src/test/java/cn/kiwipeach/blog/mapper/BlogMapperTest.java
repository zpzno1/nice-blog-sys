package cn.kiwipeach.blog.mapper;

import cn.kiwipeach.blog.BlogApiApplicationTests;
import cn.kiwipeach.blog.domain.Blog;
import cn.kiwipeach.blog.domain.vo.ArchiveBlogTimelineVO;
import cn.kiwipeach.blog.domain.vo.BlogInfoVO;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

    @Test
    public void selectArchiveBlog() {
        Page<ArchiveBlogTimelineVO> page = new Page<>(1, 3);
        List<ArchiveBlogTimelineVO> yyyy = blogMapper.selectArchiveBlog(page, "yyyy");
        log.info("total:{}", page.getTotal());
    }

    @Test
    @Rollback(value = false)
    public void updateBlogTitle() {
        //简单更新
        Blog blog = new Blog();
        blog.setId("100");
        blog.setTitle("50岁的程序员该何去何从");
        Integer updateRow = blogMapper.updateById(blog);

        //测试更新comment_count=comment_count+1
        //blogMapper.selectCount()
        log.warn("结果：{}", updateRow);
    }

    /**
     * clob:大文本
     * blob：大的二进制文件
     */
    @Test
    @Rollback(value = false)
    public void 测试Clob大文本插入方法() throws IOException {
        Blog blog = new Blog();
        blog.setTitle("MySQL解压版安装教程（保存在数据库中）");
        blog.setUserId("kiwipeach");
        blog.setIntroduction("一般的上传流程是用户获得上传凭证之后直接将资源上传到七牛空间，然后七牛回返回一个上传成功或者失败的信息，用户业务服务器是不清楚这些信息的，可以参考下面的流程图：\n");
        String markDown = getMarkDown();
        blog.setContent(markDown);
        blog.setIconUrl("http://7xkn2v.dl1.z0.glb.clouddn.com/QQ20151019-4@2x.png");
        Integer insert = blogMapper.insert(blog);
        System.out.println(insert);
    }

    private String getMarkDown() throws IOException {
        FileReader fileReader = new FileReader("D:\\资料文档库\\10.我的文档\\Typora笔记文档\\数据库\\MySQL数据库\\解压版安装教程.md");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        StringBuffer contentBuffer = new StringBuffer();
        while ((line = bufferedReader.readLine()) != null) {
            contentBuffer.append(line).append(System.getProperty("line.separator"));
        }
        return contentBuffer.toString();
    }


}