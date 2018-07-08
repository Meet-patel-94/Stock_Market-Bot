package com.meetpatel.loginregister2;

public class Util {

    public String getMonthCharacter(int monthNumber){
        String monthCharacter = "";

        if (monthNumber == 1){
            monthCharacter = "Jan";
        }

        if (monthNumber == 2){
            monthCharacter = "Feb";
        }

        if (monthNumber == 3){
            monthCharacter = "Mar";
        }

        if (monthNumber == 4){
            monthCharacter = "Apr";
        }

        if (monthNumber == 5){
            monthCharacter = "May";
        }

        if (monthNumber == 6){
            monthCharacter = "Jun";
        }

        if (monthNumber == 7){
            monthCharacter = "Jul";
        }

        if (monthNumber == 8){
            monthCharacter = "Agu";
        }

        if (monthNumber == 9){
            monthCharacter = "Sep";
        }

        if (monthNumber == 10){
            monthCharacter = "Oct";
        }

        if (monthNumber == 11){
            monthCharacter = "Nov";
        }

        if (monthNumber == 12){
            monthCharacter = "Dec";
        }

        return monthCharacter;
    }
}
