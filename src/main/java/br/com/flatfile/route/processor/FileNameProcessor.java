package br.com.flatfile.route.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Optional;

@Component
public class FileNameProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        GenericFile<File> genericFile = (GenericFile<File>) exchange.getIn().getBody();
        Optional.ofNullable(genericFile).ifPresent(file -> {
            String[] arrayFileName = file.getFileName().split("\\.");
            String newFileName = String.format("%s.done.dat", arrayFileName[0]);
            exchange.getIn().setHeader(Exchange.FILE_NAME, newFileName);
        });
    }
}
