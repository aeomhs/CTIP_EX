package View;


import watch.Controller;
import watch.InstManager;
import watch.Stopwatch;
import watch.StopwatchDTO;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class StopWatchView extends JPanel{
    private JLabel sw_label;
    private BaseView base;

    private JButton A;
    private JButton B;
    private JButton C;
    private JButton D;

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


    SimpleDateFormat dot_fm;
    SimpleDateFormat seg_fm;
    String strDate;
    Calendar calendar;
    public Controller controller = null;
    public Stopwatch sw = null;
    StopwatchDTO dto;

    /*
        1. Pause    2.Excute      3. Record
    */


    public StopWatchView(BaseView base)
    {
        this.base= base;
        this.setBounds(0,0,800,500);
        this.setBackground(Color.CYAN);
        dot = new JLabel();

        calendar = Calendar.getInstance();
        dto = StopwatchDTO.getInstance();

       // ImageIcon icon1 = new ImageIcon("C:\\Users\\조은지\\IdeaProjects\\CTIP_SMA_6\\src\\main\\java\\Image\\circle.png");
        sw_label = new JLabel();
        seg_fm = new SimpleDateFormat("HH:mm:ss");
       // strDate = seg_fm.format(calendar.getTime());
        dot.setText(dto.getNum() + ":");
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

        this.add(sw_label);
        this.setLayout(null);


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
                base.change_view(6);
                if(sw_status.equals("Pause") == true){  //리셋이다.
                    base.controller.req_finish("stopwatch");
                }
                else if(sw_status.equals("Execute") == true){   //리셋이다
                    base.controller.req_finish("stopwatch");
                    sw_status = "Pause";
                }
                else if(sw_status.equals("Record") == true){    //next기록보여준다
                    //recordlist에서 next를 보여주는거니까 이건 내일 해결하자
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
                    if(hour==0 && minute==0 && second==0){
                        controller.req_countUp("stopwatch");
                        sw_status = "Execute";
                    }
                    else{
                        controller.req_continue("stopwatch");
                        sw_status = "Execute";
                    }

                }
                else if(sw_status.equals("Execute") == true){
                    controller.req_pause("stopwatch");
                    sw_status = "Pause";
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
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sw_status.equals("Pause") == true){


                }
                else if(sw_status.equals("Excute") == true){


                }
                else if(sw_status.equals("Record") == true){
                    base.controller.req_recordList();
                    calendar.set(InstManager.getInstance().getTimekeeping().getYear(),InstManager.getInstance().getTimekeeping().getMonth(),InstManager.getInstance().getTimekeeping().getDate(),dto.getHour(),dto.getMinute(),dto.getSecond());
                }
            }
        });

        dot.setText("05.24.FRI");
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

}
