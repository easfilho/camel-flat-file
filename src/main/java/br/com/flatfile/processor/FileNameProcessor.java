package br.com.flatfile.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileNameProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        GenericFile<File> genericFile = (GenericFile<File>) exchange.getIn().getBody();
        String[] arrayFileName = genericFile.getFileName().split("\\.");
        String newFileName = String.format("%s.done.dat", arrayFileName[0]);
        exchange.getIn().setHeader(Exchange.FILE_NAME, newFileName);
    }
}
