package watch;

public class Timer extends Thread{
    private int hour, minute, second;
    private boolean is_stop;
    private boolean check;

    //버저 객체를 가져옴
    Buzzer buzzer = Buzzer.getInstance();

    public Timer(){
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
        this.is_stop = true;
    }

    public void run(){
        //여기는 run
        //checkTimer()은 countDown()안에 존재한다.
        countDown();
    }

    //usecase: stop_buzzer
    public Buzzer getBuzzer(){
        return buzzer;
    }

    //usecase:set_timer
    public void setTime(int hour, int minute, int second){
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    //usecase: cancel_timer
    public int getHour(){
        return this.hour;
    }
    //usecase: cancel_timer
    public int getMinute(){
        return this.minute;
    }
    //usecase: cancel_timer
    public int getSecond(){
        return this.second;
    }

    //usecase: count_down
    synchronized public void countDown() {
        outerLoop:
        while (true) {
            if(is_stop == false) {
                this.second--;
                if (this.second == 0 && this.minute != 0) {
                    this.second = 60;
                    this.minute--;
                } else if (this.second == 0 && this.minute == 0 && this.hour != 0) {
                    this.minute = 60;
                    this.hour--;
                } else if(this.second == 0 && this.minute == 0 && this.hour == 0){
                    check = checkTimer();
                    if (check == true) {
                        buzzer.setIs_stop(false);
                        buzzer.ringBuzzer();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
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
            System.out.println(hour + ":" +  minute +  ":" +  second);
        }

    }

    //usecase: count_down
    public boolean checkTimer(){
        if(this.hour==0 && this.minute==0 && this.second==0){
            return true;
        }
        else
            return false;
    }

    public void reset(){
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public boolean getIs_stop() {
        return is_stop;
    }

    public void setIs_stop(boolean is_stop) {
        this.is_stop = is_stop;
    }
}
