package org.yes.cart;

import org.hornetq.jms.server.impl.JMSServerManagerImpl;
import org.junit.After;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.yes.cart.dao.impl.AbstractTestDAO;

/**
 * User: Igor Azarny iazarny@yahoo.com
 * Date: 09-May-2011
 * Time: 14:12:54
 */
public abstract class BaseCoreDBTestCase extends AbstractTestDAO {

    private static ApplicationContext sharedContext;

    protected synchronized ApplicationContext createContext() {
        if (sharedContext == null) {
            sharedContext = new ClassPathXmlApplicationContext(
                    "testApplicationContext.xml",
                    "core-aspects.xml");
        }
        return sharedContext;
    }

    @After
    public void tearDown() throws Exception {
        try {
            JMSServerManagerImpl jmsServerManager = (JMSServerManagerImpl) ctx.getBean("jmsServerManagerImpl");
            if (jmsServerManager != null) {
                jmsServerManager.stop();
            }
        } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
            //nothing
        }
    }
}