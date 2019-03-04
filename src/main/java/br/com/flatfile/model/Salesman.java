package br.com.flatfile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Salesman extends FlatFileData {
    private String taxId;
    private String name;
    private BigDecimal salary;
}
