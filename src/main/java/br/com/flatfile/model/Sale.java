package br.com.flatfile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Sale extends FlatFileData{
    private Long id;
    private List<SaleItem> saleItems;
    private String salesmanName;

    public BigDecimal getValue() {
        return saleItems.stream()
                .map(saleItem -> saleItem.getPrice().multiply(new BigDecimal(saleItem.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
