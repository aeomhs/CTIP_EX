package watch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//실행하면 버저30초동안 울려서 30초동안 기다린 후 pass확인을 할 수 있다.
class BuzzerTest {
    public static Buzzer junitTest;

    BuzzerTest() {
    }

    @BeforeAll
    public static void makeInstance(){
        junitTest = new Buzzer();
    }

    @Test
    void getInstance() throws Exception {
        try {
            Buzzer var10000 = junitTest;
            Buzzer.getInstance();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void beep() throws Exception {
        try {
            junitTest.beep();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void ringBuzzer()throws Exception  {
        try {
            junitTest.ringBuzzer();
            System.out.println("30초동안 ring");
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void stopBuzzer() throws Exception {
        try {
            junitTest.ringBuzzer();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void getIs_stop() throws Exception {
        try {
           assertEquals(false, junitTest.getIs_stop());
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void setIs_stop() throws Exception {
        try {
            junitTest.setIs_stop(true);
        } catch (Exception var2) {
            System.out.println("error");
        }
    }
}