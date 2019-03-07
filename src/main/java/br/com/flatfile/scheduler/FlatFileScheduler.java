package br.com.flatfile.scheduler;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class FlatFileScheduler {

    private final ProducerTemplate producerTemplate;
    private final Logger logger;

    @Autowired
    public FlatFileScheduler(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @Scheduled(fixedDelay = 5000)
    public void processFlatFiles() {
        logger.info("[Flat-File-Summary] Starting processing of flat files");
        Exchange exchange = new DefaultExchange(producerTemplate.getCamelContext());
        producerTemplate.send("direct:convertFlatFile", exchange);
        logger.info("[Flat-File-Summary] Flat files processed");
    }
}
