package br.com.flatfile.processor;

import br.com.flatfile.config.FlatFileConfig;
import br.com.flatfile.model.FlatFileData;
import br.com.flatfile.service.FlatFileConverter;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FlatFileDataProcessor implements Processor {

    private Map<String, FlatFileConverter> mapFlatFileConverter;

    @Autowired
    public FlatFileDataProcessor(@Qualifier("salesman") FlatFileConverter salesmanService,
                                 @Qualifier("customer") FlatFileConverter customerService,
                                 @Qualifier("sale") FlatFileConverter saleService) {
        mapFlatFileConverter = new HashMap<>();
        mapFlatFileConverter.put(FlatFileConfig.SALESMAN_DATA_ID, salesmanService);
        mapFlatFileConverter.put(FlatFileConfig.CUSTOMER_DATA_ID,customerService);
        mapFlatFileConverter.put(FlatFileConfig.SALE_DATA_ID, saleService);
    }

    @Override
    public void process(Exchange exchange) {
        List<String> lines = exchange.getIn().getBody(List.class);
        List<FlatFileData> flatFileDatas = new ArrayList<>();
        lines.parallelStream()
                .forEach(line -> {
                    String[] dataArray = line.split(FlatFileConfig.DATA_SEPARATOR_CHARACTER);
                    flatFileDatas.add(
                            mapFlatFileConverter.get(dataArray[FlatFileConfig.DATA_ID_POSITION]).convert(line)
                    );
                });
        exchange.getIn().setBody(flatFileDatas);
    }
}