package watch;

import java.sql.*;


public class DBManager {


    Connection con = null;

    PreparedStatement psmt = null;
    PreparedStatement psmt_ver2 = null;
    PreparedStatement psmt_fit = null;//고정
    PreparedStatement psmt_sw = null;//고정

    ResultSet rs = null;
    ResultSet rs_ver2 = null;
    // ResultSetMetaData rs_num = null;



    String sql = null;
    String sql_ver2 = null;
    String sql_fit = null;
    String sql_sw = null;

    String url = null;

    //Fitness Attribute


    private int fitColumn ;// 열 개수
    private FitnessDTO fitDTO = FitnessDTO.getInstance();
    private int first=0;

    //Stopwatch Attibute
    private int swColumn;
    private StopwatchDTO swDTO = StopwatchDTO.getInstance();

    public DBManager()
    {

        url = "jdbc:mysql://localhost:3306/watch?useSSL=false";
        sql_fit = "select * from fitness";
        sql_sw = "select * from stopwatch";

        try {
            Class.forName("com.mysql.jdbc.Driver");//드라이버 로드
            con = DriverManager.getConnection(url,"aaa","!rhfms2clrhk!");
            System.out.println("DB연결 성공");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }


    //Fitness method
    //필드설명
    //1.month 2.date 3.hour 4.minute 5.second 6.totalCalories 7.record_num(column_num을 저장)

    public void selectFitness(String status,int column){
        //어떤땐 전체를, 어떤땐 마지막 값만 반환해야 함.
        //분기처리 해줘야 함.

        fitColumn=column;

        sql = "select * FROM fitness where number="+Integer.toString(fitColumn)+";";
      /*  if(fitColumn==1)
        {
            System.out.println("기록 존재하지 않음");
            return;
        }*/

        try {
            psmt_fit = con.prepareStatement(sql_fit);//레코드 개수 가져오기 (기본)

            rs_ver2 = psmt_fit.executeQuery(sql_fit);

            rs_ver2.last();
            fitDTO.setCount(rs_ver2.getRow()); //레코드개수 저장


            if(fitDTO.getCount()==0) {
                System.out.println("기록 존재하지 않음");
                return;
            }

            if (status.equals("look")) {
                psmt = con.prepareStatement(sql);//특정 행 가져오기
                rs = psmt.executeQuery(sql);

                while(rs.next()) {
                    fitDTO.setMonth(rs.getInt("month"));
                    fitDTO.setDate(rs.getInt("date"));
                    fitDTO.setHour(rs.getInt("hour"));
                    fitDTO.setMinute(rs.getInt("minute"));
                    fitDTO.setSecond(rs.getInt("second"));
                    fitDTO.setTotalCalories(rs.getInt("totalCalories"));


                }

            }
            else if(status.equals("finish"))
            {
                sql = "select * FROM fitness where number="+Integer.toString(fitDTO.getCount())+";"; //마지막 레코드만 읽어오기
                psmt = con.prepareStatement(sql);
                rs = psmt.executeQuery(sql);
                while(rs.next()) {
                    fitDTO.setMonth(rs.getInt("month"));
                    fitDTO.setDate(rs.getInt("date"));
                    fitDTO.setHour(rs.getInt("hour"));
                    fitDTO.setMinute(rs.getInt("minute"));
                    fitDTO.setSecond(rs.getInt("second"));
                    fitDTO.setTotalCalories(rs.getInt("totalCalories"));
                }

            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public void insertFitness(int month, int date, int hour, int minute,int second, int totalCalories) throws ClassNotFoundException {

        sql = "insert into fitness value (?,?,?,?,?,?,?)";
        fitColumn=0;

        try{

            psmt = con.prepareStatement(sql);
            psmt_fit = con.prepareStatement(sql_fit);

            psmt.setInt(1,month);
            psmt.setInt(2,date);
            psmt.setInt(3,hour);
            psmt.setInt(4,minute);
            psmt.setInt(5,second);
            psmt.setInt(6,totalCalories);
            psmt.setInt(7,fitDTO.getCount()+1); //현재 레코드개수+1

            psmt.executeUpdate();
            //실행한 후 증가된 개수
            rs_ver2 = psmt_fit.executeQuery(sql_fit);
            rs_ver2.last();
            fitDTO.setCount(rs_ver2.getRow());


        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateFitness( int hour, int minute,int second, int totalCalories){

        //맨 마지막 거에만 추가해주는 거.
        sql = "update fitness set hour = ?,minute= ? ,second= ?,totalCalories= ? where number = ?;";
        fitColumn=0;
        try{
            psmt = con.prepareStatement(sql);
            psmt.setInt(1,hour);
            psmt.setInt(2,minute);
            psmt.setInt(3,second);
            psmt.setInt(4,totalCalories);
            psmt.setInt(5,fitDTO.getCount());
            psmt.executeUpdate();
            fitDTO.setHour(hour);
            fitDTO.setMinute(minute);
            fitDTO.setSecond(second);
            fitDTO.setTotalCalories(totalCalories);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void deleteFitness(){

        sql = "delete from fitness where number= 1;";
        sql_ver2 = "update fitness set number = number - 1";

        try{

            psmt = con.prepareStatement(sql);
            psmt_ver2 = con.prepareStatement(sql_ver2);

            psmt.executeUpdate();
            psmt_ver2.executeUpdate(); //모든 레코드의 column넘버를 -1해줌

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    //Stopwatch method
    //필드설명 1. hour 2. minute 3.second 4. number

    public void selectStopwatch(int column){
        swColumn = column;
        sql = "select * from stopwatch where number="+Integer.toString(swColumn)+";";
        if(swColumn==0)
        {
            System.out.println("No Record");
            return;
        }

        try {

            psmt = con.prepareStatement(sql); //보여주는 애
            psmt_sw = con.prepareStatement(sql_sw); //개수세는 애

            rs = psmt.executeQuery(sql);
            rs_ver2 =psmt_sw.executeQuery(sql_sw);
            rs_ver2.last();
            swDTO.setNum(rs_ver2.getRow());

            while(rs.next()) {
                swDTO.setHour(rs.getInt("hour"));
                swDTO.setMinute(rs.getInt("minute"));
                swDTO.setSecond(rs.getInt("second"));
                swColumn++;
                /*if (swColumn == 11)
                    swColumn = 0;*/
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void insertStopwatch(int hour, int minute, int second, int count){


        sql_ver2= "insert into stopwatch value (?,?,?,?)";

        try{

            psmt_ver2 = con.prepareStatement(sql_ver2);
            psmt_sw = con.prepareStatement(sql_sw); //개수세는애

            //완료안하고 창닫으면 기존 기록 누적되어있음  오류처리
            rs_ver2 =psmt_sw.executeQuery(sql_sw);
            rs_ver2.last();
            System.out.println(rs_ver2.getRow());
            if((count-1 != rs_ver2.getRow())&&first==0 )
            {
                resetStopwatch();
                //지워주고 저장시작
                rs_ver2 = psmt_sw.executeQuery(sql_sw);
                rs_ver2.last();
                swDTO.setNum(0);
                first++;
            }

            psmt_ver2.setInt(1,hour);
            psmt_ver2.setInt(2,minute);
            psmt_ver2.setInt(3,second);
            psmt_ver2.setInt(4,swDTO.getNum()+1);


            swDTO.setHour(hour);
            swDTO.setMinute(minute);
            swDTO.setSecond(second);

            psmt_ver2.executeUpdate();

            rs_ver2 =psmt_sw.executeQuery(sql_sw);//더해진 개수 센다.
            rs_ver2.last();
            swDTO.setNum(rs_ver2.getRow());

        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteStopwatch(){


        sql = "delete from stopwatch where number = 1";
        sql_ver2 = "update stopwatch set number = number -1;";

        try{
            psmt = con.prepareStatement(sql);
            psmt_ver2 = con.prepareStatement(sql_ver2);

            psmt.executeUpdate();
            psmt_ver2.executeUpdate();
            swDTO.setNum(swDTO.getNum()-1);

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    public void resetStopwatch(){

        sql  ="delete from stopwatch;";
        try{

            psmt = con.prepareStatement(sql);
            swDTO.setSecond(0);
            swDTO.setMinute(0);
            swDTO.setHour(0);
            swDTO.setNum(0);
            psmt.executeUpdate();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}