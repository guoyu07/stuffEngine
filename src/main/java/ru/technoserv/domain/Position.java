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
@Table(name = "POSITION")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "position", propOrder = {
        "id",
        "title"
})
public class Position {

    private int id;
    @XmlElement(required = true)
    private String title;

    @Id
    @Column(name = "POS_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Position(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Position() {
    }

    public Position(int id) {
        this(id, null);
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;

        Position position = (Position) o;

        if (getId() != position.getId()) return false;
        return getTitle().equals(position.getTitle());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getTitle().hashCode();
        return result;
    }
}
