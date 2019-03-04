package br.com.flatfile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlatFileConfig {

    public static String SEPARATOR_CHARACTER;
    public static Integer SALESMAN_TAX_ID_POSITION;
    public static Integer SALESMAN_NAME_POSITION;
    public static Integer SALESMAN_SALARY_POSITION;
    public static Integer CUSTOMER_TAX_ID_POSITION;
    public static Integer CUSTOMER_NAME_POSITION;
    public static Integer CUSTOMER_BUSINESS_AREA_POSITION;

    @Value("separator")
    public static void setSeparatorCharacter(String separatorCharacter) {
        SEPARATOR_CHARACTER = separatorCharacter;
    }

    @Value("position.salesman.taxId")
    public static void setSalesmanTaxIdPosition(Integer salesmanTaxIdPosition) {
        SALESMAN_TAX_ID_POSITION = salesmanTaxIdPosition;
    }

    @Value("position.salesman.name")
    public static void setSalesmanNamePosition(Integer salesmanNamePosition) {
        SALESMAN_NAME_POSITION = salesmanNamePosition;
    }

    @Value("position.salesman.salary")
    public static void setSalesmanSalaryPosition(Integer salesmanSalaryPosition) {
        SALESMAN_SALARY_POSITION = salesmanSalaryPosition;
    }

    @Value("position.customer.taxId")
    public static void setCustomerTaxIdPosition(Integer customerTaxIdPosition) {
        CUSTOMER_TAX_ID_POSITION = customerTaxIdPosition;
    }

    @Value("position.customer.name")
    public static void setCustomerNamePosition(Integer customerNamePosition) {
        CUSTOMER_NAME_POSITION = customerNamePosition;
    }

    @Value("position.customer.businessArea")
    public static void setCustomerBusinessAreaPosition(Integer customerBusinessAreaPosition) {
        CUSTOMER_BUSINESS_AREA_POSITION = customerBusinessAreaPosition;
    }
}
