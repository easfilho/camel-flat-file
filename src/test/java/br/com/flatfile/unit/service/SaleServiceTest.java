package br.com.flatfile.unit.service;

import br.com.flatfile.config.FlatFileConfigTest;
import br.com.flatfile.model.Sale;
import br.com.flatfile.model.SaleItem;
import br.com.flatfile.service.SaleService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleServiceTest {

    private SaleService saleService;

    @Before
    public void setUp() {
        saleService = new SaleService();
        FlatFileConfigTest.initializeFlatFileConfig();
    }

    @Test
    public void shouldConvertSaleData() {
        Sale sale = (Sale) saleService.convert("003|10|[1-10-100,2-30-2.50,3-40-3.10]|Diego");
        Assert.assertEquals(10L, sale.getId().longValue());
        Assert.assertEquals("Diego", sale.getSalesmanName());
        List<SaleItem> expectedSaleItems = new ArrayList<>();
        expectedSaleItems.add(new SaleItem(1L, 10L, new BigDecimal("100")));
        expectedSaleItems.add(new SaleItem(2L, 30L, new BigDecimal("2.50")));
        expectedSaleItems.add(new SaleItem(3L, 40L, new BigDecimal("3.10")));
        Assert.assertEquals(expectedSaleItems.size(), sale.getSaleItems().size());
        expectedSaleItems.forEach(expectedSaleItem ->
                Assert.assertTrue(sale.getSaleItems().stream().anyMatch(saleItem ->
                        saleItem.getId().equals(expectedSaleItem.getId()) &&
                                saleItem.getQuantity().equals(expectedSaleItem.getQuantity()) &&
                                saleItem.getPrice().equals(expectedSaleItem.getPrice()))));
    }
}


