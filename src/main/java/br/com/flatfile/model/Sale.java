package br.com.flatfile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Sale extends FlatFileData{
    private Long id;
    private List<SaleItem> saleItems;
    private String salesmanName;
}
