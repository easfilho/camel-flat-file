package br.com.flatfile.unit.service;

import br.com.flatfile.config.FlatFileConfig;
import br.com.flatfile.config.FlatFileConfigTest;
import br.com.flatfile.model.Customer;
import br.com.flatfile.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomerServiceTest {

    private CustomerService customerService;

    @Before
    public void setUp() {
        customerService = new CustomerService();
        FlatFileConfigTest.initializeFlatFileConfig();
    }

    @Test
    public void shouldConvertCustomer() {
        Customer customer = (Customer) customerService.convert("002|2345675434544345|Jose da Silva|Rural");
        Assert.assertEquals("2345675434544345", customer.getTaxId());
        Assert.assertEquals("Jose da Silva", customer.getName());
        Assert.assertEquals("Rural", customer.getBusinessArea());
    }
}
