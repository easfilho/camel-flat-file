package br.com.flatfile.integration.route;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.io.File;

import static org.junit.Assert.assertTrue;

@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@MockEndpoints
@DirtiesContext
public class ConverterFlatFileRouteTest {

    @EndpointInject(uri = "mock:file:data/output")
    protected MockEndpoint mockEndpoint;

    @Autowired
    protected ProducerTemplate producerTemplate;

    @Test
    public void shouldConvertFile() throws Exception {
        mockEndpoint.expectedBodiesReceived(getExpectedBody().trim());
        mockEndpoint.expectedMessageCount(1);
        Exchange exchange = new DefaultExchange(producerTemplate.getCamelContext());
        producerTemplate.send("direct:convertFlatFile", exchange);
        Thread.sleep(5000);
        mockEndpoint.assertIsSatisfied();
        File file = new File("data/output");
        assertTrue(file.isDirectory());
    }

    private String getExpectedBody() {
        return "Amount of clients: 2\r\n" +
                "Amount of salesman: 2\r\n" +
                "Most expensive sale id: 10\r\n" +
                "Worst salesman ever: Renato\r\n";
    }
}
