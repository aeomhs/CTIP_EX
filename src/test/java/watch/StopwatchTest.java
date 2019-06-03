package watch;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StopwatchTest {
    public static Stopwatch junitTest;

    StopwatchTest(){
    }

    @BeforeAll
    public static void makeInstance() throws Exception{
        try {
            junitTest = new Stopwatch();
        }catch (Exception e){
            System.out.println("error");
        }
    }


    @Test
    void getIs_stop() throws Exception {
        try {
            assertEquals(false, junitTest.getIs_stop());
        }catch (Exception e){
            System.out.println("error");
        }
    }


    @Test
    void setIs_stop() throws Exception{
        try{
            junitTest.setIs_stop(true);
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Ignore
    void run() {
        junitTest.run();
        System.out.println("run이 끝나지 않아 ignore처리");
    }

    @Ignore
    void countUp()  throws Exception{
        try{
            junitTest.countUp();
        }catch (Exception e){
            System.out.println("error:무한히 test되므로 Ignore");
        }
    }

    @Test
    void reset() throws Exception {
        try {
            junitTest.reset();
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void showRecord(int column) throws Exception {
        try {
            junitTest.showRecord(3);
        }catch (Exception e){
            System.out.println("error");
        }
    }

    @Test
    void record(int count) throws Exception {
        try {
            junitTest.record(2);
        }catch (Exception e){
            System.out.println("error");
        }
    }
}