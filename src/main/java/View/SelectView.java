package View;




import watch.SelectFunction;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;



public class SelectView extends JPanel{
    private JLabel select_label;
    private BaseView base;

    private JButton A;
    private JButton B;
    private JButton C;
    private JButton D;

    //<
    private  JLabel LCD1; //기능들을 표시할 LCD 화면 //Timer
    private  JLabel LCD2;//Alarm
    private  JLabel LCD3;//Stopwatch
    private  JLabel LCD4;//Dday
    private  JLabel LCD5;//Fitness
    //>
    private JLabel dot;
    private JLabel segment;
    private JLabel tmp;

    private int hour;
    private int minute;
    private int second;

    //<
    private TimeKeepingView tkv;
    private SelectFunction selectFunction;
    private String functionName;
    //>

    public SelectView(BaseView base)
    {

        this.base= base;
        this.setBounds(0,0,800,500);
        this.setBackground(Color.ORANGE);

        //<
        this.tkv = new TimeKeepingView(base);
        this.selectFunction = base.controller.getInstManager().getSelectFunction();
        this.functionName = base.controller.req_funcList();
        this.functionName = base.controller.req_funcList();
        //초기에 timekeeping(항상 선택된 기능)이 대입되어있기 때문에
        //화면에 timekeeping 다음기능부터 출력시키기 위해서 getfunctionName()를 한번 더 호출해준다.
        //>

        ImageIcon icon1 = new ImageIcon("C:\\Users\\조은지\\IdeaProjects\\CTIP_SMA_6\\src\\main\\java\\Image\\circle.png");
        select_label = new JLabel(icon1);
        select_label.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        select_label.setBounds(0,0,500,500);

        tmp = new JLabel();
        tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        tmp.setBounds(100,100,300,300);
        select_label.add(tmp);
        //select_label.setVisible(true);

        //<
        LCD1 = new JLabel();
        LCD1.setBounds(200,150,20,20);
        LCD1.setText("T");
        select_label.add(LCD1);
        LCD1.setVisible(base.controller.req_isFunctionSelected(1));

        LCD2 = new JLabel();
        LCD2.setBounds(220,150,20,20);
        LCD2.setText("A");
        select_label.add(LCD2);
        LCD2.setVisible(base.controller.req_isFunctionSelected(2));


        LCD3 = new JLabel();
        LCD3.setBounds(240,150,20,20);
        LCD3.setText("S");
        select_label.add(LCD3);
        LCD3.setVisible(base.controller.req_isFunctionSelected(3));


        LCD4 = new JLabel();
        LCD4.setBounds(260,150,20,20);
        LCD4.setText("D");
        select_label.add(LCD4);
        LCD4.setVisible(base.controller.req_isFunctionSelected(4));


        LCD5 = new JLabel();
        LCD5.setBounds(280,150,20,20);
        LCD5.setText("F");
        select_label.add(LCD5);
        LCD5.setVisible(base.controller.req_isFunctionSelected(5));
        //>

        this.add(select_label);
        this.setLayout(null);

        A = new JButton("A");
        A.setBounds(100,150,50,50);
        select_label.add(A);
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //<
                base.change_view(0);//A버튼을 누르면 timekeepingView로 바꿔준다.
                //>
            }
        });
        B = new JButton("B");
        B.setBounds(350,150,50,50);
        select_label.add(B);
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //>
                functionName = base.controller.req_funcList();
                if(functionName.equals("TimeKeeping")){//보여주는 기능에 "TimeKeeping"이 뜨지 않도록 처리
                    functionName = base.controller.req_funcList();
                }//타임키핑은 기본으로 선택된 기능이므로 선택할 기능으로 보여주지 않고 한번 더 호출하여 다음 기능을 보여준다.
                dot.setText(functionName);
                //>
            }
        });
        C = new JButton("C");
        C.setBounds(100,300,50,50);
        select_label.add(C);
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //<
                if(functionName.equals("Timer")){
                    selectFunction.setFunctionList(1);//해당기능을 선택 또는 취소
                    LCD1.setVisible(selectFunction.getFunctionListBool(1));
                }
                else if(functionName.equals("Alarm")){
                    selectFunction.setFunctionList(2);//해당기능을 선택 또는 취소
                    LCD2.setVisible(selectFunction.getFunctionListBool(2));
                }
                else if(functionName.equals("StopWatch")){
                    selectFunction.setFunctionList(3);//해당기능을 선택 또는 취소
                    LCD3.setVisible(selectFunction.getFunctionListBool(3));
                }
                else if(functionName.equals("D+Day")){
                    selectFunction.setFunctionList(4);//해당기능을 선택 또는 취소
                    LCD4.setVisible(selectFunction.getFunctionListBool(4));
                }
                else if(functionName.equals("Fitness")){
                    selectFunction.setFunctionList(5);//해당기능을 선택 또는 취소
                    LCD5.setVisible(selectFunction.getFunctionListBool(5));
                }
                //>
            }
        });
        D = new JButton("D");
        D.setBounds(350,300,50,50);
        select_label.add(D);
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (base.controller.req_finishSelect()){//선택된 기능의 개수가 3개이면
                    base.change_view(0);// timekeepingView로 바꿔준다.
                }

            }
        });
        dot = new JLabel();

        //<
        dot.setText(functionName);
        //>
        dot.setBounds(150,200,100,30);
        dot.setFont(new Font("돋움",Font.BOLD,15));
        dot.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));

        select_label.add(dot);

        //<
        segment = tkv.getSegment();
        //>

        //<
/*      segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
        segment.setBounds(150,230,200,50);
        segment.setBorder(new TitledBorder((new LineBorder(Color.BLACK))));
        segment.setFont(new Font("돋움",Font.BOLD,50));
*/
        //>
        select_label.add(segment);


    }

}
