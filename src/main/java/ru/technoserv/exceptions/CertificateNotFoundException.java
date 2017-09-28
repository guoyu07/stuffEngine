package ru.technoserv.exceptions;

public class CertificateNotFoundException extends CertificateException {

    private final static int ERROR_ID = 4;
    private final static String SHORT_MESSAGE = "Сертификат c таким номером не найден";

    public CertificateNotFoundException(int certNum) {
        super(certNum);
    }

    public int getErrorId() {
        return ERROR_ID;
    }

    public String getShortMessage() {
        return SHORT_MESSAGE;
    }
}
