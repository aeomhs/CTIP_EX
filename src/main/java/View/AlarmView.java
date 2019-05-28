package View;

import watch.Alarm;
import watch.InstManager;
import watch.Timekeeping;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AlarmView extends JPanel {
    private JLabel alarm_label;
    private BaseView base;
    private Alarm alarm;

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


    private int dayListNum;
    private String text;
    private int i;
    private int num;
    private String setting_state;
    private ArrayList<Integer> checkDayList;
    private int cycle;
    private int[] cycleList = {5, 10, 15};
    private int index = 0;
    private int choice = 0;

    private Calendar calendar;
    private SimpleDateFormat dot_fm;
    private SimpleDateFormat seg_fm;
    private String strTime;
    private String strDay;
    private int settingNum;

    private String alarm_status = "List";
    /*
        1. List     2. Setting      3. Add
     */

    public AlarmView(BaseView base)
    {
        settingNum = 1;
        calendar = Calendar.getInstance();
        calendar.set(0,0,0, 0,0);
        calendar.setWeekDate(0,0,2);
        checkDayList = new ArrayList<Integer>(7);
        this.base = base;
        this.setBounds(0,0,800,500);
        //this.setBackground(Color.GREEN);

        ImageIcon icon1 = new ImageIcon("C:\\Users\\yheji\\IdeaProjects\\CTIP_SMA_6\\src\\main\\java\\Image\\circle.png");
        alarm_label = new JLabel(icon1);
        alarm_label.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        alarm_label.setBounds(0,0,500,500);

        tmp = new JLabel();
        tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        tmp.setBounds(100,100,300,300);
        alarm_label.add(tmp);
        //tk_label.setVisible(true);

        LCD1 = new JLabel();
        LCD1.setBounds(200,150,20,20);
        LCD1.setText("T");
        alarm_label.add(LCD1);
        LCD1.setVisible(base.controller.req_isFunctionSelected(1));

        LCD2 = new JLabel();
        LCD2.setBounds(220,150,20,20);
        LCD2.setText("A");
        alarm_label.add(LCD2);
        LCD2.setVisible(base.controller.req_isFunctionSelected(2));


        LCD3 = new JLabel();
        LCD3.setBounds(240,150,20,20);
        LCD3.setText("S");
        alarm_label.add(LCD3);
        LCD3.setVisible(base.controller.req_isFunctionSelected(3));


        LCD4 = new JLabel();
        LCD4.setBounds(260,150,20,20);
        LCD4.setText("D");
        alarm_label.add(LCD4);
        LCD4.setVisible(base.controller.req_isFunctionSelected(4));


        LCD5 = new JLabel();
        LCD5.setBounds(280,150,20,20);
        LCD5.setText("F");
        alarm_label.add(LCD5);
        LCD5.setVisible(base.controller.req_isFunctionSelected(5));

        this.add(alarm_label);
        this.setLayout(null);


        A = new JButton("A");
        A.setBounds(100,150,50,50);
        this.add(A);
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                base.controller.req_changeMode();

            }
        });
        B = new JButton("B");
        B.setBounds(350,150,50,50);
        this.add(B);
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(alarm_status.equals("List") == true){
                    alarm = base.controller.req_alarmList();
                    if(alarm == null){
                        dot.setText("No Record");
                        segment.setText("00:00:00");

                    }
                    else{
                        text = " ";
                        dayListNum = alarm.getDayListNum();
                        for(i = 0; i< dayListNum; i++){
                            num= alarm.getCheckDayList(i);
                            if(num == 1){
                                text = text + "월 " ;
                            }
                            else if(num == 2){
                                text = text + "화 ";
                            }
                            else if(num == 3){
                                text = text + "수 ";
                            }
                            else if(num == 4){
                                text = text + "목 ";
                            }
                            else if(num == 5){
                                text = text = text + "금 ";
                            }
                            else if(num == 6){
                                text = text = text + "토 ";
                            }
                            else if(num == 7){
                                text = text = text + "일 ";
                            }

                        }
                        dot.setText(text);
                        segment.setText(Integer.toString(InstManager.getInstance().getdDayIndex())+". "+ Integer.toString(alarm.getHour()) + ":" + Integer.toString(alarm.getMinute()));
                    }


                }
                else if(alarm_status.equals("Setting") == true){
                    if(setting_state.equals("Delete")){
                        base.controller.req_deleteAlarm();
                    }
                    else if(setting_state.equals("Change OnOff")){
                        base.controller.req_onOff();
                    }

                }
                else if(alarm_status.equals("Add") == true){
                    switch(settingNum)
                    {
                        case 1:
                            if(checkDayList.contains(new Integer(calendar.get(Calendar.DAY_OF_WEEK)+1))){
                                dot.setForeground(Color.BLUE);
                            }
                            else{
                                dot.setForeground(Color.DARK_GRAY);

                            }
                            req_nextDay();
                            if(calendar.get(Calendar.DAY_OF_WEEK)==1){
                                dot.setForeground(Color.DARK_GRAY);
                                settingNum++;
                            }

                            break;
                        case 2:
                            req_nextCycle();
                            break;
                        case 3:
                            req_nextHour();
                            break;
                        case 4:
                            req_nextMinute();
                            break;

                    }

                }
            }
        });
        C = new JButton("C");
        C.setBounds(100,300,50,50);
        this.add(C);
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alarm_status.equals("List") == true){
                    if(alarm != null){
                        choice = 0;
                        dot.setText("delete?");

                    }
                    else{
                        dot.setText("No Record");
                        segment.setText("00:00:00");

                    }

                }
                else if(alarm_status.equals("Setting") == true){

                    //이 때 C버튼은 OK(Next)
                 /*   System.out.println(settingNum);
                    if(settingNum==2)
                    {
                        base.controller.req_setDate("timekeeping",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                        Timekeeping tk = InstManager.getInstance().getTimekeeping();
                        System.out.println(tk.getDate());

                    }
                    else if(settingNum==6) {
                        base.controller.req_setTime(calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
                        settingNum = 0;

                    }
                */
                }
                else if(alarm_status.equals("Add") == true){

                    if(settingNum == 1){
                        if(checkDayList.contains(new Integer(calendar.get(Calendar.DAY_OF_WEEK)))){
                            checkDayList.remove(new Integer(calendar.get(Calendar.DAY_OF_WEEK)));
                            dot.setForeground(Color.DARK_GRAY);
                        }
                        else{
                            checkDayList.add(new Integer(calendar.get(Calendar.DAY_OF_WEEK)));
                            dot.setForeground(Color.BLUE);
                        }

                    }
                    else{
                        settingNum++;
                        if(settingNum == 4){
                            base.controller.req_setDate(checkDayList, dayListNum, cycleList[index], calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE));
                        }
                    }

                }
            }
        });
        D = new JButton("D");
        D.setBounds(350,300,50,50);
        this.add(D);
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(alarm_status.equals("List") == true){
                        alarm = base.controller.req_setAlarm();
                        if(alarm != null){
                            alarm_status = "Add";
                            strDay = dot_fm.format(calendar.getTime());
                            dot.setText(strDay);
                            settingNum = 1;
                        }
                }
                else if(alarm_status.equals("Setting") == true){


                }
                else if(alarm_status.equals("Add") == true){
                    //DOWN버튼
                    switch(settingNum)
                    {
                        case 1:
                            if(checkDayList.contains(new Integer(calendar.get(Calendar.DAY_OF_WEEK)-1))){
                                dot.setForeground(Color.BLUE);
                            }
                            else{
                                dot.setForeground(Color.DARK_GRAY);
                            }
                            req_prevDay();
                            break;
                        case 2:
                            req_prevCycle();
                            break;
                        case 3:
                            req_prevHour();
                            break;
                        case 4:
                            req_prevMinute();
                            break;

                    }

                }
            }
        });

        dot = new JLabel();
        dot.setText("05.24.FRI");
        dot.setBounds(150,200,100,30);
        dot.setFont(new Font("돋움",Font.BOLD,15));
        dot.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        alarm_label.add(dot);

        segment = new JLabel();

       // segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
        segment.setBounds(150,230,200,50);
        segment.setBorder(new TitledBorder((new LineBorder(Color.BLACK))));
        segment.setFont(new Font("돋움",Font.BOLD,40));
        alarm_label.add(segment);

        dot_fm = new SimpleDateFormat("E요일");
        seg_fm = new SimpleDateFormat("   HH:mm");

        alarm = base.controller.req_alarmList();
        if(alarm == null){
            dot.setText("No Record");
            segment.setText("00:00:00");

        }
        else{
            text = " ";
            dayListNum = alarm.getDayListNum();
            for(i = 0; i< dayListNum; i++){
                num= alarm.getCheckDayList(i);
                if(num == 1){
                    text = text + "월 " ;
                }
                else if(num == 2){
                    text = text + "화 ";
                }
                else if(num == 3){
                    text = text + "수 ";
                }
                else if(num == 4){
                    text = text + "목 ";
                }
                else if(num == 5){
                    text = text = text + "금 ";
                }
                else if(num == 6){
                    text = text = text + "토 ";
                }
                else if(num == 7){
                    text = text = text + "일 ";
                }

            }
            dot.setText(text);
            segment.setText(Integer.toString(InstManager.getInstance().getdDayIndex())+". "+ Integer.toString(alarm.getHour()) + ":" + Integer.toString(alarm.getMinute()));
        }



    }
    public void req_nextDay()
    {
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        strDay = dot_fm.format(calendar.getTime());
        dot.setText(strDay);
    }
    public void req_prevDay()
    {
        calendar.add(Calendar.DAY_OF_WEEK,-1);
        strDay = dot_fm.format(calendar.getTime());
        dot.setText(strDay);
    }
    public void req_nextCycle(){
        index ++;
        if(index == 3){
            index = 0;
        }
        dot.setText(strDay + " 주기:"+cycleList[index]);
    }
    public void req_prevCycle(){
        index --;
        if(index == -1){
            index = 2;
        }
        dot.setText(strDay + " 주기:"+cycleList[index]);
    }

    public void req_nextHour()
    {
        calendar.add(Calendar.HOUR,1);
        strTime = seg_fm.format(calendar.getTime());
        segment.setText(strTime);
    }
    public void req_prevHour()
    {
        calendar.add(Calendar.HOUR,-1);
        strTime = seg_fm.format(calendar.getTime());
        segment.setText(strTime);
    }
    public void req_nextMinute()
    {
        calendar.add(Calendar.MINUTE, 1);
        strTime = seg_fm.format(calendar.getTime());
        segment.setText(strTime);
    }
    public void req_prevMinute()
    {
        calendar.add(Calendar.MINUTE,-1);
        strTime = seg_fm.format(calendar.getTime());
        segment.setText(strTime);
    }



}
