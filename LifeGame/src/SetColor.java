import java.awt.Color;

import javax.swing.JPanel;

public class SetColor {
    static boolean[][] state;
    static JPanel[][] jPanel;
    static boolean[][] stateNext;

    //初始化类
    public SetColor(boolean[][] stateOne, JPanel[][] jPanelOne,boolean[][] stateNextOne) {
        state = stateOne;
        jPanel = jPanelOne;
        stateNext = stateNextOne;
    }

    //设置颜色
    public static void Paint() {
        //循环判断状态设置颜色
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] && !stateNext[i][j]) {
                    stateNext[i][j] = true;
                    jPanel[i][j].setBackground(Color.black);
                } else if (!state[i][j] && stateNext[i][j]) {
                    stateNext[i][j] = false;
                    jPanel[i][j].setBackground(Color.white);
                }
            }
        }
    }
}

