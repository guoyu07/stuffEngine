package ru.technoserv.domain;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EVENT")
public class Action {

    public Action(){

    }

    public Action(int id){
        this.id = id;

    }
    @Id
    @Column(name="EVE_ID")
    private int id;
    @Column(name="NAME")
    private String event;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
