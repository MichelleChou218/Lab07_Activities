package com.example.student.lab07_activities.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by student on 2016/4/12.
 */
@Root
public class Question {
    @Element
    private String description;
    @Element
    private String optionA;
    @Element
    private String optionB;
    @Element
    private String optionC;

    public Question(String question, String optionA, String optionB, String optionC) {
        this.description = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
    }

    public String getDescription() {
        return description;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }
}
