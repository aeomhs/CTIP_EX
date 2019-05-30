package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import watch.*;
import watch.Timer;
import java.util.Calendar;

import static java.lang.Thread.sleep;
//import java.util.Timer;
//import java.util.TimerTask;

public class TimerView extends JPanel{
    private JLabel timer_label;
    private BaseView base;

    private Timer timer;
    private InstManager instManager;
    private Timekeeping tk;

    private JButton A;  //mode전환    ->    mode
    private JButton B;  //up          ->    reset
    private JButton C;  //ok(start)   ->    pause(continue)
    private JButton D;  //down        ->    none
    private String timer_status = "Setting";
    /*
        1. Setting      2. Excute       3. Pause
     */
    private JLabel segment;
    private JLabel dot;

    private int hour = 0;
    private int minute = 0;
    private int second = 0;
    int settingNum = 0;

    Calendar calendar;
    SimpleDateFormat dot_fm;
    SimpleDateFormat seg_fm;
    String strDate;
    public Controller controller = null;



    public TimerView(BaseView base)
    {
        timer = instManager.getInstance().getTimer();
        instManager = InstManager.getInstance();
        tk = instManager.getInstance().getTimekeeping();
        calendar = Calendar.getInstance();


        this.base = base;
        this.setBounds(0,0,800,500);
        this.setBackground(Color.CYAN);

        dot_fm = new SimpleDateFormat("yy.MM.dd.E요일");
        seg_fm = new SimpleDateFormat("HH:mm:SS");

        ImageIcon icon1 = new ImageIcon("C:\\Users\\조은지\\IdeaProjects\\CTIP_SMA_6\\src\\main\\java\\Image\\circle.png");
        timer_label = new JLabel(icon1);
        timer_label.setBounds(0,0,500,500);
        //tk_label.setVisible(true);

        this.add(timer_label);
        this.setLayout(null);


        A = new JButton("A");
        A.setBounds(100,150,50,50);
        this.add(A);
        //mode버튼으로 기능전환
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                base.controller.req_changeMode();
            }
        });

        B = new JButton("B");
        B.setBounds(350,150,50,50);
        this.add(B);
        //if(타이머설정): up, else(타이머실행): reset
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timer_status.equals("Setting") == true){     //이게 문제
                    switch(settingNum){
                        case 0:
                            req_nextHour();
                            break;
                        case 1:
                            req_nextMinute();
                            break;
                        case 2:
                            req_nextSecond();
                            break;
                    }
                }
                //타이머 일시정지 중에 리셋버튼이 눌리면
                else if(timer_status.equals("Pause") == true){
                    base.controller.req_reset();
                    timer_status = "Setting";
                    segment.setText(Integer.toString(timer.getHour())+":"+Integer.toString(timer.getMinute())+":"+Integer.toString(timer.getSecond()));
                }
                //타이머 실행 중에 리셋 버튼이 눌리면
                else if(timer_status.equals("Execute") == true){
                    base.controller.req_pause("timer");
                    base.controller.req_reset();
                    timer_status = "Setting";
                    segment.setText(Integer.toString( timer.getHour())+":"+Integer.toString(timer.getMinute())+":"+Integer.toString(timer.getSecond()));
                }
            }
        });

        //if(타이머설정):ok, else(타이머실행):pause/continue
        C = new JButton("C");
        C.setBounds(100,300,50,50);
        this.add(C);
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timer_status.equals("Pause") == true){
                    System.out.println("continue합니다");
                    base.controller.req_continue("timer");
                    timer_status = "Execute";
                    while(timer_status.equals("Execute") == true){
                        segment.setText(Integer.toString( timer.getHour())+":"+Integer.toString(timer.getMinute())+":"+Integer.toString(timer.getSecond()));
                        try {
                            sleep(1000);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                    }

                else if(timer_status.equals("Setting") == true){
                    settingNum++;
                    if(settingNum == 3) {
                        req_next();
                        timer_status = "Execute";
                    }
                }
                else if(timer_status.equals("Execute") == true){
                    timer_status = "Pause";
                    System.out.println("일시정지합니다");
                    base.controller.req_pause("timer");
                    segment.setText(Integer.toString( timer.getHour())+":"+Integer.toString(timer.getMinute())+":"+Integer.toString(timer.getSecond()));
                }
            }
        });

        //if(타이머설정): down, else(타이머실행): none
        D = new JButton("D");
        D.setBounds(350,300,50,50);
        this.add(D);
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timer_status.equals("Pause") == true){
                    //none
                }
                else if(timer_status.equals("Setting") == true){
                    switch(settingNum){
                        case 0:
                            req_prevHour();
                            break;
                        case 1:
                            req_prevMinute();
                            break;
                        case 2:
                            req_prevSecond();
                            break;
                    }
                }
                else if(timer_status.equals("Execute") == true){
                    //none
                }
            }
        });

        dot = new JLabel();
        dot.setBounds(150,200,150,30);
        dot.setFont(new Font("돋움",Font.BOLD,15));
        dot.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));

        calendar.set(tk.getYear(),tk.getMonth(),tk.getDate(),tk.getHour(),tk.getMinute(),tk.getSecond());
        strDate = dot_fm.format(calendar.getTime());
        dot.setText(strDate);

        timer_label.add(dot);

        segment = new JLabel();

        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
        segment.setBounds(150,230,200,50);
        segment.setBorder(new TitledBorder((new LineBorder(Color.BLACK))));
        segment.setFont(new Font("돋움",Font.BOLD,40));
        timer_label.add(segment);

    }
    //req함수들
    public void req_nextHour() {
        if(hour != 99){
            hour=hour+1;
        }else{
            hour=0;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }
    public void req_prevHour(){
        if(hour != 0){
            hour--;
        }else{
            hour=0;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }

    public void req_nextMinute(){
        if(minute != 99){
            minute++;
        }else{
            minute=99;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }
    public void req_prevMinute(){
        if(minute != 0){
            minute--;
        }else{
            minute=0;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }

    public void req_nextSecond(){
        if(second != 99){
            second++;
        }else{
            second=99;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }
    public void req_prevSecond(){
        if(second != 0){
            second--;
        }else{
            second=0;
        }
        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
    }

    public void req_next(){
        base.controller.req_setTime("timer", hour, minute, second);
        timer_status = "Execute";
        base.controller.req_countDown();

    }
}

