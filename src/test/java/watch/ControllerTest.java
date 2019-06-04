package watch;

import View.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    public static Controller junitTest;

    ControllerTest(){
    }

    @BeforeAll
     public static void makeInstance(){
        BaseView bv = new BaseView();
        bv.controller = new Controller(bv);
        Buzzer.getInstance().setBaseView(bv);
        bv.tkv = new TimeKeepingView(bv);
        bv.tmv = new TimersView(bv);
        bv.alarmView = new AlarmView(bv);
        bv.selectView = new SelectView(bv);
        bv.stopWatchView = new StopWatchView(bv);
        bv.fitnessView = new FitnessView(bv);
        bv.ddayView = new DdayView(bv);
        junitTest = new Controller(bv);
    }

    @Test
    void req_countUp() throws Exception {
        try{
            junitTest.req_countUp("stopwatch");
        }catch(Exception e){
            System.out.println("req_countUp failed");
        }
    }

    @Test
    void req_continue()  throws Exception {
        try{
            junitTest.req_continue("stopwatch");
        }catch(Exception e){
            System.out.println("req_continue failed");
        }
    }

    @Test
    void req_stopBuzzer()  throws Exception {
        try{
            junitTest.req_stopBuzzer("alarm");
        }catch(Exception e){
            System.out.println("req_stopBuzzer failed");
        }
    }

    @Test
    void req_changeMode()  throws Exception {
        try{
            junitTest.req_changeMode();
        }catch(Exception e){
            System.out.println("req_changeMode failed");
        }
    }

    @Test
    void req_lookFunc() throws Exception {
        try{
            junitTest.req_lookFunc();
        }catch(Exception e){
            System.out.println("req_lookFunc failed");
        }
    }

    @Test
    void req_funcList() throws Exception {
        try{
            junitTest.req_funcList();
        }catch(Exception e){
            System.out.println("req_funcList failed");
        }
    }

    @Test
    void req_selectFunc() throws Exception {
        try{
            junitTest.req_selectFunc(3);
        }catch(Exception e){
            System.out.println("req_selectFunc failed");
        }
    }

    @Test
    void req_finishSelect() throws Exception{
        try{
            assertEquals(true, junitTest.req_finishSelect());
        }catch (Exception e){
            System.out.println("req_finishSelect failed");
        }
    }

    @Test
    void req_setDate() throws Exception {
        try{
            junitTest.req_setDate("dDay", 2019, 5, 30);
        }catch (Exception e){
            System.out.println("req_setDate failed");
        }
    }

    @Test
    void req_setTime() throws Exception {
        try{
            junitTest.req_setTime("timer", 13, 5, 30);
        }catch (Exception e){
            System.out.println("req_setTime failed");
        }
    }

    @Test
    void req_countDown() throws Exception{
        try {
            junitTest.req_countDown();
        }catch(Exception e){
            System.out.println("req_counDown failed");
        }
    }

    @Test
    void req_pause() throws Exception {
        try {
            junitTest.req_pause("timer");
        }catch (Exception e){
            System.out.println("req_pause failed");
        }
    }

    @Test
    void req_reset() throws Exception {
        try {
            junitTest.req_reset();
        }catch (Exception e){
            System.out.println("req_reset failed");
        }
    }

    @Test
    void req_alarmList() throws Exception {
        try {
            junitTest.req_alarmList();
        }catch (Exception e){
            System.out.println("req_alarmList failed");
        }
    }

    @Test
    void req_setAlarm() throws Exception {
        try {
            junitTest.req_setAlarm();
        }catch (Exception e){
            System.out.println("req_setAlarm failed");
        }
    }

    @Test
    void req_setDate1()throws Exception {
        try {
            junitTest.req_setDate(null, 3, 5, 9, 30);
        }catch (Exception e){
            System.out.println("req_setDate1 failed");
        }
    }

    @Test
    void req_onOff() throws Exception {
        try {
            junitTest.req_onOff();
        }catch (Exception e){
            System.out.println("req_onoff failed");
        }
    }

    @Test
    void req_deleteAlarm()throws Exception {
        try {
            junitTest.req_deleteAlarm();
        }catch (Exception e){
            System.out.println("req_deleteAlarm failed");
        }
    }

    @Test
    void req_record() throws Exception {
        try {
            junitTest.req_record(1);
        }catch (Exception e){
            System.out.println("req_record failed");
        }
    }

    @Test
    void req_pause1() throws Exception {
        try {
            junitTest.req_pause();
        }catch (Exception e){
            System.out.println("req_pause1 failed");
        }
    }

    @Test
    void req_recordList() throws Exception {
        try {
            junitTest.req_recordList(1);
        }catch (Exception e){
            System.out.println("req_recordList failed");
        }
    }

    @Test
    void req_finish() throws Exception {
        try {
            junitTest.req_finish("fitness");
        }catch (Exception e){
            System.out.println("req_finish failed");
        }
    }

    @Test
    void getBaseView() throws Exception {
        try {
            junitTest.getBaseView();
        }catch (Exception e){
            System.out.println("getBaseView failed");
        }
    }

    @Test
    void req_selectDate() throws Exception {
        try {
            junitTest.req_selectDate();
        }catch (Exception e){
            System.out.println("req_selectDate failed");
        }
    }

    @Test
    void req_nextGoal() throws Exception {
        try {
            junitTest.req_nextGoal("nextGoal");
        }catch (Exception e){
            System.out.println("req_nextGoal failed");
        }
    }

    @Test
    void req_setGoal() throws Exception {
        try {
            junitTest.req_setGoal("stop smoking");
        }catch (Exception e){
            System.out.println("req_setGoal failed");
        }
    }

    @Test
    void req_DdayList()throws Exception {
        try {
            junitTest.req_DdayList();
        }catch (Exception e){
            System.out.println("req_DdayList failed");
        }
    }

    @Test
    void req_deleteDday() throws Exception {
        try {
            junitTest.req_deleteDday();
        }catch (Exception e){
            System.out.println("req_deleteDday failed");
        }
    }

    @Test
    void req_fitnessList() throws Exception {
        try {
            junitTest.req_fitnessList(1);
        }catch (Exception e){
            System.out.println("req_fitnessList failed");
        }
    }

    @Test
    void req_nextExercise() throws Exception {
        try {
            junitTest.req_nextExercise();
        }catch (Exception e){
            System.out.println("req_nextExercise failed");
        }
    }

    @Test
    void req_setExercise()throws Exception {
        try {
            junitTest.req_setExercise("running");
        }catch (Exception e){
            System.out.println("req_setExercise failed");
        }
    }

    @Test
    void getInstManager()  {
        junitTest.getInstManager();
    }
}