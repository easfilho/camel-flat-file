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
import java.io.IOException;
import java.util.List;

public class FlatFileContentProcessorTest extends CamelTestSupport {

    private FlatFileContentProcessor flatFileContentProcessor;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        flatFileContentProcessor = new FlatFileContentProcessor();
        return new ConverterFlatFileRoute(Mockito.mock(FileNameProcessor.class),
                flatFileContentProcessor,
                Mockito.mock(FlatFileDataProcessor.class),
                Mockito.mock(SummaryDataProcessor.class),
                Mockito.mock(SummaryFileContentProcessor.class));
    }

    @Test
    public void shouldRenameFile() throws IOException {
        GenericFile<File> genericFile = new GenericFile<>();
        genericFile.setFile(new File("data/input/lines.dat"));
        Exchange exchange = new DefaultExchange(super.context());
        exchange.getIn().setBody(genericFile);
        flatFileContentProcessor.process(exchange);
        List<String> lines = exchange.getIn().getBody(List.class);
        Assert.assertEquals("a", lines.get(0));
        Assert.assertEquals("b", lines.get(1));
    }
}
