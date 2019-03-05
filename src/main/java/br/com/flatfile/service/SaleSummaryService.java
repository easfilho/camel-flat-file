package br.com.flatfile.service;

import br.com.flatfile.model.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SaleSummaryService {

    public SaleSummary getSummarySale(List<FlatFileData> flatFileDatas) {
        Long salesmanAmount = getSalesmanAmount(flatFileDatas);
        Long customerAmount = getCustomerAmount(flatFileDatas);
        Long mostExpensiveSaleId = getMostExpensiveSaleId(flatFileDatas);
        String worstSalesman = getWorstSalesman(flatFileDatas);
        return new SaleSummary(customerAmount, salesmanAmount, mostExpensiveSaleId, worstSalesman);
    }

    private Long getSalesmanAmount(List<FlatFileData> flatFileDatas) {
        return flatFileDatas.stream().filter(flatFileData -> flatFileData instanceof Salesman).count();
    }

    private Long getCustomerAmount(List<FlatFileData> flatFileDatas) {
        return flatFileDatas.stream().filter(flatFileData -> flatFileData instanceof Customer).count();
    }

    private Long getMostExpensiveSaleId(List<FlatFileData> flatFileDatas) {
        return flatFileDatas.stream().filter(flatFileData -> flatFileData instanceof Sale)
                .map(flatFileData -> (Sale) flatFileData)
                .max(Comparator.comparing(Sale::getValue))
                .get()
                .getId();
    }

    private String getWorstSalesman(List<FlatFileData> flatFileDatas) {
        return flatFileDatas.stream()
                .filter(flatFileData -> flatFileData instanceof Sale)
                .map(flatFileData -> (Sale) flatFileData)
                .collect(Collectors.groupingBy(Sale::getSalesmanName))
                .entrySet()
                .stream()
                .min(Comparator.comparing(entry ->
                        entry.getValue()
                                .stream()
                                .map(Sale::getValue)
                                .reduce(BigDecimal.ZERO, BigDecimal::add)))
                .get()
                .getKey();
    }
}
