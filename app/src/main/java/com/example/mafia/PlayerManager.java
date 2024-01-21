package com.example.mafia;
import android.telephony.CellSignalStrength;

import java.util.ArrayList;


    public class PlayerManager{
        private static byte playersCount, mafiasCount;                                                // число игроков и мафий
        private static byte speechTime;                                                               // время на речь
        private static String killedPlayer = "никто";
        private static ArrayList<Player> players = new ArrayList<>();                                 // массив хранящий информацию об игроке
        private static ArrayList<String> whoVoted = new ArrayList<>();                                // игроки на голосовании
        private static String[] roles = {"Мирный", "Мафия","Дон","Комиссар"};

        public static void setPlayers(String str){
            String[] names = str.split(",");
            for(String name: names){
                Player player = new Player(name);
                players.add(player);
            }
        }                                            // массив со всеми игроками
        public static void setRoles(String[] mafias, String don, String comissar){
            for(Player player: players){
                for(String nameMaf: mafias){
                    if(nameMaf.equals(player.getName())){
                        if(nameMaf.equals(don)) {
                            player.setRole("don");
                        }
                        else
                            player.setRole("mafia");
                        break;
                    }
                }
                if(comissar.equals(player.getName())){
                    player.setRole("comissar");
                }
            }
        }                // присваивание роли каждому игроку
        public static void setPlayersCount(byte count){
            playersCount = count;
            mafiasCount = (byte) (playersCount / 3);
        }                         // установление кол-ва игроков
        public static void setSpeechTime(byte time){speechTime = time;}                               // установление времени на речь
        public static boolean CheckSameName(String[] names){
            for(int i = 0; i < names.length-1; i++)
                for(int j = i+1; j < names.length; j++)
                    if(names[i].equals(names[j]))
                        return true;
            return false;
        }                                      // проверка на совпадение имён
        public static boolean checkRightNames(String namesForCheck){
            String[] names = namesForCheck.split(",");
            boolean isOk = true;
            for(String name: names){
                if(getPlayersNames().indexOf(name) == -1)
                    isOk = false;
                else
                    isOk = true;
            }
            return isOk;
        }

        public static void killPlayer(String name){
            for(Player player: players)
                if(name.equals(player.getName())) {
                    players.remove(player);
                    setKilledPlayer(name);
                }
        }                                    // удаление игрока
        public static void setKilledPlayer(String name){killedPlayer = name;}
        public static void addWhoVoted(String name){
            whoVoted.add(name);
        }
        public static boolean isContinue(){
            if(((playersCount - mafiasCount) <= (mafiasCount+1)) || mafiasCount == 0)
                return false;
            return true;
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

        }                                        // список имён игроков
        public static String getMafiasNames(){
            String names = "";
            for(Player player: players)
                if("don".equals(player.getRole()) || "mafia".equals(player.getRole()))
                    names += (player.getName() + ", ");
            return names;

        }                                         // список имён мафий
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
        public static byte getPlayersCount(){return playersCount;}                                    // получение кол-ва игрокв
        public static byte getSpeechTime(){return speechTime;}                                        // сколько времени на речь
        public static String getKilledPlayer(){return killedPlayer;}                                  // получение убитого игрока
        public static String[] getRoles(){return roles;}
        public static String getWhoVoted(int position){
            return whoVoted.get(position);
        }
        public static int getCountVoted(){
            return whoVoted.size();
        }

        public static String getRole(int position){return (players.get(position)).getRole();}
        public static String getInfo(){
            String info = "Мафия: ";
            info += (getMafiasNames() + '\n');
            info +=("Дон: " + getDonName() + '\n');
            info += ("Комиссар: " + getComissarName());
            return info;
        }
    }

