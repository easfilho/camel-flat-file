package br.com.flatfile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlatFileConfig {

    public static String DATA_SEPARATOR_CHARACTER;
    public static String DATA_ITEM_SEPARATOR_CHARACTER;
    public static Integer DATA_ID_POSITION;
    public static String SALESMAN_DATA_ID;
    public static String CUSTOMER_DATA_ID;
    public static String SALE_DATA_ID;
    public static String ITEM_SEPARATOR_CHARACTER;
    public static Integer SALESMAN_TAX_ID_POSITION;
    public static Integer SALESMAN_NAME_POSITION;
    public static Integer SALESMAN_SALARY_POSITION;
    public static Integer CUSTOMER_TAX_ID_POSITION;
    public static Integer CUSTOMER_NAME_POSITION;
    public static Integer CUSTOMER_BUSINESS_AREA_POSITION;
    public static Integer SALE_ID_POSITION;
    public static Integer SALE_ITEMS_POSITION;
    public static Integer SALE_SALESMAN_POSITION;
    public static Integer ITEM_ID_POSITION;
    public static Integer ITEM_QUANTITY_POSITION;
    public static Integer ITEM_PRICE_POSITION;

    @Value("${separator.data}")
    public void setDataSeparatorCharacter(String dataSeparatorCharacter) {
        DATA_SEPARATOR_CHARACTER = dataSeparatorCharacter;
    }

    @Value("${separator.dataItem}")
    public void setDataItemSeparatorCharacter(String dataItemSeparatorCharacter) {
        DATA_ITEM_SEPARATOR_CHARACTER = dataItemSeparatorCharacter;
    }

    @Value("${position.data.id}")
    public void setDataIdPosition(Integer dataIdPosition) {
        DATA_ID_POSITION = dataIdPosition;
    }

    @Value("${data.id.salesman}")
    public void setSalesmanDataId(String salesmanDataId) {
        SALESMAN_DATA_ID = salesmanDataId;
    }

    @Value("${data.id.customer}")
    public void setCustomerDataId(String customerDataId) {
        CUSTOMER_DATA_ID = customerDataId;
    }

    @Value("${data.id.sale}")
    public void setSaleDataId(String saleDataId) {
        SALE_DATA_ID = saleDataId;
    }

    @Value("${separator.items}")
    public void setItemSeparatorCharacter(String itemSeparatorCharacter) {
        ITEM_SEPARATOR_CHARACTER = itemSeparatorCharacter;
    }

    @Value("${position.salesman.taxId}")
    public void setSalesmanTaxIdPosition(Integer salesmanTaxIdPosition) {
        SALESMAN_TAX_ID_POSITION = salesmanTaxIdPosition;
    }

    @Value("${position.salesman.name}")
    public void setSalesmanNamePosition(Integer salesmanNamePosition) {
        SALESMAN_NAME_POSITION = salesmanNamePosition;
    }

    @Value("${position.salesman.salary}")
    public void setSalesmanSalaryPosition(Integer salesmanSalaryPosition) {
        SALESMAN_SALARY_POSITION = salesmanSalaryPosition;
    }

    @Value("${position.customer.taxId}")
    public void setCustomerTaxIdPosition(Integer customerTaxIdPosition) {
        CUSTOMER_TAX_ID_POSITION = customerTaxIdPosition;
    }

    @Value("${position.customer.name}")
    public void setCustomerNamePosition(Integer customerNamePosition) {
        CUSTOMER_NAME_POSITION = customerNamePosition;
    }

    @Value("${position.customer.businessArea}")
    public void setCustomerBusinessAreaPosition(Integer customerBusinessAreaPosition) {
        CUSTOMER_BUSINESS_AREA_POSITION = customerBusinessAreaPosition;
    }

    @Value("${position.sale.id}")
    public void setSaleIdPosition(Integer saleIdPosition) {
        SALE_ID_POSITION = saleIdPosition;
    }

    @Value("${position.sale.items}")
    public void setSaleItemsPosition(Integer saleItemsPosition) {
        SALE_ITEMS_POSITION = saleItemsPosition;
    }

    @Value("${position.sale.salesman}")
    public void setSaleSalesmanPosition(Integer saleSalesmanPosition) {
        SALE_SALESMAN_POSITION = saleSalesmanPosition;
    }

    @Value("${position.item.id}")
    public void setItemIdPosition(Integer itemIdPosition) {
        ITEM_ID_POSITION = itemIdPosition;
    }

    @Value("${position.item.quantity}")
    public void setItemQuantityPosition(Integer itemQuantityPosition) {
        ITEM_QUANTITY_POSITION = itemQuantityPosition;
    }

    @Value("${position.item.price}")
    public void setItemPricePosition(Integer itemPricePosition) {
        ITEM_PRICE_POSITION = itemPricePosition;
    }
}
