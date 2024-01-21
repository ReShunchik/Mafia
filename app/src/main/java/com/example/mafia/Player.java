package com.example.mafia;
public class Player {
    private String name, role;
    private int numberOfVote;
    Player(String n){                                                 //создание игрока
        setName(n);
        setRole("citizen");
    }

    private void setName(String n){
        name = n;
    }                      //установка имени игрока
    public void setRole(String r){
        role = r;
    }                       //установка роли игрока
    public void setNumberOfVote(int n){
        numberOfVote = n;
    }          //установка количество голосов за этого игрока
    public String getName(){
        return name;
    }                          //получение имени игрока
    public String getRole(){
        return role;
    }                          //получение роли игрока
}
