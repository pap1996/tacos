package com.tacocloud.tacos;



import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {
    

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testHomePage() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("home"))
        .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Welcome to ...")));
    }
}
