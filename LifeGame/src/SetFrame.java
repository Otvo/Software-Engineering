import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.awt.FlowLayout;

public class SetFrame {
    static JFrame iFrame;
    // 游戏迭代速度
    static int speed = 1000;
    // 剩余生命个数
    static int life = 0;
    // 演化步数
    static int step_one = 0;
    myThread thread = null;
    static StartFrame StartFrame;
    // 显示细胞状态面板
    static JPanel[][] jPanel;
    // 模式一（love）
    static int pattern = 0;
    // 显示生命个数面板
    static JPanel panel_1;
    static JLabel number;
    static JLabel step;
    static boolean end = true;
    int row1, col1;
    static boolean enable = true;
    JMenuItem clean;

    public SetFrame(int row, int col) {
        row1 = row;
        col1 = col;
        // 建立窗口
        iFrame = new JFrame("The Game of Life");
        // 初始化边界
        jPanel = new JPanel[row][col];
        iFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        iFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        iFrame.getContentPane().add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        number = new JLabel("Number of remaining lives: " + life + "               ");
        panel.add(number);

        step = new JLabel("step: " + step_one);
        panel.add(step);

        panel.setBackground(Color.red);
        panel_1 = new JPanel();
        iFrame.getContentPane().add(panel_1, BorderLayout.CENTER);
        panel_1.setLayout(new GridLayout(row, col, 2, 2));
        // 初始化所有界面为白色
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                jPanel[i][j] = new JPanel();
                jPanel[i][j].setBackground(Color.white);
                panel_1.add(jPanel[i][j]);
            }
        }

        // 设置图形界面大小
        iFrame.setSize((int) (row * 50.0 / 3), (int) (row * 50.0 / 3));
        iFrame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        iFrame.setJMenuBar(menuBar);

        JMenu choice = new JMenu("choice");
        menuBar.add(choice);

        JMenuItem start = new JMenuItem("start");
        choice.add(start);
        start.addActionListener(new start());

        JMenuItem continue_one = new JMenuItem("continue");
        choice.add(continue_one);
        continue_one.addActionListener(new continue_one());

        JMenuItem stop = new JMenuItem("stop");
        choice.add(stop);
        stop.addActionListener(new stop());

        clean = new JMenuItem("clean");
        choice.add(clean);
        clean.addActionListener(new clean());

        JMenu pattern = new JMenu("pattern");
        menuBar.add(pattern);

        JMenuItem easy = new JMenuItem("easy");
        pattern.add(easy);
        easy.addActionListener(new easy());

        JMenuItem love = new JMenuItem("love");
        love.setEnabled(enable);
        pattern.add(love);
        love.addActionListener(new love());

        JMenuItem arrow = new JMenuItem("arrow");
        arrow.setEnabled(enable);
        pattern.add(arrow);
        arrow.addActionListener(new arrow());

        JMenuItem random = new JMenuItem("random");
        pattern.add(random);
        random.addActionListener(new random());

        JMenuItem customize = new JMenuItem("customize");
        pattern.add(customize);
        customize.addActionListener(new customize());

        JMenu size = new JMenu("size");
        menuBar.add(size);

        JMenuItem size_small = new JMenuItem("small");
        size.add(size_small);
        size_small.addActionListener(new size_small());

        JMenuItem size_middle = new JMenuItem("middle");
        size.add(size_middle);
        size_middle.addActionListener(new size_middle());

        JMenuItem size_large = new JMenuItem("large");
        size.add(size_large);
        size_large.addActionListener(new size_large());

        JMenu speed = new JMenu("speed");
        menuBar.add(speed);

        JMenuItem speed_fast = new JMenuItem("quick");
        speed.add(speed_fast);
        speed_fast.addActionListener(new speed_fast());

        JMenuItem speed_middle = new JMenuItem("middle");
        speed.add(speed_middle);
        speed_middle.addActionListener(new speed_middle());

        JMenuItem speed_low = new JMenuItem("slow");
        speed.add(speed_low);
        speed_low.addActionListener(new speed_low());

        JMenuItem speed_cus = new JMenuItem("customize");
        speed.add(speed_cus);
        speed_cus.addActionListener(new speed_cus());

        iFrame.setVisible(true);

    }

    class myThread extends Thread {
        public myThread() {
        }

        public void run() {
            while (end) {
                life = 0;

                StartFrame.judgeState.Judge();

                try {
                    sleep(speed);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();//NOPMD
                }

                for (int m = 0; m < StartFrame.stateOne.length; m++) {
                    for (int n = 0; n < StartFrame.stateOne[m].length; n++) {
                        if (StartFrame.stateOne[m][n]) {
                            life++;
                        }
                    }
                }
                step_one++;
                number.setText("Number of remaining lives: " + life + "               ");
                step.setText("step: " + step_one);

                SetColor.Paint();

                if (life == 0) {
                    end = false;
                    JOptionPane.showMessageDialog(null, "生命演化结束：\n" + "        所用步数为" + step_one);
                }

            }
        }
    }

    // 控制游戏的开始
    class start implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            // 初始化逻辑地图
            if (pattern == 1) {
                StartFrame.initData.InitOne();
            } else if (pattern == 2) {
                StartFrame.initData.InitTwo();
            } else if (pattern == 3) {

                StartFrame.initData.InitThree();
            } else if (pattern == 4) {
            } else {
                StartFrame.initData.InitZero();
            }
            // 更新地图颜色
            StartFrame.setColor.Paint();
            // 初始化步数和剩余生命个数
            life = 0;
            step_one = 0;
            end = true;
            // 控制线程的开断
            if (thread != null)
                thread.stop();
            thread = new myThread();
            thread.start();
        }
    }

    // 控制游戏的继续
    class continue_one implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            if (thread != null)
                thread.stop();
            thread = new myThread();
            thread.start();
        }

    }

    // 控制游戏的停止
    class stop implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            // 控制线程的开断
            if (thread != null)
                thread.stop();
            thread = null;
        }
    }

    // 清除屏幕
    class clean implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (thread != null)
                thread.stop();
            thread = null;
            StartFrame.initData.InitFive();
            StartFrame.setColor.Paint();
            life = 0;
            step_one = 0;
            end = true;
            number.setText("Number of remaining lives: " + life + "               ");
            step.setText("step: " + step_one);
        }
    }

    // 设置主界面大小（小）
    class size_small implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clean.doClick();
            iFrame.dispose();
            StartFrame.setSize(20);
            SetFrame.enable = false;
            StartFrame.main(null);
        }
    }

    // 设置主界面大小（中）
    class size_middle implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clean.doClick();
            iFrame.dispose();
            StartFrame.setSize(30);
            SetFrame.enable = true;
            StartFrame.main(null);
        }
    }

    // 设置主界面大小（大）
    class size_large implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            clean.doClick();
            iFrame.dispose();
            StartFrame.setSize(50);
            SetFrame.enable = true;
            StartFrame.main(null);
        }
    }

    // 设置生命迭代速度（快速）
    class speed_fast implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            speed = 100;
        }
    }

    // 设置生命迭代速度（中速）
    class speed_middle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            speed = 1000;
        }
    }

    // 设置生命迭代速度（慢速）
    class speed_low implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            speed = 5000;
        }
    }

    // 设置生命迭代速度（自定义）
    class speed_cus implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            new SetSpeed();
        }
    }

    // 模式中 love型
    class love implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            pattern = 1;
        }
    }

    // 模式中 箭头型
    class arrow implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            pattern = 2;
        }
    }

    // 随机模式
    class random implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            pattern = 3;
        }
    }

    // 简单模式
    class easy implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            pattern = 0;
        }
    }

    // 自定义模式
    class customize implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            pattern = 4;
            StartFrame.initData.InitFour(jPanel, row1, col1);
        }
    }
}