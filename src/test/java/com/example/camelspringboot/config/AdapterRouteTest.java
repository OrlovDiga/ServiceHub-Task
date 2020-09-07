package com.example.camelspringboot.config;

import com.example.camelspringboot.data.TestResponse;
import com.example.camelspringboot.data.serviceARequest.Coordinates;
import com.example.camelspringboot.data.serviceARequest.Language;
import com.example.camelspringboot.data.serviceARequest.MessageA;
import com.example.camelspringboot.data.serviceBRequest.MessageB;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * @author Orlov Diga
 */
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest
@MockEndpoints
public class AdapterRouteTest {

    @Autowired
    private ProducerTemplate template;

    @EndpointInject("mock:direct:end")
    MockEndpoint mock;

    @EndpointInject("mock:direct:serviceB")
    MockEndpoint serviceBMock;

    @EndpointInject("mock:direct:weather-calls")
    MockEndpoint mockTest;

    MessageA msg;

    @Test
    public void CompletionAdapterTest() throws Exception {
        MessageA msg = new MessageA();

        msg.setMsg("test");
        msg.setLng(Language.RU);
        Coordinates coordinates = new Coordinates();
        coordinates.setLongitude("10");
        coordinates.setLatitude("10");
        msg.setCoordinates(coordinates);

        String responseBody =
                template.requestBody("direct:adapter", msg, String.class);
        MessageB msgB =
                serviceBMock.getExchanges().get(0).getIn().getBody(MessageB.class);

        assertEquals("Simple: Ok!", responseBody);
        assertEquals(msg.getMsg(), msgB.getTxt());
        assertNotNull(msgB.getCurrentTemp());

        serviceBMock.assertIsSatisfied();
    }

    @Test
    public void FailedNotMsgTest() throws Exception {
        MessageA msg = new MessageA();

        msg.setMsg("");
        msg.setLng(Language.RU);
        Coordinates coordinates = new Coordinates();
        coordinates.setLongitude("10");
        coordinates.setLatitude("10");
        msg.setCoordinates(coordinates);

        String responseBody =
                template.requestBody("direct:crossroad", msg, String.class);

        assertEquals("Simple: empty msg field.", responseBody);
        assertEquals(0, serviceBMock.getExchanges().size());

        serviceBMock.assertIsSatisfied();
    }

    @Test
    public void NotRuFilterTest() {
        MessageA msgA = new MessageA();
        Coordinates coordinates = new Coordinates();

        coordinates.setLatitude("10");
        coordinates.setLongitude("100");
        msgA.setCoordinates(coordinates);
        msgA.setMsg("test");
        msgA.setLng(Language.ES);

        MessageA serviceAResponse =
                template.requestBody("direct:validation", msgA, MessageA.class);

        assertEquals(msgA, serviceAResponse);
        assertEquals(0, serviceBMock.getExchanges().size());
    }


}