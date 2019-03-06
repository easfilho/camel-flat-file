package br.com.flatfile.processor;

import br.com.flatfile.model.SaleSummary;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class SummaryFileContentProcessor implements Processor {

    @Override
    public void process(Exchange exchange) {
        SaleSummary saleSummary = exchange.getIn().getBody(SaleSummary.class);
        String content = String.format(
                "Amount of clients: %d\r\n" +
                "Amount of salesman: %d\r\n" +
                "Most expensive sale id: %d\r\n" +
                "Worst salesman ever: %s",
                saleSummary.getAmountClients(),
                saleSummary.getAmountSalesman(),
                saleSummary.getMostExpensiveSaleId(),
                saleSummary.getWorstSalesmanName());
        exchange.getIn().setBody(content);
    }
}
