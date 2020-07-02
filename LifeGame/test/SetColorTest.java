import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.*;

public class SetColorTest {
    SetColor setColor;
    boolean flag = true;
    static boolean[][] state = new boolean[30][30];
    static JPanel[][] jPanel;
    static boolean[][] stateNext = new boolean[30][30];

    @Before
    public void setUp() throws Exception {
        StartFrame startFrame = new StartFrame(30, 30);
        jPanel = startFrame.setframe.jPanel;
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                state[i][j] = false;
                stateNext[i][j] = false;
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
        setColor = new SetColor(state, jPanel, stateNext);
        SetColor.Paint();
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if (i == 9 && j >= 3 && j <= 7) {
                    if (!stateNext[i][j])
                        flag = false;
                } else {
                    if (stateNext[i][j])
                        flag = false;
                }
            }
            assertTrue(flag);
        }
    }

    @Test
    // 边缘测试
    public void test2() {
        for (int i = 0; i < state.length; i++)
            for (int j = 0; j < state[i].length; j++)
                state[i][j] = true;
        setColor = new SetColor(state, jPanel, stateNext);
        SetColor.Paint();
        for (int i = 0; i < state.length; i++)
            for (int j = 0; j < state[i].length; j++)
                if(!stateNext[i][j]) {
                    flag=false;
                    break;
                }
        assertTrue(flag);
    }
}
