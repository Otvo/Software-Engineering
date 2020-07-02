import javax.swing.*;

public class StartFrame extends Thread {
    static StartFrame startFrame;
    static InitData initData;
    static JudgeState judgeState;
    static SetColor setColor;
    static SetFrame setframe;
    //存放细胞状态
    static boolean[][] stateOne;
    static JPanel[][] jPanel;
    static boolean[][] stateNextOne;
    static int size=30;


    //初始化GUI界面
    public StartFrame(int row, int col) {
        stateOne = new boolean[row][col];
        stateNextOne = new boolean[row][col];
        setframe = new SetFrame(row, col);
        jPanel = setframe.jPanel;
    }

    //主函数
    public static void main(String[] args) {
        //创建游戏对象
        startFrame = new StartFrame(size, size);
        initData = new InitData(stateOne, stateNextOne);
        judgeState = new JudgeState(stateOne, jPanel, stateNextOne);
        setColor = new SetColor(stateOne, jPanel, stateNextOne);
    }

    public static void setSize(int size) {
        StartFrame.size = size;
    }
}