package com.example.mafia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class PlayerManager{
        private static ArrayList<Player> players = new ArrayList<>();                                 // массив хранящий информацию об игроке
        private static HashSet<String> whoVotednoDublicate = new HashSet<>();                         // игроки на голосовании
        private static  ArrayList<String> whoVoted;
        public static String[] roles = {"Мирный", "Мафия","Комиссар","Дон"};                          // все роли
        private static String killedPlayer = "никто";                                                 // последний убитый мафией игрок
        private static long speechTime;                                                               // время на речь
        private static int NumberOfDay = 1;                                                           // день игры
        private static int maxNumberOfVote = 0;                                                          // максимальный голос
        private static byte mafiasCount = 0;                                                          // число мафий



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
        public static void setVote(String name, int vote){
            if(vote > maxNumberOfVote)
                setMaxNumberOfVote(vote);
            for(Player player: players)
            {
                if(name.equals(player.getName()))
                    player.setNumberOfVote(vote);
            }
        }                                        // установка голоса за игрока
        public static void setMaxNumberOfVote(int number){maxNumberOfVote = number;}                        // устнановка максимального голоса
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
        public static boolean checkRightNames(String namesForCheck){
            String[] names = namesForCheck.split(",");
            for(String name: names)
                if(getPlayersNames().indexOf(name) == -1)
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
        public static void kickPlayer(){
        Iterator<Player> iterator = players.iterator();
        Player player;
        while(iterator.hasNext())
        {
            player = iterator.next();
            if(player.getNumberOfVote() == maxNumberOfVote)
            {
                if(player.getRole().equals("mafia") || player.getRole().equals("don"))
                    mafiasCount--;
                iterator.remove();
            }
            else
                player.setNumberOfVote(0);
        }
        whoVotednoDublicate.clear();
        setMaxNumberOfVote(0);
    }                                                         // выкидывание на голосовании

        public static void addWhoVoted(String name){
            whoVotednoDublicate.add(name);
        }                                              // создание списка игороков на голосование

        public static boolean isEnd(){
            if(((getPlayersCount() - mafiasCount) <= (mafiasCount+1)) || mafiasCount == 0)
                return true;
            return false;
        }                                                            // проверка на конец игры
        public static String whoWin(){
            if(mafiasCount == 0)
                return "Мирных";
            return "Мафии";
        }                                                            // кто выйграл

        public static ArrayList<String> getPlayersNames(){
            ArrayList<String> names = new ArrayList<String>();
            for(Player player: players)
                names.add(player.getName());
            return names;

        }                                        // получение списка имён игроков
        public static String getMafiasNames(){
            String names = "";
            for(Player player: players)
                if("don".equals(player.getRole()) || "mafia".equals(player.getRole()))
                    names += (player.getName() + ", ");
            return names;

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
        public static byte getPlayersCount(){
            return (byte)players.size();
        }                                                     // сколько игроков в игре осталось
        public static long getSpeechTime(){return speechTime;}                                        // сколько времени на речь
        public static String getKilledPlayer(){return killedPlayer;}                                  // получение убитого игрока
        public static String[] getRoles(){return roles;}                                              // список всех ролей в игре
        public static String getWhoVoted(int position){
            return whoVoted.get(position);
        }                                           // список всех игроков на голосовании
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
            setToArray();
        }                                                 // новый день
       private static void setToArray(){
            whoVoted = new ArrayList<>(whoVotednoDublicate);
       }

    }

