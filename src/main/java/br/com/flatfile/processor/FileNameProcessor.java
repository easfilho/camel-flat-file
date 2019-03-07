package br.com.flatfile.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class FileNameProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        String fileName = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);
        String[] arrayFileName = fileName.split("\\.");
        String newFileName = String.format("%s.done.dat", arrayFileName[0]);
        exchange.getIn().setHeader(Exchange.FILE_NAME, newFileName);
    }
}
