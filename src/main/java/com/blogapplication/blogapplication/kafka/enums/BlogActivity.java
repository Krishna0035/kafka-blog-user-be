package com.blogapplication.blogapplication.kafka.enums;

public enum BlogActivity {

    CREATE("create"),
    VIEW("view"),
    LIKE("like");

    private String value;

    private BlogActivity(String value){
        this.value=value;
    }

    public String getValue(){
        return this.value;
    }
}
