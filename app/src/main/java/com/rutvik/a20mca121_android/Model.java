package com.rutvik.a20mca121_android;

public class Model {
    String email;
    String pass;

    public Model() {
    }

    public Model(String id, String name, String dept) {
        this.email = id;
        this.pass = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
