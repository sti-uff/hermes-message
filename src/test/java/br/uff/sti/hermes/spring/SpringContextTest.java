package br.uff.sti.hermes.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 * @author dancastellani
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class SpringContextTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void whenTestsRunSpringsApplicationContextIsInitialized() {
        assertNotNull(applicationContext);
    }
}
