package watch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DdayTest {
    public static Dday junitTest;

    DdayTest() {
    }

    @BeforeAll
    public static void makeInstance(){
        junitTest = new Dday();
    }

    @Test
    void setDate() throws Exception {
        try{
            junitTest.setDate(2020, 3, 11);
        }catch(Exception e){
            System.out.println("error");
        }

    }

    @Test
    void showGoal() throws Exception {
        try {
            junitTest.showGoal("nextGoal");
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void calculateDday() throws Exception{
        try {
            junitTest.calculateDday();
        } catch (Exception var2) {
            System.out.println("error: 목록에 디데이가 없습니다(정상)");
        }
    }

    @Test
    void setDday() throws Exception {
        try {
            junitTest.setDday();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }



    @Test
    void getGoal() throws Exception {
        try {
            Assertions.assertEquals((Object)null, junitTest.getGoal());
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void getDayCount() throws Exception{
        try {
            junitTest.getDayCount();
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getGoalList() throws Exception {
        try {
            junitTest.getGoalList();
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void setDate1() throws Exception {
        try {
            junitTest.setDate(24);
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void setGoal()throws Exception {
        try {
            junitTest.setGoal("stop drinking");
        } catch (Exception var2) {
            System.out.println("error");
        }
    }

    @Test
    void setGoalList()throws Exception {
        String[] arr = new String[]{"happy", "smile"};

        try {
            junitTest.setGoalList(arr);
        } catch (Exception var3) {
            System.out.println("error");
        }
    }
}