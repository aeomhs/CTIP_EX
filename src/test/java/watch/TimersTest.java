package watch;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimersTest {
    public static Timers junitTest;

    TimersTest() {
    }

    @BeforeAll
    public static void makeInstance(){
        junitTest = new Timers();
    }


    @Test
    void getBuzzer()  throws Exception {
        try {
            junitTest.getBuzzer();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void setTime()  throws Exception {
        try {
            junitTest.setTime(2, 10, 30);
        } catch (Exception var2) {
            System.out.println("error");
        }
    }


    @Ignore
    void countDown() throws Exception {
        try {
            junitTest.countDown();
        } catch (Exception var2) {
            System.out.println("error: 무한하게 test하기때문에 ignore처리");
        }
    }

    @Test
    void checkTimer() throws Exception {
        try {
            junitTest.checkTimer();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void reset() throws Exception {
        try {
            junitTest.reset();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void getIs_stop() throws Exception{
        try{
            assertEquals(false, junitTest.getIs_stop());
        }catch (Exception var2){
            System.out.println("error");
        }
    }

    @Test
    void setIs_stop() throws Exception{
        try {
            junitTest.setIs_stop(true);
        }
        catch (Exception var2){
            System.out.println("error");
        }
    }
}