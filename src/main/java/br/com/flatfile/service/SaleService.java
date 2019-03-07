package br.com.flatfile.service;

import br.com.flatfile.config.FlatFileConfig;
import br.com.flatfile.model.FlatFileData;
import br.com.flatfile.model.Sale;
import br.com.flatfile.model.SaleItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component(value = "sale")
public class SaleService implements FlatFileConverter {

    @Override
    public FlatFileData convert(String data) {
        String[] fields = data.split(FlatFileConfig.DATA_SEPARATOR_CHARACTER);
        Long id = new Long(fields[FlatFileConfig.SALE_ID_POSITION]);
        List<SaleItem> saleItems = convertListItem(fields[FlatFileConfig.SALE_ITEMS_POSITION]);
        String salesmanName = fields[FlatFileConfig.SALE_SALESMAN_POSITION];
        return new Sale(id, saleItems, salesmanName);
    }

    private List<SaleItem> convertListItem(String dataListItem) {
        return Arrays.stream(getDataItems(dataListItem))
                .map(this::convertItem)
                .collect(Collectors.toList());
    }

    private String[] getDataItems(String dataListItem) {
        return removeInitialAndFinalBrackets(dataListItem)
                .split(FlatFileConfig.ITEM_SEPARATOR_CHARACTER);
    }

    private String removeInitialAndFinalBrackets(String dataListItem) {
        return dataListItem.replace("[", "").replace("]", "");
    }

    private SaleItem convertItem(String dataItem) {
        String[] fieldsItem = dataItem.split(FlatFileConfig.DATA_ITEM_SEPARATOR_CHARACTER);
        Long id = new Long(fieldsItem[FlatFileConfig.ITEM_ID_POSITION]);
        Long quantity = new Long(fieldsItem[FlatFileConfig.ITEM_QUANTITY_POSITION]);
        BigDecimal price = new BigDecimal(fieldsItem[FlatFileConfig.ITEM_PRICE_POSITION]);
        return new SaleItem(id, quantity, price);
    }
}
