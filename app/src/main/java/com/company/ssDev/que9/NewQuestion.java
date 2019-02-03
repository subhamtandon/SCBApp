package com.company.ssDev.que9;

public class NewQuestion {
    String chapter;
    Explanation explanation;
    OptionA optionA;
    OptionB optionB;
    OptionC optionC;
    OptionD optionD;
    Question question;
    String set;

    public NewQuestion() {
    }

    public NewQuestion(String chapter, Explanation explanation, OptionA optionA, OptionB optionB, OptionC optionC, OptionD optionD, Question question, String set) {
        this.chapter = chapter;
        this.explanation = explanation;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.question = question;
        this.set = set;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public Explanation getExplanation() {
        return explanation;
    }

    public void setExplanation(Explanation explanation) {
        this.explanation = explanation;
    }

    public OptionA getOptionA() {
        return optionA;
    }

    public void setOptionA(OptionA optionA) {
        this.optionA = optionA;
    }

    public OptionB getOptionB() {
        return optionB;
    }

    public void setOptionB(OptionB optionB) {
        this.optionB = optionB;
    }

    public OptionC getOptionC() {
        return optionC;
    }

    public void setOptionC(OptionC optionC) {
        this.optionC = optionC;
    }

    public OptionD getOptionD() {
        return optionD;
    }

    public void setOptionD(OptionD optionD) {
        this.optionD = optionD;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }
}
