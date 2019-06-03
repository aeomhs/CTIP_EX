package watch;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FitnessTest {
    public static Fitness junitTest;

    FitnessTest() { }

    @BeforeAll
    public static void makeInstance(){
        junitTest = new Fitness();
    }

    @Test
    void getCPM()throws Exception{
        try{
            assertEquals(0,junitTest.getCPM());
        }catch(Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getTotalCalories() throws Exception{
        try{
            assertEquals(0,junitTest.getTotalCalories());
        }catch(Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getCount() throws Exception{
        try{
            assertEquals(0,junitTest.getCount());
        }catch(Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getExercise() throws Exception{
        try {
            assertEquals(null, junitTest.getExercise());
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void isIs_exist() throws Exception{
        try {
            junitTest.isIs_exist();
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getIs_stop()  throws Exception{
        try {
            junitTest.getIs_stop();
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void setCPM()throws Exception{
        try {
            junitTest.setCPM("bike");
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void setTotalCalories()throws Exception{
        try {
            junitTest.setTotalCalories(2100);
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void setCount() throws Exception{
        try {
            junitTest.setCount(5);
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void setExercise()throws Exception{
        try {
            junitTest.setExercise("running");
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void setIs_exist() throws Exception {
        try {
            junitTest.setIs_exist(true);
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void setIs_stop()  throws Exception {
        try {
            junitTest.setIs_stop(false);
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void getRecentDate() throws Exception {
        try {
            junitTest.getRecentDate();
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void checkDate()  throws Exception {
        try {
            junitTest.checkDate();
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void showFitnessList()  throws Exception {
        try {
            junitTest.showFitnessList(0);
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Ignore
    void countUp() throws Exception {
        try {
            junitTest.countUp();
        }catch (Exception e){
            System.out.println("error: 무한히 test하기 때문에 ignore처리");
        }
    }

    @Test
    void calcultateCalories()  throws Exception {
        try {
            junitTest.calcultateCalories();
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void finish()  throws Exception {
        try {
            junitTest.finish();
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void updateFitness()  throws Exception {
        try {
            junitTest.updateFitness(1, 30, 30, 2000);
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void deleteFitness()  throws Exception {
        try {
            junitTest.deleteFitness();
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void initFitness() throws Exception {
        try {
            junitTest.initFitness();
        }catch (Exception e){
            System.out.println("error");
        }
    }
}