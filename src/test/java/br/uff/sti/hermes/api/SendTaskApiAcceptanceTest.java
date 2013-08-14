/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.sti.hermes.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author dancastellani
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class SendTaskApiAcceptanceTest {

    @Autowired
    private SendTaskApi sendTaskApi;

    @Test
    public void whenApplicationStartupSpringAutowiresSendTaskServiceOnSendTaskApi() {
        assert sendTaskApi.getSendTaskService() != null;
    }
}
