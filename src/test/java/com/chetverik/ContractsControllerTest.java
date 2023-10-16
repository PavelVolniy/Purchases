package com.chetverik;

import com.chetverik.controllers.ContractsController;
import com.chetverik.service.ContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("q")
public class ContractsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ContractsController controller;
    @Autowired
    private ContractService service;

    @Test
    public void findAllContractsList() throws Exception {
        mockMvc.perform(get("/contracts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
