package ru.technoserv.domain;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "DOC_HEADER")
public class HeaderEntity implements Serializable {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "CLIENT_ID")
    private int clientId;

    @Column(name = "VERSION")
    private int version;

    @Column(name = "CURRENCY")
    private String currency;

    @Column(name = "DOC_ID")
    private int docId;

    public HeaderEntity(){}

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "HeaderEntity{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", version=" + version +
                ", currency='" + currency + '\'' +
                ", docId=" + docId +
                '}';
    }
}
