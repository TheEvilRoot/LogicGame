package com.theevilroot.logically.common;

import com.theevilroot.logically.common.mouse.selection.State;
import javafx.scene.paint.Paint;

public class Resources {

    public static final Double ELEMENT_WIDTH_UNIT = 100d;

    public static final Double ELEMENT_HEIGHT_PER_WIRE = 60d;

    public static final Double ELEMENT_PORT_RADIUS = 10d;

    public static final Paint ELEMENT_BACKGROUND_COLOR = Paint.valueOf("#ffffff");

    public static final Paint SELECTED_ELEMENT_BACKGROUND_COLOR = Paint.valueOf("#ffffff");

    public static final Paint HOVER_ELEMENT_BACKGROUND_COLOR = Paint.valueOf("#ffffff");

    public static final Paint ELEMENT_STROKE_COLOR = Paint.valueOf("#ffffff4A");

    public static final Paint SELECTED_ELEMENT_STROKE_COLOR = Paint.valueOf("#ffffff4A");

    public static final Paint HOVER_ELEMENT_STROKE_COLOR = Paint.valueOf("#ffffff4A");

    public static final Double ELEMENT_STROKE_WIDTH = 8d;

    public static final Double SELECTED_ELEMENT_STROKE_WIDTH = 8d;

    public static final Double HOVER_ELEMENT_STROKE_WIDTH = 8d;

    public static final Paint ELEMENT_FOREGROUND_COLOR = Paint.valueOf("#000000");

    public static final Paint ELEMENT_PORT_COLOR = Paint.valueOf("#ffffff");

    public static final Paint ELEMENT_TEXT_COLOR = Paint.valueOf("#000000");

    public static final Paint CIRCUIT_BACKGROUND_PRIMARY = Paint.valueOf("#424d93");

    public static final Paint CIRCUIT_BACKGROUND_SECONDARY = Paint.valueOf("#ffffff0A");

    public static final Paint PORT_ACTIVE_COLOR = Paint.valueOf("#00ff00");

    public static final Paint PORT_INACTIVE_COLOR = Paint.valueOf("#ff0000");

    public static final Paint ELEMENT_SELECTION_BOX_COLOR = Paint.valueOf("#ffffffAA");

    public static final Double ELEMENT_SELECTION_BOX_WIDTH = 2d;

    public static Paint getElementBackgroundColor(State state) {
        switch (state) {
            case SELECTED:
                return SELECTED_ELEMENT_BACKGROUND_COLOR;
            case HOVER:
                return HOVER_ELEMENT_BACKGROUND_COLOR;
            default:
                return ELEMENT_BACKGROUND_COLOR;
        }
    }

    public static Paint getElementStrokeColor(State state) {
        switch (state) {
            case SELECTED:
                return SELECTED_ELEMENT_STROKE_COLOR;
            case HOVER:
                return HOVER_ELEMENT_STROKE_COLOR;
            default:
                return ELEMENT_STROKE_COLOR;
        }
    }

    public static Double getElementStrokeWidth(State state) {
        switch (state) {
            case SELECTED:
                return SELECTED_ELEMENT_STROKE_WIDTH;
            case HOVER:
                return HOVER_ELEMENT_STROKE_WIDTH;
            default:
                return ELEMENT_STROKE_WIDTH;
        }
    }
}
