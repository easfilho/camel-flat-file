package br.com.flatfile.route;


import br.com.flatfile.processor.FileNameProcessor;
import br.com.flatfile.processor.FlatFileContentProcessor;
import br.com.flatfile.processor.FlatFileDataProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterFlatFileRoute extends RouteBuilder {

    private final FileNameProcessor fileNameProcessor;
    private final FlatFileContentProcessor flatFileContentProcessor;
    private final FlatFileDataProcessor flatFileDataProcessor;

    @Autowired
    public ConverterFlatFileRoute(FileNameProcessor fileNameProcessor, FlatFileContentProcessor flatFileContentProcessor, FlatFileDataProcessor flatFileDataProcessor) {
        this.fileNameProcessor = fileNameProcessor;
        this.flatFileContentProcessor = flatFileContentProcessor;
        this.flatFileDataProcessor = flatFileDataProcessor;
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
                            .to("file:data/output")
                        .endChoice()
                    .end()
                .end();
    }
}
