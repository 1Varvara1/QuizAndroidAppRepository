package com.example.quizapplication.JsonModels;

public class Questinarie {
    public Questinarie(Question[] questionnaires) {
        this.questionnaires = questionnaires;
    }
    public Questinarie() {

    }

    public Question[] getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(Question[] questionnaires) {
        this.questionnaires = questionnaires;
    }

    Question [] questionnaires;
}
