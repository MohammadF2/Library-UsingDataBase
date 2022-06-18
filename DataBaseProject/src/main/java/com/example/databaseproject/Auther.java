package com.example.databaseproject;

public class Auther {
    private String name;
    private String Address;

    public Auther(String name, String Address) {
        this.name = name;
        this.Address = Address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    @Override
    public String toString() {
        return "Auther [name=" + name + ", Address=" + Address + "]";
    }
}
