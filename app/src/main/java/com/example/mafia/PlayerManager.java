package com.example.mafia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;


public class PlayerManager{
        private static final ArrayList<Player> players = new ArrayList<>();                           // массив хранящий информацию об игроке
        private static final HashSet<String> whoVotednoDublicate = new HashSet<>();                   // игроки на голосовании
        private static  ArrayList<String> whoVoted;
        public static String[] roles = {"Мирный", "Мафия","Комиссар","Дон"};                          // все роли
        private static String killedPlayer = "никто";                                                 // последний убитый мафией игрок
        private static long speechTime;                                                               // время на речь
        private static int NumberOfDay = 1;                                                           // день игры

        private static int mafiasCount = 0;                                                          // число мафий



        public static void addPlayers(String name){
            Player player = new Player(name);
            players.add(player);
        }                                                // массив со всеми игроками
        public static void setRoles(String[] mafias, String don, String comissar)
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
            for(Player player: players)
                if(player.getRole().equals("mafia") || player.getRole().equals("don"))
                    mafiasCount++;
        }                                                                                         // присваивание роли каждому игроку
        public static void setSpeechTime(long time){speechTime = time;}                               // установление времени на речь

        public static void setKilledPlayer(String name){killedPlayer = name;}                         // установка полсдеднего убитого игрока мафией

        public static boolean checkEmptyName(String checkName){
            return checkName.equals("");
        }
        public static boolean CheckSameName(String checkName){
            for(Player player: players)
                if(checkName.equals(player.getName()))
                    return true;
            return false;

        }                                      // проверка на совпадение имён
        public static boolean checkRightNames(String[] namesForCheck){
            for(String name: namesForCheck)
                if(!getPlayersNames().contains(name))
                    return false;
            return true;
        }                              // если ли такое имя в списке игроков

        public static void killPlayer(String name){
            Iterator<Player> iterator = players.iterator();
            Player player;
            while(iterator.hasNext())
            {
                player = iterator.next();
                if(name.equals(player.getName()))
                {
                    if(player.getRole().equals("mafia") || player.getRole().equals("don"))
                        mafiasCount--;
                    iterator.remove();
                }
            }
            setKilledPlayer(name);
        }                                               // смерть игрока
        public static void kickPlayer(String name){
            Iterator<Player> iterator = players.iterator();
            Player player;
            while(iterator.hasNext()){
                player = iterator.next();
                if(player.getName().equals(name)) {
                    iterator.remove();
                    return;
                }
            }
            whoVotednoDublicate.clear();
    }                                                         // выкидывание на голосовании

        public static void addWhoVoted(String name){
            whoVotednoDublicate.add(name);
        }                                              // создание списка игороков на голосование

        public static boolean isEnd(){
            return ((getPlayersCount() - mafiasCount) <= (mafiasCount + 1)) || mafiasCount == 0;
        }                                                            // проверка на конец игры
        public static String whoWin(){
            if(mafiasCount == 0)
                return "Мирных";
            return "Мафии";
        }                                                            // кто выйграл

        public static ArrayList<String> getPlayersNames(){
            ArrayList<String> names = new ArrayList<>();
            for(Player player: players)
                names.add(player.getName());
            return names;

        }                                        // получение списка имён игроков
        public static String getMafiasNames(){
            StringBuilder names = new StringBuilder();
            for(Player player: players)
                if("don".equals(player.getRole()) || "mafia".equals(player.getRole()))
                    names.append(player.getName()).append(", ");
            return names.toString();

        }                                                    // кто мафия
        public static String getDonName(){
            String name = "";
            for(Player player: players)
                if("don".equals(player.getRole())){
                    name = player.getName();
                }
            return name;

        }                                                        // кто дон
        public static String getComissarName(){
            String name = "";
            for(Player player: players)
                if("comissar".equals(player.getRole())){
                    name = player.getName();
                }
            return name;

        }                                                   // кто комиссар
        public static int getPlayersCount(){
            return players.size();
        }                                                     // сколько игроков в игре осталось
        public static long getSpeechTime(){return speechTime;}                                        // сколько времени на речь
        public static String getKilledPlayer(){return killedPlayer;}                                  // получение убитого игрока
        public static String[] getRoles(){return roles;}                                              // список всех ролей в игре
        public static String getOneVoted(int position){
            return whoVoted.get(position);
        }                                           // список всех игроков на голосовании
        public static ArrayList<String> getAllVoted(){
            return whoVoted;
        }
        public static int getCountVoted(){
            return whoVoted.size();
        }                                                        // сколько игроков на голосовании
        public static int getNumberOfDay() {
            return NumberOfDay;
        }                                                      // какой день
        public static String getInfo(){
            String info = "Мафии: ";
            info += (getMafiasNames() + '\n');
            info +=("Дон: " + getDonName() + '\n');
            info += ("Комиссар: " + getComissarName());
            return info;
        }                                                           // для ночи(кто есть кто)

        public static void IncreaseNumberOfDay(){
            NumberOfDay++;
            listToArray((List<String>) whoVotednoDublicate);
        }                                                 // новый день
        public static void listToArray(List<String> voted){
            whoVoted = new ArrayList<>(voted);
       }
    }

