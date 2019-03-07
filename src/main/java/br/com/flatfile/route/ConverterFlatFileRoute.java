package br.com.flatfile.route;


import br.com.flatfile.processor.*;
import org.apache.camel.LoggingLevel;
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
        onException(Exception.class)
                .log(LoggingLevel.ERROR, "[Flat-File-Summary] Error in processing of file: ${header.CamelFileName}");

        from("direct:convertFlatFile")
                .routeId("converterFlatFile")
                .from("file:data/input?delete=true")
                    .choice()
                        .when().simple("${in.body} != null")
                            .log(LoggingLevel.INFO, "[Flat-File-Summary] Reading file: ${header.CamelFileName}")
                            .process(flatFileContentProcessor)
                            .process(flatFileDataProcessor)
                            .process(summaryDataProcessor)
                            .process(summaryFileContentProcessor)
                            .process(fileNameProcessor)
                            .to("file:data/output")
                            .log(LoggingLevel.INFO, "[Flat-File-Summary] File ${header.CamelFileName} processed with success")
                        .endChoice()
                    .end()
                .end();
    }
}
