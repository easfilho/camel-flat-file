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
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FlatFileContentProcessor.class)
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
    public void shouldReturnLines() throws IOException {
        GenericFile<File> genericFile = new GenericFile<>();
        genericFile.setFile(new File("path"));
        PowerMockito.mockStatic(Files.class);
        List<String> lines = new ArrayList<>();
        lines.add("a");
        lines.add("b");

        PowerMockito.when(Files.readAllLines(genericFile.getFile().toPath(), StandardCharsets.UTF_8)).thenReturn(lines);

        Exchange exchange = new DefaultExchange(super.context());
        exchange.getIn().setBody(genericFile);
        flatFileContentProcessor.process(exchange);
        List<String> expectedLines = exchange.getIn().getBody(List.class);
        Assert.assertEquals("a", expectedLines.get(0));
        Assert.assertEquals("b", expectedLines.get(1));
    }
}
