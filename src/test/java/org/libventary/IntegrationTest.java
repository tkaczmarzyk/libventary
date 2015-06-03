package org.libventary;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventstore.EventStore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public abstract class IntegrationTest {

    @Autowired
    protected CommandGateway cmdGateway;
    
    @Autowired
    protected EventStore eventStore;
}
