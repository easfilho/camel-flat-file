package br.com.flatfile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer extends FlatFileData {
    private String taxId;
    private String name;
    private String businessArea;
}
