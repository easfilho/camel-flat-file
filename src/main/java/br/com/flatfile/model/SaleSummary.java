package br.com.flatfile.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleSummary {
    private Long amountClients;
    private Long amountSalesman;
    private Long mostExpensiveSaleId;
    private String worstSalesmanName;
}
