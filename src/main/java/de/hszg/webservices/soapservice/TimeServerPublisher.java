package de.hszg.heider.soapservice;

import javax.xml.ws.Endpoint;

public class TimeServerPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/ts", new TimeServerImpl());
    }
}
