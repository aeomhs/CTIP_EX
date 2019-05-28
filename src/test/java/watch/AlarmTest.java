package watch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlarmTest {
    public static Alarm junitTest;

    AlarmTest() {
    }

    @BeforeAll
    public static void makeInstance() {
        junitTest = new Alarm();
    }

    @Test
    void getBuzzer() throws Exception {
        try {
            junitTest.getBuzzer();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void getDayList() throws Exception {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};

        try {
            Assertions.assertArrayEquals(arr, junitTest.getDayList());
        } catch (Exception var3) {
            System.out.println("error");
        }
    }

    @Test
    void getCheckDayList() throws Exception{
        try {
            junitTest.getCheckDayList(1);
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void getDayListNum() throws Exception{
        try{
            junitTest.getDayListNum();
        }catch (Exception var2){
            System.out.println("error");
        }
    }

    @Test
    void getCycle()throws Exception {
        try {
            junitTest.getCycle();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void setDay() throws Exception {
        try {
            junitTest.setDay(1);
        } catch (Exception var3) {
            System.out.println("error");
        }
    }

    @Test
    void setCycle() throws Exception {
        try {
            junitTest.setCycle(5);
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void onOffAlarm() throws Exception {
        try {
            junitTest.OnOffAlarm();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void checkAlarm()throws Exception {
        try {
            junitTest.checkAlarm();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }
}