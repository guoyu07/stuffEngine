package ru.technoserv.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "DOCUMENT")
public class DocumentEntity implements Serializable {

    @Id
    @Column(name = "ID")
    private int id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID")
    private HeaderEntity header;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID")
    Set<DocumentDataEntity> data;

    public DocumentEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HeaderEntity getHeader() {
        return header;
    }

    public void setHeader(HeaderEntity header) {
        this.header = header;
    }

    public Set<DocumentDataEntity> getData() {
        return data;
    }

    public void setData(Set<DocumentDataEntity> data) {
        this.data = data;
    }
}
