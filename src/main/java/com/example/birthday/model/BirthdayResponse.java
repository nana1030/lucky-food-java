package com.example.birthday.model;

public class BirthdayResponse {

    private boolean success;
    private String message;
    private int age;
    private String nextBirthday;
    private String[] kanshi;

    public BirthdayResponse(boolean success, String message, int age,
                             String nextBirthday, String[] kanshi) {
        this.success      = success;
        this.message      = message;
        this.age          = age;
        this.nextBirthday = nextBirthday;
        this.kanshi       = kanshi;
    }

    public boolean isSuccess()      { return success; }
    public String getMessage()      { return message; }
    public int getAge()             { return age; }
    public String getNextBirthday() { return nextBirthday; }
    public String[] getKanshi()     { return kanshi; }
}