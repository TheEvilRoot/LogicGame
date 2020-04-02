package com.theevilroot.logically.common.mouse;

import com.theevilroot.logically.common.math.Vector;
import javafx.scene.input.MouseEvent;

public interface MouseHandler {

    boolean handle(MouseEvent event, Vector relPos, MouseTrace trace);

}
