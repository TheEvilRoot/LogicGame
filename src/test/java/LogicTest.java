import com.theevilroot.logically.common.elements.LogicInputPanel;
import com.theevilroot.logically.common.elements.LogicOutputPanel;
import com.theevilroot.logically.common.elements.base.LogicAndGate;
import com.theevilroot.logically.common.elements.base.LogicNotGate;
import com.theevilroot.logically.common.elements.base.LogicOrGate;
import com.theevilroot.logically.common.elements.base.LogicXorGate;
import org.junit.Assert;
import org.junit.Test;

public class LogicTest {

    @Test
    public void testNotGate() {
        LogicInputPanel panel = new LogicInputPanel(2).setValues(false, true);
        LogicNotGate not1 = new LogicNotGate();
        LogicNotGate not2 = new LogicNotGate();

        LogicOutputPanel outputPanel = new LogicOutputPanel(2);

        panel.connectPort(0, not1, 0);
        panel.connectPort(1, not2, 0);

        not1.connectPort(0, outputPanel, 0);
        not2.connectPort(0, outputPanel, 1);

        Assert.assertTrue(outputPanel.getValue(0));
        Assert.assertFalse(outputPanel.getValue(1));
    }

    @Test
    public void testAndGate() {
        LogicInputPanel panel = new LogicInputPanel(8).setValues(
                false, false,
                true, false,
                false, true,
                true, true);
        LogicAndGate[] ands = { new LogicAndGate(2),
                new LogicAndGate(2),
                new LogicAndGate(2),
                new LogicAndGate(2) };

        LogicOutputPanel outputPanel = new LogicOutputPanel(4);
        for (int i = 0, and = 0; i < 8 && and < 4; i += 2, and++) {
            panel.connectPort(i, ands[and], 0);
            panel.connectPort(i + 1, ands[and], 1);
            ands[and].connectPort(0, outputPanel, and);
        }
        Assert.assertFalse(outputPanel.getValue(0));
        Assert.assertFalse(outputPanel.getValue(1));
        Assert.assertFalse(outputPanel.getValue(2));
        Assert.assertTrue(outputPanel.getValue(3));
    }

    @Test
    public void testOrGate() {
        LogicInputPanel panel = new LogicInputPanel(8).setValues(
                false, false,
                true, false,
                false, true,
                true, true);
        LogicOrGate[] ors = { new LogicOrGate(2),
                new LogicOrGate(2),
                new LogicOrGate(2),
                new LogicOrGate(2) };

        LogicOutputPanel outputPanel = new LogicOutputPanel(4);
        for (int i = 0, or = 0; i < 8 && or < 4; i += 2, or++) {
            panel.connectPort(i, ors[or], 0);
            panel.connectPort(i + 1, ors[or], 1);
            ors[or].connectPort(0, outputPanel, or);
        }

        Assert.assertFalse(outputPanel.getValue(0));
        Assert.assertTrue(outputPanel.getValue(1));
        Assert.assertTrue(outputPanel.getValue(2));
        Assert.assertTrue(outputPanel.getValue(3));
    }


    @Test
    public void testXorGate() {
        LogicInputPanel panel = new LogicInputPanel(8).setValues(
                false, false,
                true, false,
                false, true,
                true, true);
        LogicXorGate[] xors = { new LogicXorGate(),
                new LogicXorGate(),
                new LogicXorGate(),
                new LogicXorGate() };

        LogicOutputPanel outputPanel = new LogicOutputPanel(4);
        for (int i = 0, xor = 0; i < 8 && xor < 4; i += 2, xor++) {
            panel.connectPort(i, xors[xor], 0);
            panel.connectPort(i + 1, xors[xor], 1);
            xors[xor].connectPort(0, outputPanel, xor);
        }
        Assert.assertFalse(outputPanel.getValue(0));
        Assert.assertTrue(outputPanel.getValue(1));
        Assert.assertTrue(outputPanel.getValue(2));
        Assert.assertFalse(outputPanel.getValue(3));
    }

}
