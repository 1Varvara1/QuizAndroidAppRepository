package com.example.quizapplication.JsonModels;

import java.util.ArrayList;

public class Questinarie {

    public ArrayList<Question> getQuestionnaires() {
        return questionnaires;
    }

    public Questinarie(ArrayList<Question> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public void setQuestionnaires(ArrayList<Question> questionnaires) {
        this.questionnaires = questionnaires;
    }

    ArrayList<Question> questionnaires;
}
