package br.com.flatfile.unit.service;

import br.com.flatfile.config.FlatFileConfigTest;
import br.com.flatfile.model.Salesman;
import br.com.flatfile.service.SalesmanService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class SalesmanServiceTest {

    private SalesmanService salesmanService;

    @Before
    public void setUp() {
        salesmanService = new SalesmanService();
        FlatFileConfigTest.initializeFlatFileConfig();
    }

    @Test
    public void shouldConvertSalesmanData() {
        Salesman salesman = (Salesman) salesmanService.convert("001|01234567890|Diego|50000");
        Assert.assertEquals("01234567890", salesman.getTaxId());
        Assert.assertEquals("Diego", salesman.getName());
        Assert.assertEquals(0, new BigDecimal("50000").compareTo(salesman.getSalary()));
    }
}
