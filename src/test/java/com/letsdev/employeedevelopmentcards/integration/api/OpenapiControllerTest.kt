package com.letsdev.employeedevelopmentcards.integration.api

import com.letsdev.employeedevelopmentcards.integration.AbstractIntegrationTest
import org.junit.Test

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class OpenapiControllerTest : AbstractIntegrationTest() {

    @Test
    fun testOpenapiUiRedirect() {
        mockMvc.perform(get("/")).andExpect(status().is3xxRedirection)
    }

    @Test
    fun testOpenapiYaml() {
        mockMvc.perform(get("/openapi.yaml")).andExpect(status().isOk)
        mockMvc.perform(get("/openapi.yml")).andExpect(status().isOk)
    }

    @Test
    fun testOpenapiJson() {
        mockMvc.perform(get("/openapi.json")).andExpect(status().isOk)
    }

}
