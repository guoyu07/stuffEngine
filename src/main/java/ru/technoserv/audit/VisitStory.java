package ru.technoserv.audit;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
@ComponentScan
public class VisitStory {

    private String visitor;
    private String requestToServer;
    private String serverActions;
    private String dataReturn;

    public void VisitStory(){

    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public void setRequestToServer(String requestToServer) {
        this.requestToServer = requestToServer;
    }

    public void setServerActions(String serverActions) {
        this.serverActions = serverActions;
    }

    public void setDataReturn(String dataReturn) {
        this.dataReturn = dataReturn;
    }
}
