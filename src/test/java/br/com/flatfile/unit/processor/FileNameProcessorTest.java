package br.com.flatfile.unit.processor;

import br.com.flatfile.processor.*;
import br.com.flatfile.route.ConverterFlatFileRoute;
import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

public class FileNameProcessorTest extends CamelTestSupport {

    private FileNameProcessor fileNameProcessor;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        fileNameProcessor = new FileNameProcessor();
        return new ConverterFlatFileRoute(fileNameProcessor,
                Mockito.mock(FlatFileContentProcessor.class),
                Mockito.mock(FlatFileDataProcessor.class),
                Mockito.mock(SummaryDataProcessor.class),
                Mockito.mock(SummaryFileContentProcessor.class));
    }

    @Test
    public void shouldRenameFile() {
        GenericFile<File> genericFile = new GenericFile<>();
        genericFile.setFileName("input1.dat");
        Exchange exchange = new DefaultExchange(super.context());
        exchange.getIn().setBody(genericFile);
        fileNameProcessor.process(exchange);
        Assert.assertEquals("input1.done.dat", exchange.getIn().getHeader(Exchange.FILE_NAME));
    }
}
