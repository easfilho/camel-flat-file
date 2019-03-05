package br.com.flatfile.service;

import br.com.flatfile.config.FlatFileConfig;
import br.com.flatfile.model.FlatFileData;
import br.com.flatfile.model.Salesman;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component(value = "salesman")
public class SalesmanService implements FlatFileConverter {

    @Override
    public FlatFileData convert(String data) {
        String[] fields = data.split(FlatFileConfig.DATA_SEPARATOR_CHARACTER);
        String taxId = fields[FlatFileConfig.SALESMAN_TAX_ID_POSITION];
        String name = fields[FlatFileConfig.SALESMAN_NAME_POSITION];
        BigDecimal salary = new BigDecimal(fields[FlatFileConfig.SALESMAN_SALARY_POSITION]);
        return new Salesman(taxId, name, salary);
    }
}
