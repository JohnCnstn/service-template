package com.johncnstn.servicetemplate.unit;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public abstract class AbstractUnitTest {

    @MockBean protected HttpClient httpClient;
    @MockBean protected HttpResponse<Object> httpResponse;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
}
