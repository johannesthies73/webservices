package de.hszg.heider.soapservice;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface TimeServer {

    @WebMethod
    public String getDateAsString();

    @WebMethod
    public Long getDateInMilis();
}
