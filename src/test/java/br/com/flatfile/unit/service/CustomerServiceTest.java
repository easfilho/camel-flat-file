package br.com.flatfile.unit.service;

import br.com.flatfile.config.FlatFileConfig;
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
        FlatFileConfig.setSeparatorCharacter("ç");
        FlatFileConfig.setCustomerTaxIdPosition(1);
        FlatFileConfig.setCustomerNamePosition(2);
        FlatFileConfig.setCustomerBusinessAreaPosition(3);
    }

    @Test
    public void shouldConvertCustomer() {
        Customer customer = (Customer) customerService.convert("002ç2345675434544345çJose da SilvaçRural");
        Assert.assertEquals("2345675434544345", customer.getTaxId());
        Assert.assertEquals("Jose da Silva", customer.getName());
        Assert.assertEquals("Rural", customer.getBusinessArea());
    }
}
