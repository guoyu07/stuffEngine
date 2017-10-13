package ru.technoserv.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="EVENT")
public class Action implements Serializable {

    public Action(){
        //по требованию hibernate требуется пустой конструктор
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

    @Override
    public String toString() {
        return "Action{" +
                "id=" + id +
                ", event='" + event + '\'' +
                '}';
    }
}
