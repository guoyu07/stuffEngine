package ru.technoserv.exceptions;

public class EmpCertificatesNotFoundException extends RuntimeException {
    private final static int ERROR_ID = 4;
    private final static String SHORT_MESSAGE = "Сертификат c таким номером не найден";
    private int empID;

    public EmpCertificatesNotFoundException(int empID) {
        this.empID = empID;
    }

    public int getErrorId() {
        return ERROR_ID;
    }

    public String getShortMessage() {
        return SHORT_MESSAGE;
    }
}

