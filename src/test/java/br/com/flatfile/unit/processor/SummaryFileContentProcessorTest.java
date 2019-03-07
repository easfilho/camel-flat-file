package br.com.flatfile.unit.processor;

import br.com.flatfile.config.FlatFileConfigTest;
import br.com.flatfile.model.SaleSummary;
import br.com.flatfile.processor.*;
import br.com.flatfile.route.ConverterFlatFileRoute;
import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class SummaryFileContentProcessorTest extends CamelTestSupport {

    private SummaryFileContentProcessor summaryFileContentProcessor;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        FlatFileConfigTest.initializeFlatFileConfig();
        summaryFileContentProcessor = new SummaryFileContentProcessor();

        return new ConverterFlatFileRoute(Mockito.mock(FileNameProcessor.class),
                Mockito.mock(FlatFileContentProcessor.class),
                Mockito.mock(FlatFileDataProcessor.class),
                Mockito.mock(SummaryDataProcessor.class),
                summaryFileContentProcessor);
    }

    @Test
    public void shouldReturnSummaryContentForFile() {
        SaleSummary saleSummary = new SaleSummary(20L,
                2L,
                11L,
                "Diego");
        String expectedContent = "Amount of clients: 20\r\n" +
                "Amount of salesman: 2\r\n" +
                "Most expensive sale id: 11\r\n" +
                "Worst salesman ever: Diego";
        Exchange exchange = new DefaultExchange(super.context);
        exchange.getIn().setBody(saleSummary);
        summaryFileContentProcessor.process(exchange);
        String content = exchange.getIn().getBody(String.class);
        Assert.assertEquals(expectedContent, content);
    }
}
