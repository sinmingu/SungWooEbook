package com.sungwoo.sungwooebook.Model;

public class UserModel {
    String uid;
    String id;
    String name;

    public UserModel() {

    }

    public UserModel(String uid, String id, String name) {
        this.uid = uid;
        this.id = id;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
