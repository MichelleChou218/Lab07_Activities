package com.example.student.lab07_activities.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by student on 2016/4/19.
 */

@Root
public class QuestionList {
    @ElementList
    private List<Question> list;

    public List<Question> getList() {
        return list;
    }
}
