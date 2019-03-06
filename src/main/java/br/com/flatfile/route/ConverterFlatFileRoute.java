package br.com.flatfile.route;


import br.com.flatfile.processor.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterFlatFileRoute extends RouteBuilder {

    private final FileNameProcessor fileNameProcessor;
    private final FlatFileContentProcessor flatFileContentProcessor;
    private final FlatFileDataProcessor flatFileDataProcessor;
    private final SummaryDataProcessor summaryDataProcessor;
    private final SummaryFileContentProcessor summaryFileContentProcessor;

    @Autowired
    public ConverterFlatFileRoute(FileNameProcessor fileNameProcessor,
                                  FlatFileContentProcessor flatFileContentProcessor,
                                  FlatFileDataProcessor flatFileDataProcessor,
                                  SummaryDataProcessor summaryDataProcessor,
                                  SummaryFileContentProcessor summaryFileContentProcessor) {
        this.fileNameProcessor = fileNameProcessor;
        this.flatFileContentProcessor = flatFileContentProcessor;
        this.flatFileDataProcessor = flatFileDataProcessor;
        this.summaryDataProcessor = summaryDataProcessor;
        this.summaryFileContentProcessor = summaryFileContentProcessor;
    }

    @Override
    public void configure() {
        from("direct:convertFlatFile")
                .routeId("converterFlatFile")
                .from("file:data/input?noop=true")
                    .choice()
                        .when().simple("${in.body} != null")
                            .log("Body -> ${body}")
                            .process(fileNameProcessor)
                            .process(flatFileContentProcessor)
                            .process(flatFileDataProcessor)
                            .process(summaryDataProcessor)
                            .process(summaryFileContentProcessor)
                            .to("file:data/output")
                        .endChoice()
                    .end()
                .end();
    }
}
