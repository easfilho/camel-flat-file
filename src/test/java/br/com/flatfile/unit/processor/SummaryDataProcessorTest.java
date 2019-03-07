package br.com.flatfile.unit.processor;

import br.com.flatfile.config.FlatFileConfigTest;
import br.com.flatfile.model.*;
import br.com.flatfile.processor.*;
import br.com.flatfile.route.ConverterFlatFileRoute;
import br.com.flatfile.service.SaleSummaryService;
import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.impl.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SummaryDataProcessorTest extends CamelTestSupport {

    private SummaryDataProcessor summaryDataProcessor;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        FlatFileConfigTest.initializeFlatFileConfig();
        summaryDataProcessor = new SummaryDataProcessor(new SaleSummaryService());

        return new ConverterFlatFileRoute(Mockito.mock(FileNameProcessor.class),
                Mockito.mock(FlatFileContentProcessor.class),
                Mockito.mock(FlatFileDataProcessor.class),
                summaryDataProcessor,
                Mockito.mock(SummaryFileContentProcessor.class));
    }

    @Test
    public void shouldReturnSaleSummary() {
        List<FlatFileData> listData = new ArrayList<>();
        List<SaleItem> listItem = new ArrayList<>();

        listData.add(new Salesman("123", "Joze", new BigDecimal(100)));
        listData.add(new Customer("456", "Vitoria", "Rural"));
        listItem.add(new SaleItem(1L, 1L, new BigDecimal(5)));
        listData.add(new Sale(10L, listItem, "Joze"));

        Exchange exchange = new DefaultExchange(super.context());
        exchange.getIn().setBody(listData);
        summaryDataProcessor.process(exchange);
        SaleSummary saleSummary = exchange.getIn().getBody(SaleSummary.class);
        Assert.assertEquals(1L, saleSummary.getAmountClients().longValue());
        Assert.assertEquals(1L, saleSummary.getAmountSalesman().longValue());
        Assert.assertEquals(10L, saleSummary.getMostExpensiveSaleId().longValue());
        Assert.assertEquals("Joze", saleSummary.getWorstSalesmanName());
    }
}
