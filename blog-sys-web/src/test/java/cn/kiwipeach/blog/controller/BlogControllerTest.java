package cn.kiwipeach.blog.controller;

import cn.kiwipeach.blog.BlogWebApplicationTests;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

public class BlogControllerTest extends BlogWebApplicationTests {





    @Test
    public void queryBlogDetail() throws Exception {
        MvcResult mvcResult= mockMvc.perform(MockMvcRequestBuilders.get("/blog/query")
                //.param("name","lvgang")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                // .andExpect(MockMvcResultMatchers.status().isOk())             //等同于Assert.assertEquals(200,status);
                // .andExpect(MockMvcResultMatchers.content().string("hello lvgang"))    //等同于 Assert.assertEquals("hello lvgang",content);
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //int status=mvcResult.getResponse().getStatus();                 //得到返回代码
        //String content=mvcResult.getResponse().getContentAsString();    //得到返回结果
        //System.out.println("status==>"+status);
        //System.out.println("content==>"+content);
        //Assert.assertEquals(200,status);                        //断言，判断返回代码是否正确
        //Assert.assertEquals("hello lvgang",content);            //断言，判断返回的值是否正确
    }
}