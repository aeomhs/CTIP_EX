package watch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlarmTest {
    public static Alarm junitTest;

    AlarmTest() {
    }

    @BeforeAll
    public static void makeInstance() {
        junitTest = new Alarm();
        junitTest.setIs_delete(false);
        try {
            Assertions.assertNotNull(junitTest);
        } catch (Exception var2) {
            System.out.println("error");
        }
    }




    @Test
    void getBuzzer() throws Exception {
        try {
            Assertions.assertNotNull(junitTest.getBuzzer());
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    /*
    @Test
    void getDayList() throws Exception {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        try {
            Assertions.assertArrayEquals(arr, junitTest.getDayList());
        } catch (Exception var3) {
            System.out.println("error");
        }
    }
*/
    @Test
    void getCheckDayList() throws Exception{
        try {
            junitTest.setDay(1);
            Assertions.assertEquals(1,junitTest.getCheckDayList(0));
            Assertions.assertEquals(1,junitTest.getDayListNum());

        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    /*
    @Test
    void getDayListNum() throws Exception{
        try{
            junitTest.getDayListNum();
        }catch (Exception var2){
            System.out.println("error");
        }
    }
*/
    @Test
    void getCycle()throws Exception {
        try {
            junitTest.setCycle(0);
            Assertions.assertEquals(0,junitTest.getCycle());
        } catch (Exception var2) {
            System.out.println("error");
        }
    }
    @Test
    void getHour() throws Exception{
        try {
            junitTest.setHour(11);
            Assertions.assertEquals(11, junitTest.getHour());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    @Test
    void getMinute() throws Exception{
        try {
            junitTest.setMinute(11);
            Assertions.assertEquals(11, junitTest.getMinute());
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    void getStatus() throws Exception{
        try {
            junitTest.setStatus(true);
            Assertions.assertEquals(true, junitTest.getStatus());
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    void onOffAlarm() throws Exception {
        try {
            junitTest.OnOffAlarm();
            Assertions.assertEquals(false, junitTest.getStatus() );
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Disabled
    void checkAlarm()throws Exception {
        try {

            junitTest.checkAlarm();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }
}