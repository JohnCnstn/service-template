package com.letsdev.employeedevelopmentcards.unit;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.letsdev.employeedevelopmentcards.CardsApplication;
import com.letsdev.employeedevelopmentcards.api.OpenapiController;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest(classes = CardsApplication.class)
public abstract class AbstractUnitTest {

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.objectMapper = new ObjectMapper();
//        this.mockMvc = MockMvcBuilders.standaloneSetup(openapiController).build();
    }

}

