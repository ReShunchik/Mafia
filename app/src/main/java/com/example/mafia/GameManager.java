package com.example.mafia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class GameManager {
    private static final ArrayList<Player> players = new ArrayList<>();                              // массив хранящий информацию об игроке
    private static HashSet<String> whoVoted = new HashSet<>();                                       // игроки на голосовании
    public static String[] roles = {"Мирный", "Мафия","Комиссар","Дон"};                             // все роли
    private static String killedPlayer = "никто";                                                    // последний убитый мафией игрок
    private static StringBuilder kickedPlayers = new StringBuilder("Выгнали: ");
    private static long speechTime;                                                                  // время на речь
    private static int NumberOfDay = 1;                                                              // день игры
    private static boolean isNewGame = false;


    public static void setPlayers(ArrayList<String> names){
        for(String name: names){
            Player player = new Player(name);
            players.add(player);
        }
    }                                                  // массив со всеми игроками
    public static void setRoles(ArrayList<String> mafias, String don, String comissar)
    {
        for(Player player: players)
        {
            for(String nameMaf: mafias)
            {
                if(nameMaf.equals(player.getName()))
                {
                    player.setRole("mafia");
                    break;
                }
            }
            if(don.equals(player.getName()))
            {
                player.setRole("don");
            }
            if(comissar.equals(player.getName()))
            {
                player.setRole("comissar");
            }
        }
    }                                                                                            // присваивание роли каждому игроку
    public static void setSpeechTime(long time){speechTime = time;}                                  // установление времени на речь

    public static void setWhoVoted(ArrayList<String> newVoted){
        HashSet<String> list = new HashSet<>(newVoted);
        whoVoted = list;
    }
    public static void setKilledPlayer(String name){killedPlayer = name;}                            // установка полсдеднего убитого игрока мафией

    public static boolean namesCheck(ArrayList<String> checkName){
        for(int i = 0; i < checkName.size()-1; i++)
            for(int j = i + 1; j < checkName.size(); j++)
                if(checkName.get(i).equals(checkName.get(j)))
                    return true;
        setPlayers(checkName);
        return false;

    }                                       // проверка на совпадение имён

    public static void killPlayer(String name){
        for(Player player: players)
        {
            if(name.equals(player.getName()))
            {
                player.setAlive(false);
            }
        }
        setKilledPlayer(name);
    }                                                  // смерть игрока
    public static void kickPlayer(String name){
        for(Player player: players){
            if(player.getName().equals(name)) {
                player.setAlive(false);
                kickedPlayers.append(name).append(" ");
                break;
            }
        }
        whoVoted.clear();
    }                                                  // выкидывание на голосовании
    public static void addWhoVoted(String name){
        whoVoted.add(name);
    }                                                 // создание списка игороков на голосование

    public static boolean isEnd(){
        int mafiasCount = getMafiasCount();
        return ((getPlayersCount() - mafiasCount) <= (mafiasCount + 1)) || mafiasCount == 0;
    }                                                               // проверка на конец игры
    public static String whoWin(){
        if(getMafiasCount() == 0)
            return "Мирных";
        return "Мафии";
    }                                                               // кто выйграл

    public static ArrayList<String> getPlayersNames(){
        ArrayList<String> names = new ArrayList<>();
        for(Player player: players){
            if(player.isALive())
                names.add(player.getName());
        }
        return names;

    }                                           // получение списка имён игроков
    public static String getMafiasNames(){
        StringBuilder names = new StringBuilder();
        for(Player player: players)
            if("don".equals(player.getRole()) || "mafia".equals(player.getRole()))
                names.append(player.getName()).append(", ");
        return names.toString();
    }                                                       // кто мафия
    public static String getDonName(){
        String name = "";
        for(Player player: players)
            if("don".equals(player.getRole())){
                name = player.getName();
            }
        return name;

    }                                                           // кто дон
    public static String getComissarName(){
        String name = "";
        for(Player player: players)
            if("comissar".equals(player.getRole())){
                name = player.getName();
            }
        return name;

    }                                                      // кто комиссар
    public static int getPlayersCount(){
        int count = 0;
        for(Player player: players)
            if(player.isALive())
                count++;
        return count;
    }                                                         // сколько игроков в игре осталось
    public static int getMafiasCount(){
        int count = 0;
        for(Player player: players)
            if(player.getRole().equals("mafia") || player.getRole().equals("don"))
                if(player.isALive())
                    count++;
        return count;
    }
    public static long getSpeechTime(){return speechTime;}                                           // сколько времени на речь
    public static String getKilledPlayer(){return killedPlayer;}                                     // получение убитого игрока
    public static StringBuilder getKickedPlayers(){
        if(kickedPlayers.length() == 9)
            kickedPlayers.append("никого");
        return kickedPlayers;
    }
    public static ArrayList<String> getAllVoted(){
        ArrayList<String> allVoted = new ArrayList<>(whoVoted);
        return allVoted;
    }
    public static int getNumberOfDay() {
        return NumberOfDay;
    }                                                         // какой день
    public static StringBuilder getInfo(){
        StringBuilder info = new StringBuilder();
        info.append("Мафии: ");
        info.append(getMafiasNames()).append('\n');
        info.append("Дон: " + getDonName()).append('\n');
        info.append("Комиссар: ").append(getComissarName());
        return info;
    }                                                       // для ночи(кто есть кто)

    public static void IncreaseNumberOfDay(){
        NumberOfDay++;
        kickedPlayers = new StringBuilder("Кикнуты:");
    }                                                    // новый день

    public static void resetGame(){
        setKilledPlayer("никто");
        kickedPlayers = new StringBuilder("Кикнуты:");
        NumberOfDay = 1;
        isNewGame = true;
    }
    public static ArrayList<String> oldPlayers(){
        ArrayList<String> oldNames = getPlayersNames();
        players.clear();
        return oldNames;
    }
    public static boolean getIsNewGame(){
        return isNewGame;
    }
}