package ru.technoserv.domain;

import javax.persistence.*;

@Entity
@Table(name = "CERT_PAGES")
public class Page {

    @Id
    @Column (name = "ID")
    Integer id;

    @Column(name = "CERT_NUMBER")
    Integer number;

    @Column(name = "PAGE")
    Integer page;
    @Column(name = "IMAGE")
    byte[] image;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    @ManyToOne
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
