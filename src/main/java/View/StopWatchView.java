package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import watch.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class StopWatchView extends JPanel{
    private JLabel sw_label;
    private BaseView base;

    private Stopwatch stw;
    private InstManager instManager;
    private Timekeeping tk;

    private JButton A;  //mode전환
    private JButton B;  //reset             ->  next
    private JButton C;  //pause/continue    ->  none
    private JButton D;  //record하기(실행중) or record보기(일시정지)   -> record(일시정지상태로 돌아가기

    private  JLabel LCD1; //기능들을 표시할 LCD 화면 //Timer
    private  JLabel LCD2;//Alarm
    private  JLabel LCD3;//Stopwatch
    private  JLabel LCD4;//Dday
    private  JLabel LCD5;//Fitness

    private JLabel dot;
    private JLabel segment;
    private JLabel tmp;

    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    private String sw_status = "Pause"; //TimeKeeping 과 TimeSetting 의 View 가 존재한다.
    private boolean is_stop = false;

    SimpleDateFormat dot_fm;
    SimpleDateFormat seg_fm;
    Calendar calendar;
    String strDate;
    public Controller controller = null;
    StopwatchDTO dto;

    /*
        1. Pause    2.Excute      3. Record //이 레코드 상태라는건 기록을 보는상황일때
                                            //기록하는중이 아니라
    */


    public StopWatchView(BaseView base)
    {
        stw = InstManager.getInstance().getStopwatch();
        instManager = InstManager.getInstance();
        tk = InstManager.getInstance().getTimekeeping();
        calendar = Calendar.getInstance();
        dto = StopwatchDTO.getInstance();

        this.base= base;
        this.setBounds(0,0,800,500);
        this.setBackground(Color.CYAN);

        //ImageIcon icon1 = new ImageIcon("C:\\Users\\조은지\\IdeaProjects\\CTIP_SMA_6\\src\\main\\java\\Image\\circle.png");
        sw_label = new JLabel();
        seg_fm = new SimpleDateFormat("HH:mm:ss");
        dot_fm = new SimpleDateFormat("HH:mm:ss");
        // strDate = seg_fm.format(calendar.getTime());

        this.add(sw_label);
        this.setLayout(null);

        dot = new JLabel();
        dot.setText(dto.getNum() + ":");//도트는 기록보여줘야한다.

        sw_label.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        sw_label.setBounds(0,0,500,500);

        tmp = new JLabel();
        tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        tmp.setBounds(100,100,300,300);
        sw_label.add(tmp);
        //tk_label.setVisible(true);

        LCD1 = new JLabel();
        LCD1.setBounds(200,150,20,20);
        LCD1.setText("T");
        sw_label.add(LCD1);
        LCD1.setVisible(base.controller.req_isFunctionSelected(1));

        LCD2 = new JLabel();
        LCD2.setBounds(220,150,20,20);
        LCD2.setText("A");
        sw_label.add(LCD2);
        LCD2.setVisible(base.controller.req_isFunctionSelected(2));

        LCD3 = new JLabel();
        LCD3.setBounds(240,150,20,20);
        LCD3.setText("S");
        sw_label.add(LCD3);
        LCD3.setVisible(base.controller.req_isFunctionSelected(3));

        LCD4 = new JLabel();
        LCD4.setBounds(260,150,20,20);
        LCD4.setText("D");
        sw_label.add(LCD4);
        LCD4.setVisible(base.controller.req_isFunctionSelected(4));

        LCD5 = new JLabel();
        LCD5.setBounds(280,150,20,20);
        LCD5.setText("F");
        sw_label.add(LCD5);
        LCD5.setVisible(base.controller.req_isFunctionSelected(5));


        A = new JButton("A");
        A.setBounds(100,150,50,50);
        sw_label.add(A);
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                base.controller.req_changeMode();
            }
        });


        B = new JButton("B");
        B.setBounds(350,150,50,50);
        sw_label.add(B);
        B.addActionListener(new ActionListener() {  //reset버튼 / next버튼
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(tk_status.equals("TimeKeeping"))
                if(sw_status.equals("Pause") == true){  //리셋이다.
                    base.controller.req_finish("stopwatch");
                    segment.setText(Integer.toString(stw.getHour())+":"+Integer.toString(stw.getMinute())+":"+Integer.toString(stw.getSecond()));
                }
                else if(sw_status.equals("Execute") == true){   //리셋이다
                    base.controller.req_finish("stopwatch");
                    sw_status = "Pause";
                    segment.setText(Integer.toString(stw.getHour())+":"+Integer.toString(stw.getMinute())+":"+Integer.toString(stw.getSecond()));
                }
                else if(sw_status.equals("Record") == true){    //next기록보여준다
                    base.controller.req_recordList();
                    calendar.set(InstManager.getInstance().getTimekeeping().getYear(),InstManager.getInstance().getTimekeeping().getMonth(),InstManager.getInstance().getTimekeeping().getDate(),dto.getHour(),dto.getMinute(),dto.getSecond());
                    strDate = seg_fm.format(calendar.getTime());
                    dot.setText(dto.getNum()+":"+strDate);
                }
            }
        });

        C = new JButton("C");
        C.setBounds(100,300,50,50);
        sw_label.add(C);
        C.addActionListener(new ActionListener() {  //pause or continue버튼
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sw_status.equals("Pause") == true){
                    sw_status = "Execute";
                    if(hour==0 && minute==0 && second==0){
                        base.controller.req_countUp("stopwatch");
                    }
                    else if(hour!=0 || minute!=0 || second!=0){
                        base.controller.req_continue("stopwatch");
                    }
                }
                else if(sw_status.equals("Execute") == true){
                    sw_status = "Pause";
                    base.controller.req_pause("stopwatch");
                    segment.setText(Integer.toString(stw.getHour())+":"+Integer.toString(stw.getMinute())+":"+Integer.toString(stw.getSecond()));

                }
                else if(sw_status.equals("Record") == true){
                    //none
                }
            }
        });


        D = new JButton("D");
        D.setBounds(350,300,50,50);
        sw_label.add(D);
        D.addActionListener(new ActionListener() {
            //record하기(실행중) or record보기(일시정지)   -> record(일시정지상태로 돌아가기)

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("여기는 d버튼");
                if(sw_status.equals("Pause") == true){
                    sw_status = "Record";
                    base.controller.req_recordList();

                }
                else if(sw_status.equals("Execute") == true){
                    System.out.println("record하러왔다");
                    base.controller.req_record();

                }
                else if(sw_status.equals("Record") == true){
                    sw_status = "Pause";
                    segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
                    //  base.controller.req_recordList();
                    //   calendar.set(InstManager.getInstance().getTimekeeping().getYear(),InstManager.getInstance().getTimekeeping().getMonth(),InstManager.getInstance().getTimekeeping().getDate(),dto.getHour(),dto.getMinute(),dto.getSecond());
                }
            }
        });

        Timer m_timer = new Timer();
        TimerTask m_task = new TimerTask(){
            @Override
            public void run(){
                if(sw_status.equals("Execute")){
                    segment.setText(Integer.toString(stw.getHour())+":"+Integer.toString(stw.getMinute())+":"+Integer.toString(stw.getSecond()));
                }
            }
        };
        m_timer.scheduleAtFixedRate(m_task, 0, 1000);


        dot.setText("05.24.FRI");   //record상태라면 기록보여주기, 아니라면 가장 최근 기록보여주기
        dot.setBounds(150,200,100,30);
        dot.setFont(new Font("돋움",Font.BOLD,15));
        dot.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        sw_label.add(dot);

        segment = new JLabel();

        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
        segment.setBounds(150,230,200,50);
        segment.setBorder(new TitledBorder((new LineBorder(Color.BLACK))));
        segment.setFont(new Font("돋움",Font.BOLD,50));
        sw_label.add(segment);
    }

    public void setLCD(StopWatchView stwView) {
        stwView.LCD1.setVisible(base.controller.req_isFunctionSelected(1));
        stwView.LCD2.setVisible(base.controller.req_isFunctionSelected(2));
        stwView.LCD3.setVisible(base.controller.req_isFunctionSelected(3));
        stwView.LCD4.setVisible(base.controller.req_isFunctionSelected(4));
        stwView.LCD5.setVisible(base.controller.req_isFunctionSelected(5));
    }

}
