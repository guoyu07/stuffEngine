package ru.technoserv.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CERT_PAGES")
public class Page implements Serializable{

    public Page() {
        //по требованию hibernate требуется пустой конструктор
    }

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "page_generator"
    )
    @GenericGenerator(
            name = "page_generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "CERT_PAGES_SEQ"),
//                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    @Column(name = "ID")
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
