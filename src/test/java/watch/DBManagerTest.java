package watch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest {
    public static DBManager junitTest;

    DBManagerTest(){
    }

    @BeforeAll
    public static void makeInstance(){
        junitTest = new DBManager();
    }

    @Test
    void selectFitness() throws Exception{
        try{
            junitTest.selectFitness("look", 0);
        }catch (Exception e){
            System.out.println("selectFitness failed");
        }
    }

    @Test
    void insertFitness() throws Exception {
        try{
            junitTest.insertFitness(3,11, 9,30,00, 100);
        }catch (Exception e){
            System.out.println("insertFitness failed");
        }
    }

    @Test
    void updateFitness() throws Exception{
        try {
            junitTest.updateFitness(9,30,00, 300);
        }catch (Exception e){
            System.out.println("updateFitness failed");
        }
    }

    @Test
    void deleteFitness() throws Exception {
        try{
            junitTest.deleteFitness();
        }catch (Exception e){
            System.out.println("deleteFitness failed");
        }
    }

    @Test
    void selectStopwatch() throws Exception {
        try{
            junitTest.selectStopwatch(0);
        }catch (Exception e){
            System.out.println("selectStopwatch failed");
        }

    }

    @Test
    void insertStopwatch() throws Exception {
        try{
            junitTest.insertStopwatch(9,30,00, 1);
        }catch (Exception e){
            System.out.println("insertStopwatch failed");
        }
    }

    @Test
    void deleteStopwatch() throws Exception {
        try{
            junitTest.deleteStopwatch();
        }catch (Exception e){
            System.out.println("deleteStopwatch failed");
        }
    }

    @Test
    void resetStopwatch() throws Exception {
        try{
            junitTest.resetStopwatch();
        }catch (Exception e){
            System.out.println("resetStopwatch failed");
        }
    }
}