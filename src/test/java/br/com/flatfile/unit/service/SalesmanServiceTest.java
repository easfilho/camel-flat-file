package br.com.flatfile.unit.service;

import br.com.flatfile.config.FlatFileConfig;
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
        FlatFileConfig.setSeparatorCharacter("ç");
        FlatFileConfig.setTaxIdPosition(1);
        FlatFileConfig.setNamePosition(2);
        FlatFileConfig.setSalaryPosition(3);
    }

    @Test
    public void shouldConvertSalesmanData() {
        Salesman salesman = (Salesman) salesmanService.convert("001ç01234567890çDiegoç50000");
        Assert.assertEquals("01234567890", salesman.getTaxId());
        Assert.assertEquals("Diego", salesman.getName());
        Assert.assertEquals(0, new BigDecimal("50000").compareTo(salesman.getSalary()));
    }
}
