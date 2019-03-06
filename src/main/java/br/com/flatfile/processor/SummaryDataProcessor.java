package br.com.flatfile.processor;

import br.com.flatfile.model.FlatFileData;
import br.com.flatfile.model.SaleSummary;
import br.com.flatfile.service.SaleSummaryService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SummaryDataProcessor implements Processor {

    private SaleSummaryService saleSummaryService;

    @Autowired
    public SummaryDataProcessor(SaleSummaryService saleSummaryService) {
        this.saleSummaryService = saleSummaryService;
    }

    @Override
    public void process(Exchange exchange) {
        List<FlatFileData> flatFileDatas = exchange.getIn().getBody(List.class);
        SaleSummary saleSummary = saleSummaryService.getSummarySale(flatFileDatas);
        exchange.getIn().setBody(saleSummary);
    }
}
