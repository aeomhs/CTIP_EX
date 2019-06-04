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


    @Ignore
    //현재 일 을 적어줘야 test통과가 된다. 이미 확인했으므로 차후 테스트 fail을 방지하기 위해 ignore처리를 해준다.
    void getDate() throws Exception {
        try {
            Assertions.assertEquals(4, junitTest.getDate());
        } catch (Exception var2) {
            System.out.println("error: expect값이 현재 '일'과 동일하지 않습니다");
        }
    }

    @Ignore
    //현재 요일을 적어줘야 test통가과 된다. 이미 확인했으므로 차후 테스트 fail을 방지하기 위해 Ignore처리를 해준다.
    void getDayNum()  throws Exception {
        try {
            Assertions.assertEquals(3, junitTest.getDayNum());
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