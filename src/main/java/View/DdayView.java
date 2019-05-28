package View;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;



public class DdayView extends JPanel{
    private JLabel Dday_label;
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

    private int hour;
    private int minute;
    private int second;
    private String dDay_status = "List"; //TimeKeeping 과 TimeSetting 의 View 가 존재한다.
    /*
        1. List     2. Setting     3. Add
     */

    public DdayView(BaseView base)
    {
        this.base= base;
        this.setBounds(0,0,800,500);
        this.setBackground(Color.PINK);

        ImageIcon icon1 = new ImageIcon("C:\\Users\\조은지\\IdeaProjects\\CTIP_SMA_6\\src\\main\\java\\Image\\circle.png");
        Dday_label = new JLabel(icon1);
        Dday_label.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        Dday_label.setBounds(0,0,500,500);

        tmp = new JLabel();
        tmp.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        tmp.setBounds(100,100,300,300);
        Dday_label.add(tmp);
        //tk_label.setVisible(true);

        LCD1 = new JLabel();
        LCD1.setBounds(200,150,20,20);
        LCD1.setText("T");
        Dday_label.add(LCD1);
        LCD1.setVisible(base.controller.req_isFunctionSelected(1));

        LCD2 = new JLabel();
        LCD2.setBounds(220,150,20,20);
        LCD2.setText("A");
        Dday_label.add(LCD2);
        LCD2.setVisible(base.controller.req_isFunctionSelected(2));


        LCD3 = new JLabel();
        LCD3.setBounds(240,150,20,20);
        LCD3.setText("S");
        Dday_label.add(LCD3);
        LCD3.setVisible(base.controller.req_isFunctionSelected(3));


        LCD4 = new JLabel();
        LCD4.setBounds(260,150,20,20);
        LCD4.setText("D");
        Dday_label.add(LCD4);
        LCD4.setVisible(base.controller.req_isFunctionSelected(4));


        LCD5 = new JLabel();
        LCD5.setBounds(280,150,20,20);
        LCD5.setText("F");
        Dday_label.add(LCD5);
        LCD5.setVisible(base.controller.req_isFunctionSelected(5));

        this.add(Dday_label);
        this.setLayout(null);

        A = new JButton("A");
        A.setBounds(100,150,50,50);
        Dday_label.add(A);
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                base.controller.req_changeMode();
            }
        });
        B = new JButton("B");
        B.setBounds(350,150,50,50);
        Dday_label.add(B);
        B.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if(tk_status.equals("TimeKeeping"))
                base.change_view(6);
                if(dDay_status.equals("List") == true){


                }
                else if(dDay_status.equals("Setting") == true){


                }
                else if(dDay_status.equals("Add") == true){


                }
            }
        });
        C = new JButton("C");
        C.setBounds(100,300,50,50);
        Dday_label.add(C);
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dDay_status.equals("List") == true){


                }
                else if(dDay_status.equals("Setting") == true){


                }
                else if(dDay_status.equals("Add") == true){


                }
            }
        });
        D = new JButton("D");
        D.setBounds(350,300,50,50);
        Dday_label.add(D);
        D.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(dDay_status.equals("List") == true){


                }
                else if(dDay_status.equals("Setting") == true){


                }
                else if(dDay_status.equals("Add") == true){


                }
            }
        });
        dot = new JLabel();
        dot.setText("05.24.FRI");
        dot.setBounds(150,200,100,30);
        dot.setFont(new Font("돋움",Font.BOLD,15));
        dot.setBorder(new TitledBorder(new LineBorder(Color.BLACK)));
        Dday_label.add(dot);

        segment = new JLabel();

        segment.setText(Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second));
        segment.setBounds(150,230,200,50);
        segment.setBorder(new TitledBorder((new LineBorder(Color.BLACK))));
        segment.setFont(new Font("돋움",Font.BOLD,50));
        Dday_label.add(segment);



    }

}

