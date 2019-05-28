package watch;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimekeepingTest {
    public static Timekeeping junitTest;

    TimekeepingTest() {
    }

    @BeforeAll
    public static void makeInstance(){
        try {
            junitTest = new Timekeeping();
        } catch (Exception var1) {
            System.out.println("error");
        }
    }


    @Test
    void getDate() throws Exception {
        try {
            Assertions.assertEquals(26, junitTest.getDate());
        } catch (Exception var2) {
            System.out.println("error: expect값이 현재 '일'과 동일하지 않습니다");
        }
    }

    @Test
    void getDayNum()  throws Exception {
        try {
            Assertions.assertEquals(1, junitTest.getDayNum());
        } catch (Exception var2) {
            System.out.println("error: expect값이 현재 '요일'과 동일하지 않습니다.");
        }
    }

    @Test
    void getIs_stop() throws Exception{
        try{
            junitTest.getIs_stop();
        }catch (Exception var2){
            System.out.println("error");
        }
    }

    @Test
    void setIs_stop() throws Exception {
        try{
            junitTest.setIs_stop(false);
        }catch (Exception var2){
            System.out.println("error");
        }
    }

    @Ignore
    void countUp()  throws Exception {
        try {
            junitTest.countUp();
        } catch (Exception var2) {
            System.out.println("error: 테스트가 무한히 진행되므로 Ignore처리해줍니다");
        }
    }

    @Test
    void setDate() throws Exception {
        try {
            junitTest.setDate(2020, 3, 11);
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void setTime() throws Exception {
        try {
            junitTest.setTime(6, 30, 30);
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void calculateDay() throws Exception {
        try {
            junitTest.calculateDay(2019, 5, 26);
        } catch (Exception var2) {
            System.out.println("error");
        }
    }
}