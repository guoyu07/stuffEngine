package ru.technoserv.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@Entity
@Table(name="GRADE")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "grade", propOrder = {
        "id",
        "description"
})
public class Grade {

    private int id;
    @XmlElement(required = true)
    private String description;

    @Id
    @Column(name = "GRD_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
