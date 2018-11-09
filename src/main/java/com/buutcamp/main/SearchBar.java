package com.buutcamp.main;


public class SearchBar {

    private String searchValue;
    private boolean username;
    private boolean date;
    private boolean message;

    public SearchBar(){}

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public boolean isUsername() {
        return username;
    }

    public void setUsername(boolean username) {
        this.username = username;
    }

    public boolean isDate() {
        return date;
    }

    public void setDate(boolean date) {
        this.date = date;
    }

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SearchBar{" +
                "searchValue='" + searchValue + '\'' +
                ", username=" + username +
                ", date=" + date +
                ", message=" + message +
                '}';
    }
}
