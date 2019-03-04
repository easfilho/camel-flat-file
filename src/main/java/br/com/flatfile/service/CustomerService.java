package br.com.flatfile.service;

import br.com.flatfile.config.FlatFileConfig;
import br.com.flatfile.model.Customer;
import br.com.flatfile.model.FlatFileData;
import org.springframework.stereotype.Component;

@Component(value = "customer")
public class CustomerService implements Converter {
    @Override
    public FlatFileData convert(String data) {
        String[] fields = data.split(FlatFileConfig.SEPARATOR_CHARACTER);
        String taxId = fields[FlatFileConfig.CUSTOMER_TAX_ID_POSITION];
        String name = fields[FlatFileConfig.CUSTOMER_NAME_POSITION];
        String businessArea = fields[FlatFileConfig.CUSTOMER_BUSINESS_AREA_POSITION];
        return new Customer(taxId, name, businessArea);
    }
}
