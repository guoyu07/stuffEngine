package ru.technoserv.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "DOC_DATA")
public class DocumentDataEntity implements Serializable {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "FIELD_NAME")
    private String fieldName;

    @Column(name = "VALUE")
    private int value;

    public DocumentDataEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
