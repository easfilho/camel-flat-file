package br.com.flatfile.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.file.GenericFile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@Component
public class FlatFileContentProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws IOException {
        GenericFile<File> genericFile = (GenericFile<File>) exchange.getIn().getBody();
        List<String> lines = Files.readAllLines(genericFile.getFile().toPath(), StandardCharsets.UTF_8);
        exchange.getIn().setBody(lines);
    }
}
