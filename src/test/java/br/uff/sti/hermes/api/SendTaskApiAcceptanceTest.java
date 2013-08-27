/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.api;

import com.googlecode.flyway.test.annotation.FlywayTest;
import com.googlecode.flyway.test.junit.FlywayTestExecutionListener;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.*;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 * @author dancastellani
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    FlywayTestExecutionListener.class})
@FlywayTest
public class SendTaskApiAcceptanceTest {

    private static final String API_GET_SEND_TASK_BASE = "api/sendtasks/";
    private static final String API_GET_SEND_TASK_INFO = API_GET_SEND_TASK_BASE;
    @Autowired
    private SendTaskApi sendTaskApi;

    @Test
    public void whenApplicationStartupSpringAutowiresSendTaskServiceOnSendTaskApi() {
        assertNotNull(sendTaskApi.getSendTaskService());
    }

    @Test
    public void whenCallApiToGetSendTaskListShouldReturnStatusCode200() {
        expect().statusCode(200).when().get(API_GET_SEND_TASK_BASE);
    }

//    @Test
//    public void whenCallApiToGetSendTaskOneInfoShouldReturnStatusCode200() {
//        expect().statusCode(200).when().get(API_GET_SEND_TASK_INFO + "1");
//    }
}
