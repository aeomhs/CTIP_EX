package watch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class Alarm extends Thread {
    private int hour, minute, cycle;
    private boolean status = true;
    private ArrayList<Integer> checkDayList;
    private int dayListNum;
    private int[] dayList = {1, 2, 3, 4, 5, 6, 7};  //순서대로 일월화수목금토
    private InstManager inst;
    private Timekeeping time;
    private Timer tm;
    private int cycleCount;
    private boolean is_delete = false;
    TimerTask tt;
    Buzzer buzzer;

    Calendar cal = Calendar.getInstance();

    public Alarm(){
        inst = InstManager.getInstance();
        time = inst.getTimekeeping();
        checkDayList = new ArrayList<>(7);
        dayListNum = checkDayList.size();
        buzzer = Buzzer.getInstance();
    }


    public void run() {
        checkAlarm();
    }

    public Buzzer getBuzzer() {
        return buzzer;
    }

    //요일 배열을 가져온다
    public int[] getDayList() {
        return dayList;
    }

    //가져온 day배열중에 설정된 요일의 넘버만 리해준다
    public int getCheckDayList(int index) {
        return this.checkDayList.get(index);
    }

    public int getDayListNum() {
        dayListNum = checkDayList.size();
        return dayListNum;
    }

    public int getCycle() {
        return this.cycle;
    }

    public int getHour() {
        return this.hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public boolean getStatus() {
        return status;
    }

    public ArrayList<Integer> getCheckDayList() {
        return checkDayList;
    }

    public void setDay(int day) {
        this.checkDayList.add(day);
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setIs_delete(boolean is_delete) {
        this.is_delete = is_delete;
    }

    //usecase: onoff_alarm  그리고 이부분 보고서랑 다이어그램 고치기.
    public void OnOffAlarm() {
        if(status == true) {
            status = false;
            System.out.println("Off status:"+status);
        }
        //status == false
        else {
            status = true;
            System.out.println("On status :"+status);
           /* synchronized (this) {
                try {
                    status = true;
                   // notify();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            */

        }
    }

    synchronized public void checkAlarm() {


        while (is_delete == false) {
            //(Alarm off)
            System.out.println("check alarm!!");
            try{
                sleep(1000);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            if(status == true) {
                /*
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                */
                System.out.println("status ==true!!");
                if (this.hour == time.getHour() && this.minute == time.getMinute()) {
                    System.out.println("시간이 똑같다!!");
                    for (int i = 0; i < checkDayList.size(); i++) {
                        if (this.checkDayList.get(i) == time.getDayNum()) {
                            System.out.println("요일도 똑같다!!");
                            System.out.println("알람요일 : "+this.checkDayList.get(i)+"   시계 요일 : "+ time.getDayNum());
                            if(cycle !=0 ) {
                                tm = new Timer();
                                tt = new TimerTask() {
                                    @Override
                                    public void run() {

                                        //int i;
                                        //for(i=0; i<3; i++){
                                        // buzzer.setIs_stop(false);
                                        //buzzer.ringBuzzer();
                                        //}
                                        if (cycleCount == 3 || buzzer.getIs_stop() == true) {
                                            tm.cancel();
                                            tm.purge();
                                            cycleCount = 0;
                                            status = true;
                                            //return;
                                        } else {
                                            cycleCount++;
                                            System.out.println("cycle : "+cycle);
                                            buzzer.setIs_stop(false);
                                            buzzer.ringBuzzer();
                                            status = false;
                                        }
                                    }
                                };

                                tm.scheduleAtFixedRate(tt, 0, cycle * 60 * 1000);
                            }
                            else if(cycle==0) {
                                buzzer.setIs_stop(false);
                                buzzer.ringBuzzer();
                            }
                        }

                    }
                }
            }
            //status == false (Alarm off)
            /*else{
                synchronized (this) {
                    try {

                        System.out.println("wait");
                        this.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }*/
        }
    }
}