package de.hszg.heider.soapservice;

import javax.jws.WebService;
import java.util.Date;

@WebService(endpointInterface = "de.hszg.heider.soapservice.TimeServer")
public class TimeServerImpl implements TimeServer{

    public String getDateAsString() {
        return new Date().toString();
    }

    public Long getDateInMilis() {
        return new Date().getTime();
    }
}
