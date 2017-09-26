package ru.technoserv.ws;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.technoserv.domain.schemas.ChangeEmployeeRequest;
import ru.technoserv.domain.schemas.ChangeEmployeeResponse;

@Endpoint
public class EndpointEmp {
    private static final String NAMESPACE_URI = "http://technoserv.ru/domain/schemas";


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public ChangeEmployeeResponse changeEmployee(@RequestPayload ChangeEmployeeRequest request) {
        ChangeEmployeeResponse response = new ChangeEmployeeResponse();
        response.setEmployee(request.getEmployee());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++\nresponse lucky");
        return response;
    }
}