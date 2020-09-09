package com.example.camelspringboot.data.serviceARequest;

import org.apache.camel.Exchange;

import java.io.Serializable;

/**
 * @author Orlov Diga
 */
public class MessageA implements Serializable {
    private String msg;
    private Language lng;
    private Coordinates coordinates;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Language getLng() {
        return lng;
    }

    public void setLng(Language lng) {
        this.lng = lng;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public boolean checkLanguage(Exchange exchange) {
        System.out.println("This exchange in MessageA ->> " + exchange);
        MessageA msg = (MessageA) exchange.getIn().getBody();

        return msg.getLng().equals(Language.RU);
    }

    public boolean isEmptyMsg() {
        return msg == null || msg.isEmpty();
    }

    @Override
    public String toString() {
        return "MessageA{" +
                "msg='" + msg + '\'' +
                ", lng=" + lng +
                ", coordinates=" + coordinates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageA messageA = (MessageA) o;

        if (msg != null ? !msg.equals(messageA.msg) : messageA.msg != null) return false;
        if (lng != messageA.lng) return false;
        return coordinates != null ? coordinates.equals(messageA.coordinates) : messageA.coordinates == null;
    }

    @Override
    public int hashCode() {
        int result = msg != null ? msg.hashCode() : 0;
        result = 31 * result + (lng != null ? lng.hashCode() : 0);
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        return result;
    }
}
