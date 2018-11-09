package com.buutcamp.database;

import com.buutcamp.main.Utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message_table")
public class MessageData {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="username")
    private String username;

    @Column(name="date")
    private String date;

    @Column(name="message")
    private String message;

    public MessageData(String username, String date, String message) {
        setUsername(username);
        this.date = date;
        setMessage(message);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MessageData(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        int max = Utils.getUsernameMaxSize();
        if(username.length() > max){
            this.username = username.substring(0, max);
        } else {
            this.username = username;
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        int max = Utils.getMessageMaxSize();
        if(message.length() > max){
            this.message = message.substring(0,max);
        } else {
            this.message = message;
        }
    }
}
