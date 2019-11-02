package com.translatory;

public class Token {

    private String type;
    private int index;
    private String value;

    public Token(String t, int i, String v){
        this.type =t;
        this.index =i;
        this.value =v;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
