package ru.technoserv.endpoint;

import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import ru.technoserv.domain.GetEmployeeRequest;
import ru.technoserv.domain.GetEmployeeResponse;

public interface EmployeeEndpointInt {

    GetEmployeeResponse getEmployee(@RequestPayload GetEmployeeRequest request);

}
