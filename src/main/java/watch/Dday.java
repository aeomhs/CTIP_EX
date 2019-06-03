package watch;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Dday extends Thread{
    private int year;
    private int month;
    private int date;
    private String goal;
    private String[] goalList ={"stop drinking","stop smoking","studying","exercising","diet","save money"};
    private int index=0;
    private int dayCount = 0;
    private int diffHour;
    private int diffMinute;
    private InstManager im;
    Timer timer;
    Timekeeping tk;
    TimerTask task;

    public Dday()
    {
        //자바에서 제공하는 타이머. watch package의 Timer class 아님
        im = InstManager.getInstance();
        tk = im.getTimekeeping();
    }

    @Override
    public void run() {
        calculateDday();
    }

    public void setDate(int year, int month, int date){
        this.year = year;
        this.month = month;
        this.date = date;

    }

    public String showGoal(String status){
        if(status.equals("nextGoal"))
            index++;
        else //Down
        {
            index--;
            if ((index < 0)) {
                index = index + 6;
            }
        }
        String goalName = goalList[index%6];
        return goalName;
    }

    public void calculateDday()
    {
        task = new TimerTask() {
            @Override
            public void run() {
                dayCount +=1;
                System.out.println("TImer 발동!");
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(task, (diffHour * 60 + diffMinute) * 60 * 1000, 24 * 60 * 60 * 1000);
        //timer.scheduleAtFixedRate(task, 1000*60, 1000*60*2);
/*
        while(check < 2) {
            if (check == 0) {
                task = new TimerTask() {
                    @Override
                    public void run() {
                        if(check==0) {
                            timer.cancel();
                            timer.purge();
                        }
                        dayCount +=1;
                        check++;
                    }
                };
                timer = new Timer();
                //timer.scheduleAtFixedRate(task, 0, (diffHour * 60 + diffMinute) * 60 * 1000);
                timer.scheduleAtFixedRate(task, 0, 1000*60);
            }
            else {
                task = new TimerTask() {
                    @Override
                    public void run() {
                       /* if(check==1) {
                            timer.cancel();
                            timer.purge();
                        }
                           check++;


                        dayCount +=1;
                       //check++;
                    }
                };
                timer = new Timer();
                //timer.scheduleAtFixedRate(task, 0, 24 * 60 * 60 * 1000);
                timer.scheduleAtFixedRate(task, 0, 1000*60*2);
            }
        }
        */

    }

    public void setDday()
    {

        int currYear = tk.getYear();
        int currMonth = tk.getMonth();
        int currDate = tk.getDate();

        int currHour =tk.getHour();
        int currMinute = tk.getMinute();
        diffHour = 24-currHour;
        diffMinute = 60 - currMinute;

        Calendar cal = Calendar.getInstance();
        cal.set(currYear, currMonth, currDate);//오늘

        Calendar cal2 = Calendar.getInstance();
        cal2.set(this.year, this.month, this.date);//설정일

        int count = 0;
        while (!cal2.after(cal)) {
            count++;
            cal2.add(Calendar.DATE, 1);
        }
        dayCount = count -1;

        System.out.println("dayCOunt:"+dayCount);
    }



    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }


    public String getGoal() {
        return goal;
    }

    public int getDayCount() {
        return dayCount;
    }

    public String[] getGoalList() {
        return goalList;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public void setMonth(int month) {
        this.month = month;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public void setGoalList(String[] goalList) {
        this.goalList = goalList;
    }
}
