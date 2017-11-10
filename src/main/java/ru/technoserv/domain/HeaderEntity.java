package ru.technoserv.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


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


    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOC_HEADER_ID")
    private Set<DocumentDataEntity> data;

    public HeaderEntity(){}

    public Set<DocumentDataEntity> getData() {
        return data;
    }

    public void setData(Set<DocumentDataEntity> data) {
        this.data = data;
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
                ", data=" + data +
                '}';
    }
}
