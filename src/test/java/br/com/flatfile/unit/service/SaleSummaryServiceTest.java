package br.com.flatfile.unit.service;

import br.com.flatfile.model.*;
import br.com.flatfile.service.SaleSummaryService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleSummaryServiceTest {

    private SaleSummaryService saleSummaryService;

    @Before
    public void setUp() {
        saleSummaryService = new SaleSummaryService();
    }

    @Test
    public void shouldSummarizeListOfDataWithJustOneRegister() {
        List<FlatFileData> listData = new ArrayList<>();
        List<SaleItem> listItem = new ArrayList<>();

        listData.add(new Salesman("123", "Joze", new BigDecimal(100)));
        listData.add(new Customer("456", "Vitoria", "Rural"));
        listItem.add(new SaleItem(1L, 1L, new BigDecimal(5)));
        listData.add(new Sale(1L, listItem, "Joze"));

        SaleSummary saleSummary = saleSummaryService.getSummarySale(listData);

        Assert.assertEquals(1L, saleSummary.getAmountClients().longValue());
        Assert.assertEquals(1L, saleSummary.getAmountSalesman().longValue());
        Assert.assertEquals(1L, saleSummary.getMostExpensiveSaleId().longValue());
        Assert.assertEquals("Joze", saleSummary.getWorstSalesmanName());
    }

    @Test
    public void shouldSummarizeListOfDataWithMoreThanOneRegister() {
        List<FlatFileData> listData = new ArrayList<>();

        listData.add(new Salesman("111", "Joze", new BigDecimal(100)));
        listData.add(new Salesman("222", "Alfredo", new BigDecimal(150)));
        listData.add(new Salesman("333", "Maria", new BigDecimal(300)));

        listData.add(new Customer("444", "Vitoria", "Rural"));
        listData.add(new Customer("555", "Ivone", "Rural"));
        listData.add(new Customer("777", "Pedro", "Comercio"));
        listData.add(new Customer("888", "Almir", "Rural"));

        List<SaleItem> listItem = new ArrayList<>();
        listItem.add(new SaleItem(1L, 1L, new BigDecimal(5)));
        listData.add(new Sale(10L, listItem, "Joze"));

        listItem = new ArrayList<>();
        listItem.add(new SaleItem(1L, 5L, new BigDecimal(5)));
        listItem.add(new SaleItem(2L, 10L, new BigDecimal(1)));

        listData.add(new Sale(11L, listItem, "Alfredo"));

        listItem = new ArrayList<>();
        listItem.add(new SaleItem(3L, 1L, new BigDecimal(1)));
        listData.add(new Sale(12L, listItem, "Maria"));

        SaleSummary saleSummary = saleSummaryService.getSummarySale(listData);

        Assert.assertEquals(4L, saleSummary.getAmountClients().longValue());
        Assert.assertEquals(3L, saleSummary.getAmountSalesman().longValue());
        Assert.assertEquals(11, saleSummary.getMostExpensiveSaleId().longValue());
        Assert.assertEquals("Maria", saleSummary.getWorstSalesmanName());
    }
}
