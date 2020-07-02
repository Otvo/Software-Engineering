import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

public class JudgeStateTest {
    private static boolean[][] state = new boolean[30][30];
    private static JPanel[][] jPanel;
    private static boolean[][] stateNext = new boolean[30][30];
    private static JudgeState judgeState;

    @Before
    public void setUp() throws Exception {
        StartFrame startFrame = new StartFrame(30, 30);
        jPanel = startFrame.setframe.jPanel;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                state[i][j] = false;
            }
        }
    }

    @Test
    // 普通测试
    public void test1() {
        state[9][3] = true;
        state[9][4] = true;
        state[9][6] = true;
        state[9][7] = true;
        state[9][5] = true;
        state[10][5] = true;
        state[11][5] = true;
        state[12][5] = true;
        state[13][5] = true;
        state[14][5] = true;
        state[14][4] = true;
        state[14][6] = true;
        state[14][3] = true;
        state[14][7] = true;
        stateNext[9][3]=true;
        stateNext[9][4]=true;
        stateNext[9][6]=true;
        stateNext[9][7]=true;
        stateNext[9][5]=true;
        stateNext[10][5]=true;
        stateNext[11][5]=true;
        stateNext[12][5]=true;
        stateNext[13][5]=true;
        stateNext[14][5]=true;
        stateNext[14][4]=true;
        stateNext[14][6]=true;
        stateNext[14][3]=true;
        stateNext[14][7]=true;

        judgeState = new JudgeState(state, jPanel,stateNext);
                    judgeState.Judge();
                    for (int i = 0; i < state.length; i++) {
                        for (int j = 0; j < state[i].length; j++) {
                            if (i == 8 || i == 9 || i == 11 || i == 12 || i == 14 || i == 15) {
                                if (j == 4 || j == 5 || j == 6)
                                    assertTrue("wrong", state[i][j]);
                    else
                        assertFalse("wrong", state[i][j]);
                } else
                    assertFalse("wrong", state[i][j]);
            }
        }
    }

    @Test
    // 边缘测试
    public void test2() {
        judgeState = new JudgeState(state, jPanel, stateNext);
        for (int i = 0; i < 30; i++)
            for (int j = 0; j < 30; j++) {
                state[i][j] = true;
                stateNext[i][j]=true;
            }
        judgeState.Judge();
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (i == 0 || i == state.length - 1) {
                    if (j == 0 || j == state[i].length - 1) {
                        assertTrue("wrong", state[i][j]);
                    } else
                        assertFalse("wrong", state[i][j]);
                } else
                    assertFalse("wrong", state[i][j]);
            }
        }
    }

    @Test
    // 错误测试
    public void test3() {
        boolean[][] stateOne = new boolean[30][30];
        for (int i = 0; i < 30; i++)
            for (int j = 0; j < 30; j++)
                stateOne[i][j] = false;
        stateOne[24][24] = true;
        stateOne[25][24] = true;
        stateOne[24][25] = true;
        stateOne[25][25] = true;
        judgeState = new JudgeState(state, jPanel, stateNext);
        judgeState.Judge();
        boolean flag = true;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (state[i][j] != stateOne[i][j])
                    flag = false;
            }
        }
        assertFalse("wrong", flag);
    }
}