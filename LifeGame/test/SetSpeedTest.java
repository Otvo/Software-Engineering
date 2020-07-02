import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SetSpeedTest {
    SetSpeed setSpeed;

    @Before
    public void setUp() throws Exception {
        setSpeed = new SetSpeed();
    }

    @Test
    // 普通测试
    public void test1() {
        setSpeed.speedText.setText("2000");
        setSpeed.confirm.doClick();
        assertEquals(setSpeed.time, 2000);
    }

    @Test
    // 错误测试1
    public void test2() {
        boolean flag = true;
        setSpeed.speedText.setText("0");
        setSpeed.confirm.doClick();
        if (setSpeed.dialog1.isVisible()) {
            flag = false;
            setSpeed.dialog1.setVisible(false);
            setSpeed.dialog1.dispose();
        }
        assertFalse(flag);
    }

    @Test
    // 错误测试2
    public void test3() {
        boolean flag = true;
        setSpeed.speedText.setText("good");
        setSpeed.confirm.doClick();
        if (setSpeed.dialog2.isVisible()) {
            flag = false;
            setSpeed.dialog2.setVisible(false);
            setSpeed.dialog2.dispose();
        }
        assertFalse(flag);
    }
}
