import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

class Yansheng implements Runnable{
    long num;
    String name;
    Yansheng(long num) {
        this.num=num;
    }
    public void run() {
        try{
            AudioInputStream ais;
            SourceDataLine sdl;
            try{
                ais = AudioSystem.getAudioInputStream(new File("E:\\流星雨.wav"));
                AudioFormat format = ais.getFormat();
                DataLine.Info info = new DataLine.Info(SourceDataLine.class,format);
                sdl=(SourceDataLine) AudioSystem.getLine(info);
                sdl.open(format);
                sdl.start();
                int bytesRead=0;
                byte[] audioDataBuff = new byte[512];
                while (bytesRead != -1) {
                    bytesRead=ais.read(audioDataBuff,0,audioDataBuff.length);
                    if(bytesRead>=0) sdl.write(audioDataBuff,0,bytesRead);
                }
                sdl.drain();
                sdl.close();
                Thread.sleep(num);
                System.out.println("我是线程一");
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class Meteor_UI extends JFrame {
    //private int width;
    //private int height;
    private static int counts=50;
    private static double[] xxs=new double[100];
    private static double[] yys=new double[100];
    private boolean islefts[]=new boolean[counts];
    private int[] runs=new int[counts];
    private int t=0;

    class Xin  extends JPanel{
        private double[] xls=new double[counts];
        private double[] yls=new double[counts];
        private double[] lengths=new double[counts];
        private double jiao=Math.random()*10+30;
        private boolean ishei;
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(Color.white);
            Graphics2D g2 = (Graphics2D)g;  //g是Graphics对象
            for (int i=0;i<xxs.length;i++) {
                g.fillOval((int)(xxs[i]),(int)(yys[i]),3,3);
            }
            //g.fillOval((int)(xx),(int)(yy),3,3);


//            while (t<counts) {
//                islefts[t]=true;
//                if (yls[t]>Math.random()*200+500) {
//                    t++;
//                }
//            }

            islefts[0]=true;
            if(runs[t]>30) {
                //runs=0;
                t++;
                islefts[t]=true;
            }

            for (int i=0;i<islefts.length;i++) {
                if(islefts[i]==true) {
                        //setLength(getLength()-1);
                        xls[i]-=1;
                        yls[i]+=1;
                        runs[i]++;
                    g2.setStroke(new BasicStroke(2.5f));
                    g.drawLine((int)(xls[i]),(int)(yls[i]),(int)(xls[i]+lengths[i]*Math.cos(jiao)/5),(int)(yls[i]+lengths[i]*Math.sin(jiao)/5));
                    g2.setStroke(new BasicStroke(2.0f));
                    g.drawLine((int)(xls[i]+lengths[i]*Math.cos(jiao)/5),(int)(yls[i]+lengths[i]*Math.sin(jiao)/5),(int)(xls[i]+2*lengths[i]*Math.cos(jiao)/5),(int)(yls[i]+2*lengths[i]*Math.sin(jiao)/5));
                    g2.setStroke(new BasicStroke(1.5f));
                    g.drawLine((int)(xls[i]+2*lengths[i]*Math.cos(jiao)/5),(int)(yls[i]+2*lengths[i]*Math.sin(jiao)/5),(int)(xls[i]+3*lengths[i]*Math.cos(jiao)/5),(int)(yls[i]+3*lengths[i]*Math.sin(jiao)/5));
                    g2.setStroke(new BasicStroke(0.8f));
                    g.drawLine((int)(xls[i]+3*lengths[i]*Math.cos(jiao)/5),(int)(yls[i]+3*lengths[i]*Math.sin(jiao)/5),(int)(xls[i]+4*lengths[i]*Math.cos(jiao)/5),(int)(yls[i]+4*lengths[i]*Math.sin(jiao)/5));
                    g2.setStroke(new BasicStroke(0.1f));
                    g.drawLine((int)(xls[i]+4*lengths[i]*Math.cos(jiao)/5),(int)(yls[i]+4*lengths[i]*Math.sin(jiao)/5),(int)(xls[i]+lengths[i]*Math.cos(jiao)),(int)(yls[i]+lengths[i]*Math.sin(jiao)));
                }
            }

        }
//        public void setLength(double length) {
//            this.length=length;
//        }
//        public double getLength(){
//            return length;
//        }
        Xin() {
            //this.xx=(Math.random()*800+100);
            //this.yy=(Math.random()*800+100);
            for(int i=0;i<islefts.length;i++) {
                xxs[i]=Math.random()*800+100;
                yys[i]=Math.random()*800+100;
            }

            for(int i=0;i<islefts.length;i++) {
                this.xls[i]=Math.random()*800+100;
                this.yls[i]=Math.random()*800+100;
                if (i%3==0) {
                    this.lengths[i]=Math.random()*100+200;

                } else {
                    this.lengths[i]=Math.random()*10+100;

                }

            }
//            this.xl=Math.random()*800+100;
//            this.yl=Math.random()*800+100;
//            this.length=100;
            this.jiao=-120;
            setLocation(0,0);
            setSize(1000,1000);
            setBackground(Color.black);

            Timer timer=new Timer(50, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

//                    while (t<counts) {
//                        islefts[t]=true;
//                        if (yls[t]>Math.random()*200+500) {
//                            t++;
//                        }
//                    }


//                    if(yl<Math.random()*200+700) {
//                        //setLength(getLength()-1);
//                        xl-=1;
//                        yl+=1;
//                    }
                    repaint();
                }
            });timer.start();
        }

        /*public void run() {
            try {
                Xin xin=new Xin();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }*/
    }


    Meteor_UI(int width,int height) {
        //Xin[] xins=new Xin[5];

        /*for (int i=0;i< xxs.length;i++) {
            xxs[i]=(Math.random()*800+100);
            yys[i]=(Math.random()*800+100);
        }*/
        add(new Xin());

//        add(new Xin());
//        add(new Xin());
//        add(new Xin());
//        add(new Xin());
//        add(new Xin());
//        add(new Xin());
//        add(new Xin());
//        add(new Xin());


        /*for(int i=0;i<xins.length;i++) {
            xins[i]=new Xin();
            add(xins[i]);
        }*/
    }

    /*class JP extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.white);
            for(int i=0;i<50;i++) {
                g.fillOval((int)(Math.random()*800+100),(int)(Math.random()*800+100),3,3);
            }
        }
    }*/

    public static void main(String[] args) {
        Yansheng yansheng=new Yansheng(50);
        new Thread(yansheng).start();
        Meteor_UI meteor=new Meteor_UI(1000,1000);
        meteor.setTitle("流星");
        meteor.setSize(1000,1000);
        meteor.setVisible(true);
        meteor.setLocationRelativeTo(null);
        meteor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}





