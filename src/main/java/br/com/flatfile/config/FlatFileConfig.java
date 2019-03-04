package br.com.flatfile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlatFileConfig {

    public static String SEPARATOR_CHARACTER;
    public static Integer TAX_ID_POSITION;
    public static Integer NAME_POSITION;
    public static Integer SALARY_POSITION;

    @Value("separator")
    public static void setSeparatorCharacter(String separatorCharacter) {
        SEPARATOR_CHARACTER = separatorCharacter;
    }

    @Value("position.taxId")
    public static void setTaxIdPosition(Integer taxIdPosition) {
        TAX_ID_POSITION = taxIdPosition;
    }

    @Value("position.name")
    public static void setNamePosition(Integer namePosition) {
        NAME_POSITION = namePosition;
    }

    @Value("position.salary")
    public static void setSalaryPosition(Integer salaryPosition) {
        SALARY_POSITION = salaryPosition;
    }
}
