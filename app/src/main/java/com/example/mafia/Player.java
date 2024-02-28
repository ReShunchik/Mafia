package com.example.mafia;
public class Player {
    private String name, role;
    private boolean isAlive;

    Player(String n) {                                                 //создание игрока
        setName(n);
        setRole("citizen");
        isAlive = true;
    }

    private void setName(String n) {
        name = n;
    }                      //установка имени игрока

    public void setRole(String r) {
        role = r;
    }                       //установка роли игрока
    public void setAlive(boolean isAlive){
        this.isAlive = isAlive;
    }

    public String getName() {
        return name;
    }                          //получение имени игрока

    public String getRole() {
        return role;
    }                          //получение роли игрока

    public boolean isALive() {
        return isAlive;
    }
}
