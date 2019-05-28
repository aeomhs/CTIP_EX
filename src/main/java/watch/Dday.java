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
    private int number;
    private int dayCount;
    private int diffHour;
    private int diffMinute;
    private String status;
    private InstManager im;
    private int check =0;
    Timer timer;
    Timekeeping tk;

    public Dday()
    {
        //자바에서 제공하는 타이머. watch package의 Timer class 아님
        timer = new Timer();
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
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if(check==0)
                    timer.cancel();
                dayCount +=1;
                check++;
            }
        };
        while(check < 2) {
            if (check == 0)
                timer.scheduleAtFixedRate(task, 0, (diffHour * 60 + diffMinute) * 60 * 1000);
            else
                timer.scheduleAtFixedRate(task, 0, 24 * 60 * 60 * 1000);
        }

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
        dayCount = count;
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
