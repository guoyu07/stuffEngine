package ru.technoserv.exceptions;

public class CertificateException extends CommonException {

    private final static int ERROR_ID = 10;
    private final static String SHORT_MESSAGE = "Неправильный формат сертификата.";
    private int certNum;

    public CertificateException(int certNum) {
        this.certNum = certNum;
    }

    public int getErrorId() {
        return ERROR_ID;
    }

    public String getShortMessage() {
        return SHORT_MESSAGE;
    }

    public int getEmpID() {
        return certNum;
    }
}

