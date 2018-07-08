package com.meetpatel.loginregister2;

public class Global {
    private static Global instance;

    private String strStartDate;
    private String strEndDate;

    private String selectedMail;

    private int startYear;
    private int endYear;
    private int startMonth;
    private int endMonth;
    private int startDay;
    private int endDay;

    private int presentYear;
    private int presentMonth;
    private int presentDay;

    public int getPresentDay() {
        return presentDay;
    }

    public int getPresentMonth() {
        return presentMonth;
    }

    public int getPresentYear() {
        return presentYear;
    }

    public void setPresentDay(int presentDay) {
        this.presentDay = presentDay;
    }

    public void setPresentMonth(int presentMonth) {
        this.presentMonth = presentMonth;
    }

    public void setPresentYear(int presentYear) {
        this.presentYear = presentYear;
    }

    public int getEndDay() {
        return endDay;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public int getEndYear() {
        return endYear;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public String getSelectedMail() {
        return selectedMail;
    }

    public void setSelectedMail(String selectedMail) {
        this.selectedMail = selectedMail;
    }

    public String getStrEndDate() {
        return strEndDate;
    }

    public String getStrStartDate() {
        return strStartDate;
    }

    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }

    public void setStrStartDate(String strStartDate) {
        this.strStartDate = strStartDate;
    }

    public static synchronized Global getInstance(){
        if(instance==null){
            instance=new Global();
        }
        return instance;
    }

    //Utility Method

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

    public String getMailFormat(){
        String strMailFormat = "";

        switch (selectedMail){
            case "Quandl":
                strMailFormat = "EOD/AAPL," + "Q," + String.valueOf(startYear) + "-" + String.valueOf(startMonth) + "-" + String.valueOf(startDay)
                + "," + String.valueOf(endYear) + "-" + String.valueOf(endMonth) + "-" + String.valueOf(endDay);
                break;
            case "Yahoo":
                strMailFormat = "AAPL," + "Y," + String.valueOf(startDay) + "-" + String.valueOf(startMonth) + "-" + String.valueOf(startYear)
                 + "," + String.valueOf(endDay) + "-" + String.valueOf(endMonth) + "-" + String.valueOf(endYear);
                break;
            case "Google":
                strMailFormat = "Google," + "G," + String.valueOf(startDay) + "-" + String.valueOf(startMonth) + "-" + String.valueOf(startYear)
                        + "," + String.valueOf(endDay) + "-" + String.valueOf(endMonth) + "-" + String.valueOf(endYear);
                break;
        }

        return strMailFormat;
    }
}
