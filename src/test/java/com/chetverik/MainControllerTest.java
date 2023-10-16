package com.chetverik;

import com.chetverik.controllers.MainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("q")
public class MainControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private MainController controller;

    @Test
    public void mainPageTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String responseBody = result.getResponse().getContentAsString();
        assertTrue(responseBody.contains("q"));
    }

    @Test
    public void createdExitButton() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/main"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        assertTrue(content.contains("Выход"));
    }

}
