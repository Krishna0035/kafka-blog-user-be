package com.blogapplication.blogapplication.kafka.enums;

public enum UserActivity {

    REGISTER("register"),
    LOGIN("login"),

    CREATE_BLOG("create-blog"),
    VIEW_BLOG("view-blog"),
    LIKE_BLOG("like-blog");

    private String value;

    private UserActivity(String value){
        this.value=value;
    }

    public String getValue(){
        return this.value;
    }
}
