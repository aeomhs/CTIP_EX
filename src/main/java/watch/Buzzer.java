package watch;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Buzzer{
    public int time;
    public boolean is_stop;
    private static Buzzer buzzerInstance = new Buzzer();

    //다른클래스에서는 getInstance로 호출
    //Buzzer buzzer1 = Buzzer.getInstance(); 로 사용하면 된다
    public static Buzzer getInstance() {
        return buzzerInstance;
    }

    public Buzzer() {
        this.time = time;
    }

    public void beep(){
        File bgm;
        AudioInputStream stream;
        AudioFormat format;
        DataLine.Info info;

        bgm = new File("C:\\Users\\김소현\\Desktop\\3학년 1학기\\소프트웨어 모델링및분석/beep.wav");

        Clip clip;

        try {
            stream = AudioSystem.getAudioInputStream(bgm);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip)AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();

        } catch (Exception e) {
            System.out.println("err : " + e);
        }
    }

    public void ringBuzzer() {
        while(time<30){
            if(is_stop == false) {
                beep();
                this.time++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            //is_stop == true
            else{
                break;
            }
        }
    }

    public void stopBuzzer(){
        this.is_stop = true;
    }

    public boolean getIs_stop() {
        return is_stop;
    }

    public void setIs_stop(boolean is_stop) {
        this.is_stop = is_stop;
    }
}
