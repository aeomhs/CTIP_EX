package watch;

import java.sql.SQLException;
import java.util.function.DoubleBinaryOperator;

public class Fitness extends Thread implements CountUp{
    private int month;
    private int date;
    private int hour;
    private int minute;
    private int second;
    private int recentMonth;
    private int recentDate;
    private int CPM;
    private int totalCalories;
    //목록 개수
    private int count;
    private String exercise;
    private boolean is_exist;

    private boolean is_stop;

    DBManager dbManager;
    InstManager inst;
    private FitnessDTO fitDTO;

    public Fitness(){
        this.is_stop = false;
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
        this.totalCalories = 0;
        this.dbManager = new DBManager();
        this.fitDTO = FitnessDTO.getInstance();
    }

    //getter
    public int getMonth() {
        return month;
    }

    public int getDate() {
        return date;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getCPM() {
        return CPM;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public int getCount() {
        dbManager.selectFitness("look",1);
        count = fitDTO.getCount();
        return count;
    }

    public String getExercise() {
        return exercise;
    }

    public boolean isIs_exist() {
        return is_exist;
    }

    public boolean getIs_stop() {
        return is_stop;
    }

    //setter
    public void setMonth(int month) {
        this.month = month;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setCPM(String exercise) {
        this.CPM = fitDTO.getCPM(exercise);
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public void setIs_exist(boolean is_exist) {
        this.is_exist = is_exist;
    }

    public void setIs_stop(boolean is_stop) {
        this.is_stop = is_stop;
    }

    //method

    public void getRecentDate()
    {
        dbManager.selectFitness("finish",1);
        this.recentMonth = fitDTO.getMonth();
        this.recentDate= fitDTO.getDate();
    }

    public boolean checkDate(){
        Timekeeping timekeeping =inst.getTimekeeping();
        month = timekeeping.getMonth();
        date = timekeeping.getDate();
        if(this.recentMonth== month && this.recentDate == date)
            return true;

        else
            return false;

    }


    public void showFitnessList(int column){
        dbManager.selectFitness("look",column);
        month = fitDTO.getMonth();
        date = fitDTO.getDate();
        hour = fitDTO.getHour();
        minute =fitDTO.getMinute();
        second = fitDTO.getSecond();
        totalCalories= fitDTO.getTotalCalories();
    }

    @Override
    public void run() {
        countUp();
    }

    @Override
    synchronized public void countUp() {
        second = 0;
        minute = 0;
        hour =0;
        while(true) {
            if(is_stop == false) {
                second++;
                if (second == 60) {
                    second = 0;
                    minute++;
                    calcultateCalories();
                }
                if (minute == 60) {
                    minute = 0;
                    hour++;
                }
            }
            //is_stop == true
            else{
                synchronized (this) {
                    try {
                        //System.out.println("wait");
                        this.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void calcultateCalories(){
            this.totalCalories += this.CPM;
    }

    public void finish()
    {
        getRecentDate();

        if(checkDate() == true){
            if(count ==30)
            {
                dbManager.deleteFitness();
            }
            try {
                dbManager.insertFitness(month,date,hour,minute,second,totalCalories);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        else if(checkDate() == false){
            updateFitness(hour,minute,second,totalCalories);
        }

    }

    public void updateFitness(int hour, int minute, int second, int totalCal){
        dbManager.selectFitness("finish",1);//마지막 행의 데이터 가져오기
        int cal = fitDTO.getTotalCalories();
        cal+=totalCal;
        int h = fitDTO.getHour();
        h+=hour;
        int m = fitDTO.getMinute();
        m+=minute;
        int s = fitDTO.getSecond();
        s+=second;

        dbManager.updateFitness(h,m,s,cal);

    }

    public void deleteFitness(){
        dbManager.deleteFitness();
    }
    public void initFitness(){
        hour= 0;
        minute = 0;
        second = 0;
        totalCalories = 0;
        CPM = 0;
        is_stop = true;
        exercise = "";

    }



}
