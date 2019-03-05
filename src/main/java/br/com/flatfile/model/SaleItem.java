package br.com.flatfile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class SaleItem {
    private Long id;
    private Long quantity;
    private BigDecimal price;
}
