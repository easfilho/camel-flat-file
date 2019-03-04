package br.com.flatfile.route;


import br.com.flatfile.processor.FileNameProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterFlatFileRoute extends RouteBuilder {

    private final FileNameProcessor fileNameProcessor;

    @Autowired
    public ConverterFlatFileRoute(FileNameProcessor fileNameProcessor) {
        this.fileNameProcessor = fileNameProcessor;
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
                            .convertBodyTo(String.class)
                            .to("file:data/output")
                        .endChoice()
                    .end()
                .end();
    }
}
