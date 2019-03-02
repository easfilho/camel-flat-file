package br.com.flatfile.route;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConverterFlatFileRouteTest {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Test
    public void shouldConvertFile() throws InterruptedException {
        Exchange exchange = new DefaultExchange(producerTemplate.getCamelContext());
        exchange = producerTemplate.send("direct:convertFlatFile",exchange);
        Thread.sleep(5000);
        System.out.println(exchange);
        File file = new File("data/output");
        assertTrue(file.isDirectory());
    }
}
