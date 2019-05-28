package View;


import watch.Fitness;
import watch.InstManager;
import watch.Timekeeping;

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
import java.util.Timer;
import java.util.TimerTask;


public class FitnessView extends JPanel{
    private JLabel fitness_label;
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
    private  JLabel tmp;

    private int hour;
    private int minute;
    private int second;
    private int count;
    private boolean is_pause = false; //뷰에서 pause체크해준다.

    Calendar calendar;
    SimpleDateFormat dot_fm;
    SimpleDateFormat seg_fm;
    String strDate;
    String strDate2;
    Timer tm;


    private String fit_status = "List"; //TimeKeeping 과 TimeSetting 의 View 가 존재한다.
    /*
        1. List     2. Setting      3. Excute
     */

    public FitnessView(BaseView base)
    {
        this.base= base;
        this.setBounds(0,0,800,500);
       // this.setBackground(Color.YELLOW);
        count =1;

        calendar = Calendar.getInstance();
        dot_fm = new SimpleDateFormat("MM.dd");
        seg_fm = new SimpleDateFormat("HH:mm:ss");
        calendar.set(InstManager.getInstance().getTimekeeping().getYear(),InstManager.getInstance().getTimekeeping().getMonth(),InstManager.getInstance().getTimekeeping().getDate(),InstManager.getInstance().getTimekeeping().getHour(),InstManager.getInstance().getTimekeeping().getMinute(),InstManager.getInstance().getTimekeeping().getSecond());

        //ImageIcon icon1 = new ImageIcon("C:\\Users\\조은지\\IdeaProjects\\CTIP_SMA_6\\src\\main\\java\\Image\\circle.png");
        fitness_label = new JLabel();
        fitness_label.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        fitness_label.setBounds(0,0,500,500);

        tmp = new JLabel();
        tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        tmp.setBounds(100,100,300,300);
        fitness_label.add(tmp);
        //tk_label.setVisible(true);

        LCD1 = new JLabel();
        LCD1.setBounds(200,150,20,20);
        LCD1.setText("T");
        fitness_label.add(LCD1);
        LCD1.setVisible(base.controller.req_isFunctionSelected(1));

        LCD2 = new JLabel();
        LCD2.setBounds(220,150,20,20);
        LCD2.setText("A");
        fitness_label.add(LCD2);
        LCD2.setVisible(base.controller.req_isFunctionSelected(2));


        LCD3 = new JLabel();
        LCD3.setBounds(240,150,20,20);
        LCD3.setText("S");
        fitness_label.add(LCD3);
        LCD3.setVisible(base.controller.req_isFunctionSelected(3));


        LCD4 = new JLabel();
        LCD4.setBounds(260,150,20,20);
        LCD4.setText("D");
        fitness_label.add(LCD4);
        LCD4.setVisible(base.controller.req_isFunctionSelected(4));


        LCD5 = new JLabel();
        LCD5.setBounds(280,150,20,20);
        LCD5.setText("F");
        fitness_label.add(LCD5);
        LCD5.setVisible(base.controller.req_isFunctionSelected(5));

        this.add(fitness_label);
        this.setLayout(null);

        TimerTask tt = new TimerTask() {
            Fitness fit = InstManager.getInstance().getFitness();
            @Override
            public void run() {

                calendar.set(base.controller.getInstManager().getTimekeeping().getYear(),fit.getMonth(),fit.getDate(),fit.getHour(),fit.getMinute(),fit.getSecond());
                strDate = dot_fm.format(calendar.getTime());
                dot.setText(strDate+fit.getTotalCalories());
                System.out.println("운동 화면시간"+calendar.get(Calendar.SECOND));
                strDate2 = seg_fm.format(calendar.getTime());
                segment.setText(strDate2);
                if(is_pause ==true)
                    tm.cancel();

            }
        };

        A = new JButton("A");
        A.setBounds(100,150,50,50);
        fitness_label.add(A);
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                base.controller.req_changeMode();
            }
        });
        B = new JButton("B");
        B.setBounds(350,150,50,50);
        fitness_label.add(B);
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(tk_status.equals("TimeKeeping"))
                if(fit_status.equals("List") == true){
                    fit_status = "Setting";

                }
                else if(fit_status.equals("Setting") == true){
                    strDate = base.controller.req_nextExercise();
                    dot.setText(strDate);

                }
                else if(fit_status.equals("Excute") == true){


                }
            }
        });
        C = new JButton("C");
        C.setBounds(100,300,50,50);
        fitness_label.add(C);
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tm = new Timer();

                if(fit_status.equals("Setting") == true){
                    base.controller.req_setExercise(strDate);
                    fit_status = "Execute";//모드 전환
                    base.controller.req_countUp("fitness");
                    tm.scheduleAtFixedRate(tt,0,1000);


                }
                else if(fit_status.equals("Excute") == true){
                    if(is_pause==false) {
                        base.controller.req_pause("fitness");
                        is_pause = true;
                    }
                    else if(is_pause ==true)
                    {
                        base.controller.req_continue("fitness");
                        is_pause =false;
                    }

                }
            }
        });
        D = new JButton("D");
        D.setBounds(350,300,50,50);
        fitness_label.add(D);
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fit_status.equals("List") == true){
                    //NEXT버튼
                    count++;
                    if(count == base.controller.getInstManager().getFitness().getCount()+1)
                        count=1;
                    base.controller.req_fitnessList(count);
                    calendar.set(Calendar.DAY_OF_YEAR,base.controller.getInstManager().getFitness().getMonth(),base.controller.getInstManager().getFitness().getDate(),base.controller.getInstManager().getFitness().getHour(),base.controller.getInstManager().getFitness().getMinute(),base.controller.getInstManager().getFitness().getSecond());
                    strDate = seg_fm.format(calendar.getTime());
                    segment.setText(strDate);

                }
                else if(fit_status.equals("Setting") == true){
                    base.controller.req_finish("fitness");
                }

            }
        });
        calendar.set(Calendar.DAY_OF_YEAR,base.controller.getInstManager().getFitness().getMonth(),base.controller.getInstManager().getFitness().getDate(),base.controller.getInstManager().getFitness().getHour(),base.controller.getInstManager().getFitness().getMinute(),base.controller.getInstManager().getFitness().getSecond());
        dot = new JLabel();
        dot.setBounds(150,200,200,30);
        dot.setFont(new Font("돋움",Font.BOLD,15));
        dot.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        strDate2 = dot_fm.format(calendar.getTime());
        dot.setText(strDate2);
        fitness_label.add(dot);
        base.controller.req_fitnessList(count);
       // dot.setText(fitness.getMonth()+"."+fitness.getDate()+"."+fitness.getTotalCalories());

        segment = new JLabel();

        strDate = seg_fm.format(calendar.getTime());
        segment.setText(strDate);
        segment.setBounds(150,230,200,50);
        segment.setBorder(new TitledBorder((new LineBorder(Color.BLACK))));
        segment.setFont(new Font("돋움",Font.BOLD,30));
        fitness_label.add(segment);



    }

}

