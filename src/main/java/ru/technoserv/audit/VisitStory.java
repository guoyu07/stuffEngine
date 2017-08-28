package ru.technoserv.audit;

/**
 * Сохраняет дейсвтия запроса клиента
 */
public class VisitStory {

    private String visitor;
    private String requestToServer;
    private String serverActions;
    private String dataReturn;
    

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
