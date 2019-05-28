package watch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstManagerTest {
    public static InstManager junitTest;
    InstManagerTest(){
    }

    @Test
    void getInstance() {
        junitTest.getInstance();
    }

    @Test
    void getAlarmIndex() throws Exception{
        try{
            junitTest.getAlarmIndex();
        }catch (Exception e){
            System.out.println("getAlarmIndex failed");
        }
    }

    @Test
    void getdDayIndex()  throws Exception{
        try{
            junitTest.getdDayIndex();
        }catch (Exception e){
            System.out.println("getdDayIndex failed");
        }
    }

    @Test
    void setAlarmIndex()  throws Exception{
        try{
            junitTest.setAlarmIndex(3);
        }catch (Exception e){
            System.out.println("setAlarmIndex failed");
        }
    }

    @Test
    void setdDayIndex()  throws Exception{
        try{
            junitTest.setdDayIndex(2);
        }catch (Exception e){
            System.out.println("setdDayIndex failed");
        }
    }

    @Test
    void getAlarmInstNum()  throws Exception{
        try{
            junitTest.getAlarmInstNum();
        }catch (Exception e){
            System.out.println("getAlarmInstNum failed");
        }
    }

    @Test
    void getdDayInstNum()  throws Exception{
        try{
            junitTest.getdDayInstNum();
        }catch (Exception e){
            System.out.println("getdDayInstNum failed");
        }
    }

    @Test
    void getTimekeeping()  throws Exception{
        try{
            junitTest.getTimekeeping();
        }catch (Exception e){
            System.out.println("getTimekeeping failed");
        }
    }

    @Test
    void getTimer()  throws Exception{
        try{
            junitTest.getTimer();
        }catch (Exception e){
            System.out.println("getTimer failed");
        }
    }

    @Test
    void getAlarm()  throws Exception{
        try{
            junitTest.getAlarm();
        }catch (Exception e){
            System.out.println("getAlarm failed");
        }
    }

    @Test
    void getStopwatch() throws Exception{
        try{
            junitTest.getStopwatch();
        }catch (Exception e){
            System.out.println("getStopwatch failed");
        }
    }

    @Test
    void getDday()  throws Exception{
        try{
            junitTest.getDday();
        }catch (Exception e){
            System.out.println("getDday failed");
        }
    }

    @Test
    void getFitness()  throws Exception{
        try{
            junitTest.getFitness();
        }catch (Exception e){
            System.out.println("getFitness failed");
        }
    }

    @Test
    void getSelectFunction()  throws Exception{
        try{
            junitTest.getSelectFunction();
        }catch (Exception e){
            System.out.println("getSelectFunction failed");
        }
    }

    @Test
    void deleteInst() throws Exception{
        try{
            junitTest.deleteInst("alarm");
        }catch (Exception e){
            System.out.println("deleteInst failed");
        }
    }

    @Test
    void createInst() throws Exception{
        try{
            junitTest.createInst("dDay");
        }catch (Exception e){
            System.out.println("createInst failed");
        }
    }
}