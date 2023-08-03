package com.blogapplication.blogapplication.email;

import java.util.List;

public class EmailSender {

    private int noOfTopics = 5;
    public void sentEmail(List<String> emailIds, String message){

        int seg = emailIds.size()/noOfTopics;
        int i=0;
        while (i<seg){
            List<String> strings = emailIds.subList(i, i+seg);
            i+=seg;
        }


    }
}
