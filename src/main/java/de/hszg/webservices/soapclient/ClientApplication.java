package de.hszg.heider.soapclient;

import de.hszg.heider.soapservice.TimeServer;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class ClientApplication {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:8080/ts?wsdl");
        QName qName = new QName("http://soapservice.heider.hszg.de/",
                "TimeServerImplService");

        Service service = Service.create(url, qName);
        TimeServer ts = service.getPort(TimeServer.class);

        System.out.println(ts.getDateAsString());
        System.out.println(ts.getDateInMilis());
    }
}
