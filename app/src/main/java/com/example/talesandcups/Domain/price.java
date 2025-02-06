package com.example.talesandcups.Domain;

public class price {
    private int Id;
    private String Value;

    public price() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @Override
    public String toString() {
        return Value;
    }
}
