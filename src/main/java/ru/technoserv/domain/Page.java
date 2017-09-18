package ru.technoserv.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CERT_PAGES")
public class Page {
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
