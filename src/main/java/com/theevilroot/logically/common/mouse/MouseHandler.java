package com.theevilroot.logically.common.mouse;

import com.theevilroot.logically.core.math.Vector;
import javafx.scene.input.MouseEvent;

public interface MouseHandler {

    boolean handle(MouseEvent event, Vector relPos, MouseTrace trace);

}
