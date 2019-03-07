package br.com.flatfile.unit.processor;

import br.com.flatfile.config.FlatFileConfigTest;
import br.com.flatfile.model.*;
import br.com.flatfile.processor.*;
import br.com.flatfile.route.ConverterFlatFileRoute;
import br.com.flatfile.service.CustomerService;
import br.com.flatfile.service.SaleService;
import br.com.flatfile.service.SalesmanService;
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

public class FlatFileDataProcessorTest extends CamelTestSupport {

    private FlatFileDataProcessor flatFileDataProcessor;

    @Override
    protected RoutesBuilder createRouteBuilder() {
        FlatFileConfigTest.initializeFlatFileConfig();
        flatFileDataProcessor = new FlatFileDataProcessor(new SalesmanService(),
                new CustomerService(),
                new SaleService());

        return new ConverterFlatFileRoute(Mockito.mock(FileNameProcessor.class),
                Mockito.mock(FlatFileContentProcessor.class),
                flatFileDataProcessor,
                Mockito.mock(SummaryDataProcessor.class),
                Mockito.mock(SummaryFileContentProcessor.class));
    }

    @Test
    public void shouldReturnOneSalesman() {
        List<String> lines = new ArrayList<>();
        lines.add("001|1234567891234|Diego|50000");
        Exchange exchange = new DefaultExchange(super.context());
        exchange.getIn().setBody(lines);
        flatFileDataProcessor.process(exchange);
        List<FlatFileData> flatFileData = exchange.getIn().getBody(List.class);
        Assert.assertEquals(1, flatFileData.size());
        Salesman expectedSalesman = new Salesman("1234567891234", "Diego", new BigDecimal("50000"));
        Assert.assertTrue(flatFileData.stream().allMatch(data -> data.equals(expectedSalesman)));
    }

    @Test
    public void shouldReturnTwoSalesman() {
        List<String> lines = new ArrayList<>();
        lines.add("001|1234567891234|Diego|50000");
        lines.add("001|01234567890|Emilia|5");
        Exchange exchange = new DefaultExchange(super.context());
        exchange.getIn().setBody(lines);
        flatFileDataProcessor.process(exchange);
        List<FlatFileData> flatFileData = exchange.getIn().getBody(List.class);
        Assert.assertEquals(2, flatFileData.size());
        Salesman expectedSalesman1 = new Salesman("1234567891234", "Diego", new BigDecimal("50000"));
        Salesman expectedSalesman2 = new Salesman("01234567890", "Emilia", new BigDecimal("5"));
        Assert.assertTrue(flatFileData.stream()
                .allMatch(data -> data.equals(expectedSalesman1) || data.equals(expectedSalesman2)));
    }

    @Test
    public void shouldReturnOneCustomer() {
        List<String> lines = new ArrayList<>();
        lines.add("002|2345675434544345|Jose da Silva|Rural");
        Exchange exchange = new DefaultExchange(super.context());
        exchange.getIn().setBody(lines);
        flatFileDataProcessor.process(exchange);
        List<FlatFileData> flatFileData = exchange.getIn().getBody(List.class);
        Assert.assertEquals(1, flatFileData.size());
        Customer expectedCustomer = new Customer("2345675434544345", "Jose da Silva", "Rural");
        Assert.assertTrue(flatFileData.stream()
                .allMatch(data -> data.equals(expectedCustomer)));
    }

    @Test
    public void shouldReturnTwoCustomers() {
        List<String> lines = new ArrayList<>();
        lines.add("002|2345675434544345|Jose da Silva|Rural");
        lines.add("002|01234567890|Fernando Becker|Comercial");
        Exchange exchange = new DefaultExchange(super.context());
        exchange.getIn().setBody(lines);
        flatFileDataProcessor.process(exchange);
        List<FlatFileData> flatFileData = exchange.getIn().getBody(List.class);
        Assert.assertEquals(2, flatFileData.size());
        Customer expectedCustomer1 = new Customer("2345675434544345", "Jose da Silva", "Rural");
        Customer expectedCustomer2 = new Customer("01234567890", "Fernando Becker", "Comercial");
        Assert.assertTrue(flatFileData.stream()
                .allMatch(data -> data.equals(expectedCustomer1) || data.equals(expectedCustomer2)));
    }

    @Test
    public void shouldReturnOneSale() {
        List<String> lines = new ArrayList<>();
        lines.add("003|10|[1-10-100,2-30-2.50]|Diego");
        Exchange exchange = new DefaultExchange(super.context());
        exchange.getIn().setBody(lines);
        flatFileDataProcessor.process(exchange);
        List<FlatFileData> flatFileData = exchange.getIn().getBody(List.class);
        Assert.assertEquals(1, flatFileData.size());
        List<SaleItem> expectedSaleItems = new ArrayList<>();
        expectedSaleItems.add(new SaleItem(1L, 10L, new BigDecimal("100")));
        expectedSaleItems.add(new SaleItem(2L, 30L, new BigDecimal("2.50")));
        Sale expectedSale = new Sale(10L, expectedSaleItems, "Diego");
        Assert.assertEquals(expectedSale, flatFileData.get(0));
    }

    @Test
    public void shouldReturnTwoSales() {
        List<String> lines = new ArrayList<>();
        lines.add("003|10|[1-10-100,2-30-2.50]|Diego");
        lines.add("003|11|[2-1-1]|Luan");
        Exchange exchange = new DefaultExchange(super.context());
        exchange.getIn().setBody(lines);
        flatFileDataProcessor.process(exchange);
        List<FlatFileData> flatFileData = exchange.getIn().getBody(List.class);
        Assert.assertEquals(2, flatFileData.size());
        List<SaleItem> expectedSaleItems = new ArrayList<>();
        expectedSaleItems.add(new SaleItem(1L, 10L, new BigDecimal("100")));
        expectedSaleItems.add(new SaleItem(2L, 30L, new BigDecimal("2.50")));
        Sale expectedSale1 = new Sale(10L, expectedSaleItems, "Diego");
        expectedSaleItems = new ArrayList<>();
        expectedSaleItems.add(new SaleItem(2L, 1L, new BigDecimal("1")));
        Sale expectedSale2 = new Sale(11L, expectedSaleItems, "Luan");
        Assert.assertTrue(flatFileData.stream()
                .allMatch(data -> data.equals(expectedSale1) || data.equals(expectedSale2)));
    }

    @Test
    public void shouldReturnOneSalesmanOneCustomerOneSale() {
        List<String> lines = new ArrayList<>();
        lines.add("001|1234567891234|Diego|50000");
        lines.add("002|2345675434544345|Jose da Silva|Rural");
        lines.add("003|10|[1-10-100,2-30-2.50]|Diego");
        Exchange exchange = new DefaultExchange(super.context());
        exchange.getIn().setBody(lines);
        flatFileDataProcessor.process(exchange);
        List<FlatFileData> flatFileData = exchange.getIn().getBody(List.class);
        Assert.assertEquals(3, flatFileData.size());
        Salesman expectedSalesman = new Salesman("1234567891234", "Diego", new BigDecimal("50000"));
        Customer expectedCustomer = new Customer("2345675434544345", "Jose da Silva", "Rural");
        List<SaleItem> expectedSaleItems = new ArrayList<>();
        expectedSaleItems.add(new SaleItem(1L, 10L, new BigDecimal("100")));
        expectedSaleItems.add(new SaleItem(2L, 30L, new BigDecimal("2.50")));
        Sale expectedSale = new Sale(10L, expectedSaleItems, "Diego");
        Assert.assertTrue(flatFileData.stream()
                .allMatch(data -> data.equals(expectedSalesman) || data.equals(expectedCustomer) || data.equals(expectedSale)));
    }
}
