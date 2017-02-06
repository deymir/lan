package com.example.boss.lan;

public class User {
    public String letter;
    public boolean checked;

    public User(){
        this.letter = "";
        this.checked = false;
    }

    public User(String letter, Boolean checked) {
        this.letter = letter;
        this.checked = checked;
    }
}
