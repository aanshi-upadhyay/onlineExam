import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.Exception;
import java.util.Timer;
import java.util.TimerTask;


 class login extends JFrame implements ActionListener
{
    JButton b1;
    JPanel newPanel;
    JLabel userLabel, passLabel;
    final JTextField textField1, textField2;

    login()
    {
        userLabel = new JLabel();
        userLabel.setText("       Username  :");
        textField1 = new JTextField(15);
        passLabel = new JLabel();
        passLabel.setText("    Password :");
        textField2 = new JPasswordField(8);
        b1 = new JButton("    SUBMIT    ");
        newPanel = new JPanel(new GridLayout(3, 1));
        newPanel.add(userLabel);
        newPanel.add(textField1);
        newPanel.add(passLabel);
        newPanel.add(textField2);
        newPanel.add(b1);
        add(newPanel, BorderLayout.CENTER);
        b1.addActionListener(this);
        setTitle("Login Form ");
    }

    public void actionPerformed(ActionEvent ae) {
        String userValue = textField1.getText();
        String passValue = textField2.getText();
        if(!passValue.equals(""))
            new OnlineTestBegin(userValue);
        else{
            textField2.setText("Enter Password");
            actionPerformed(ae);
        }
    }
}
class OnlineTestBegin extends JFrame implements ActionListener
{
    JLabel l;
    JLabel l1;
    JRadioButton jb[]=new JRadioButton[6];
    JButton b1,b2,log;
    ButtonGroup bg;
    int count=0,current=0,x=1,y=1,now=0;
    int m[]=new int[10];
    Timer timer = new Timer();
    OnlineTestBegin(String s)
    {
        super(s);
        l = new JLabel();
        l1 = new JLabel();
        add(l);
        add(l1);
        bg = new ButtonGroup();
        for(int i=0;i<5;i++)
        {
            jb[i]=new JRadioButton();
            add(jb[i]);
            bg.add(jb[i]);
        }
        b1 = new JButton("Save and Next");
        b2 = new JButton("Save for later");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1);add(b2);
        set();
        l.setBounds(30,40,450,20);
        l1.setBounds(20,20,450,20);
        jb[0].setBounds(50,80,100,20);
        jb[1].setBounds(50,110,100,20);
        jb[2].setBounds(50,140,100,20);
        jb[3].setBounds(50,170,100,20);
        b1.setBounds(95,240,140,30);
        b2.setBounds(270,240,150,30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250,100);
        setVisible(true);
        setSize(600,350);
        timer.scheduleAtFixedRate(new TimerTask() {
                int i = 60;
                public void run() {
                    l1.setText("Time left: " + i);
                    i--;
                    if (i<0) {
                        timer.cancel();
                        l1.setText("Time Out");
                    }
                }
            }, 0, 1000);
        }
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==b1)
            {
                if(check())
                    count=count+1;
                current++;
                set();
                if(current==10)
                {
                    b1.setEnabled(false);
                    b2.setText("Result");
                }
            }
            if(e.getActionCommand().equals("Save for later"))
            {
                JButton bk=new JButton("Review"+x);
                bk.setBounds(480,20+30*x,100,30);
                add(bk);
                bk.addActionListener(this);
                m[x]=current;
                x++;
                current++;
                set();
                if(current==9)
                    b2.setText("Result");
                setVisible(false);
                setVisible(true);
            }
            for(int i=0,y=1;i<x;i++,y++)
            {
                if(e.getActionCommand().equals("Review"+y))
                {
                    if(check())
                        count=count+1;
                    now=current;
                    current=m[y];
                    set();
                    ((JButton)e.getSource()).setEnabled(false);
                    current=now;
                }
            }
            if(e.getActionCommand().equals("Result"))
            {
                if(check())
                    count=count+1;
                current++;
                JOptionPane.showMessageDialog(this,"Score ="+count);
                System.exit(0);
            }
        }
        void set()
        {
            jb[4].setSelected(true);
            if(current==0)
            {
                l.setText("Que1: Who is the father of 'C' language?");
                jb[0].setText("Guido van Rossum");jb[1].setText("James Gosling");jb[2].setText("Dennis Ritchie");jb[3].setText("Bjarne Stroustrup");
            }
            if(current==1)
            {
                l.setText("Que:2 All keywords in C are in .....?");
                jb[0].setText("LowerCase letters");jb[1].setText("UpperCase letters");jb[2].setText("CamelCase letters");jb[3].setText("None of the Mentioned");
            }
            if(current==2)
            {
                l.setText("Que:3 What is an example of iteration in 'C'?");
                jb[0].setText("for");jb[1].setText("while");jb[2].setText("do-while");jb[3].setText("all of the mentioned");
            }
            if(current==3)
            {
                l.setText("Que:4 What is #include<stdio.h>?");
                jb[0].setText("Preprocessor directive");jb[1].setText("inclusion directive");jb[2].setText("File inclusion directive");jb[3].setText("None of the mentioned");
            }
            if(current==4)
            {
                l.setText("Que:5 How many number of pointer(*) does C have against a pointr variable declaration?");
                jb[0].setText("7");jb[1].setText("127");jb[2].setText("255");jb[3].setText("No limits");
            }
            if(current==5)
            {
                l.setText("Que:6 In 'C' language, FILE is of which data type?");
                jb[0].setText("int");jb[1].setText("char*");jb[2].setText("struct");jb[3].setText("None of the mentioned");
            }
            if(current==6)
            {
                l.setText("Que:7 What is the sizeof(char) in a 32-bit C complier?");
                jb[0].setText("1 bit");jb[1].setText("2 bits");jb[2].setText("1 Byte");jb[3].setText("2 Bytes");
            }
            if(current==7)
            {
                l.setText("Que:8 The standard header ..... is used for variable list arguments(...) in C?");
                jb[0].setText("<stdio.h>");jb[1].setText("<stdlib.h>");jb[2].setText("<math.h>");jb[3].setText("<stdarg.h");
            }
            if(current==8)
            {
                l.setText("Que:9 Which of the following are C preprocessors?");
                jb[0].setText("#ifdef");jb[1].setText("#define");jb[2].setText("#endif");jb[3].setText("all of the mentioned");
            }
            if(current==9)
            {
                l.setText("Que:10 Functions in 'C'language are always....?");
                jb[0].setText("Internal");jb[1].setText("Extrnal");jb[2].setText("Both Internal and External");jb[3].setText("None of the mentioned");
            }
            l.setBounds(30,40,450,20);
            for(int i=0,j=0;i<=90;i+=30,j++)
                jb[j].setBounds(50,80+i,200,20);
        }
        boolean check()
        {
            if (current == 0)
                return (jb[2].isSelected());
            if (current == 1)
                return (jb[0].isSelected());
            if (current == 2)
                return (jb[3].isSelected());
            if (current == 3)
                return (jb[0].isSelected());
            if (current == 4)
                return (jb[3].isSelected());
            if (current == 5)
                return (jb[2].isSelected());
            if (current == 6)
                return (jb[2].isSelected());
            if (current == 7)
                return (jb[3].isSelected());
            if (current == 8)
                return (jb[3].isSelected());
            if (current == 9)
                return (jb[1].isSelected());
            return false;
        }
    }
    class OnlineExam
    {
    public static void main(String args[])
    {
        try
        {
            login form = new login();
            form.setSize(400,150);
            form.setVisible(true);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}