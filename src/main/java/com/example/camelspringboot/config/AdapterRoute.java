package com.example.camelspringboot.config;

import com.example.camelspringboot.adapter.FindOutWeather;
import com.example.camelspringboot.data.serviceARequest.MessageA;
import com.example.camelspringboot.data.serviceBRequest.MessageB;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.concurrent.TimeoutException;

/**
 * @author Orlov Diga
 */
@Component
public class AdapterRoute extends RouteBuilder {

    @Value("${server.port}")
    String serverPort;

    @Value("${baeldung.api.path}")
    String contextPath;

    @Value("${serviceId}")
    String serviceId;

    @Autowired
    private FindOutWeather adapter;

    @Override
    public void configure() {

        CamelContext context = new DefaultCamelContext();

        onException(TimeoutException.class)
                .process(exchange -> {
                    exchange.getIn().setBody("Error");
                    exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, Response.Status.BAD_REQUEST);
                }).end();

        rest("/api/").description("Adapter Rest Service")
        .post("/adapter")
        .produces(MediaType.APPLICATION_JSON)
        .consumes(MediaType.APPLICATION_JSON)
        .bindingMode(RestBindingMode.auto)
        .type(MessageA.class)
        .enableCORS(true)
        .to("direct:crossroad");

        from("direct:crossroad")
                  .choice()
                    .when(exchange -> {
                        MessageA a = exchange.getIn().getBody(MessageA.class);
                        return a.getMsg() == null || a.getMsg().isEmpty();
                    })
                        //.method(MessageA.class, "isEmptyMsg")
                        .to("direct:empty_msg")
                    .otherwise()
                        .to("direct:validation")
                  .endChoice();

        from("direct:validation")
                .filter().method(MessageA.class, "checkLanguage")
                .to("direct:adapter");

        from("direct:adapter")
                .process(exchange -> {
                    MessageA msg = (MessageA) exchange.getIn().getBody();

                    int temp = adapter.getWeather(exchange);

                    MessageB msgB = new MessageB();
                    msgB.setCurrentTemp(temp);
                    msgB.setTxt(msg.getMsg());

                    exchange
                            .getIn()
                            .setBody(msgB); })
                .to("mock:direct:serviceB")
                .to("log:?showBody=true&showHeaders=true&level=INFO")
                .process(exchange -> {
                    exchange.getMessage().setBody(simple("Ok!"));
                });

        from("direct:empty_msg").log("Hello, empty_msg").process(exchange -> {
            exchange
                    .getMessage()
                    .setBody(simple("empty msg field."));
            exchange
                    .getMessage()
                    .setHeader(Exchange.HTTP_RESPONSE_CODE, Response.Status.BAD_REQUEST.getStatusCode());
        });
    }
}
