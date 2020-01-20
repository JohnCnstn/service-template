package com.letsdev.employeedevelopmentcards.unit;


import com.letsdev.employeedevelopmentcards.CardsApplication;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.Silent.class)
@SpringBootTest(classes = CardsApplication.class)
public abstract class AbstractUnitTest {

}
