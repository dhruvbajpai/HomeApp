package com.application.homeapp.firstaws.Entity;

/**
 * DTO for guess result response
 */
public class GuessResult {
    private boolean correct;
    private String correctAnswer;
    private String message;

    public GuessResult() {
    }

    public GuessResult(boolean correct, String correctAnswer, String message) {
        this.correct = correct;
        this.correctAnswer = correctAnswer;
        this.message = message;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
