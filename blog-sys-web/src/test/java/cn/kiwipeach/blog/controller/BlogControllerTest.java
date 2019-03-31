package cn.kiwipeach.blog.controller;

import cn.kiwipeach.blog.BlogWebApplicationTests;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class BlogControllerTest extends BlogWebApplicationTests {


    @Test
    public void queryBlogDetail1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/blog/query")
                .param("categoryId", "1013")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void queryBlogDetail2() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/blog/query")
                .param("categoryId", "1013")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();                 //得到返回代码
        String content = mvcResult.getResponse().getContentAsString();    //得到返回结果
        System.out.println("status==>" + status);
        System.out.println("content==>" + content);
    }
}