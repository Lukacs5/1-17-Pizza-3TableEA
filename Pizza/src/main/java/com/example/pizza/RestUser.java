package com.example.pizza;
/*
"id": 2573,
"name": "Leela Chaturvedi Ret.",
"email": "chaturvedi_ret_leela@considine.io",
"gender": "female",
"status": "active"
* */

public class RestUser {
    public int id;
    public String name;
    public String email;
    public String gender;
    public String status;

    public RestUser(int id, String name, String email, String gender, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id + ","+
                "\"name\":\"" + name + "\","+
                "\"email\":\"" + email + "\","+
                "\"gender\":\"" + gender + "\","+
                "\"status\":\"" + status + "\""+
                '}';
    }
}
