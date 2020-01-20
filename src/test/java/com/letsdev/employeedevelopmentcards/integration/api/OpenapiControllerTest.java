package com.letsdev.employeedevelopmentcards.integration.api;

import com.letsdev.employeedevelopmentcards.integration.AbstractIntegrationTest;
import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OpenapiControllerTest extends AbstractIntegrationTest {

    @Test
    public void testOpenapiUiRedirect() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().is3xxRedirection());
    }

    @Test
    public void testOpenapiYaml() throws Exception {
        mockMvc.perform(get("/openapi.yaml")).andExpect(status().isOk());
        mockMvc.perform(get("/openapi.yml")).andExpect(status().isOk());
    }

    @Test
    public void testOpenapiJson() throws Exception {
        mockMvc.perform(get("/openapi.json")).andExpect(status().isOk());
    }

}
