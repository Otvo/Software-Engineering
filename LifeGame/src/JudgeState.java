import java.awt.Color;

import javax.swing.JPanel;

public class JudgeState {
    static boolean[][] stateOne;
    static boolean[][] stateNext;
    JPanel[][] jPanel;


    // 初始化类
    public JudgeState(boolean[][] state, JPanel[][] jPanelOne, boolean[][] stateNextOne) {
        stateOne = state;
        jPanel = jPanelOne;
        stateNext = stateNextOne;
    }

    // 判断中心生命的状态并更新
    public void Judge() {
        for (int i = 0; i < stateOne.length; i++) {
            for (int j = 0; j < stateOne[i].length; j++) {
                // 循环判断中心生命周围的各个点的状态
                int nCount = 0;
                if (i > 0 && i < stateOne.length && j > 0 && j < stateOne[i].length)
                    if (stateNext[i - 1][j - 1])
                        nCount++;
                if (i > 0 && i < stateOne.length && j >= 0 && j < stateOne[i].length)
                    if (stateNext[i - 1][j])
                        nCount++;
                if (i > 0 && i < stateOne.length && j >= 0 && j < stateOne[i].length - 1)
                    if (stateNext[i - 1][j + 1])
                        nCount++;
                if (i >= 0 && i < stateOne.length && j > 0 && j < stateOne[i].length)
                    if (stateNext[i][j - 1])
                        nCount++;
                if (i >= 0 && i < stateOne.length && j >= 0 && j < stateOne[i].length - 1)
                    if (stateNext[i][j + 1])
                        nCount++;
                if (i >= 0 && i < stateOne.length - 1 && j > 0 && j < stateOne[i].length)
                    if (stateNext[i + 1][j - 1])
                        nCount++;
                if (i >= 0 && i < stateOne.length - 1 && j >= 0 && j < stateOne[i].length)
                    if (stateNext[i + 1][j])
                        nCount++;
                if (i >= 0 && i < stateOne.length - 1 && j >= 0 && j < stateOne[i].length - 1)
                    if (stateNext[i + 1][j + 1])
                        nCount++;

                if (nCount == 3) {
                    stateOne[i][j] = true;
                } else if (nCount == 2) {
                    stateOne[i][j] = stateOne[i][j];
                } else {
                    stateOne[i][j] = false;
                }
            }
        }
    }
}