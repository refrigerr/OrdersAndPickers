package org.example;

import java.awt.font.ShapeGraphicAttribute;
import java.time.LocalTime;
import java.util.List;

public class ShopConfiguration {
    //list of pickers
    private List<String> pickers;
    //start of a sift
    private LocalTime pickingStartTime;
    //end of a sift
    private LocalTime pickingEndTime;

    public List<String> getPickers() {
        return pickers;
    }

    public LocalTime getPickingStartTime() {
        return pickingStartTime;
    }

    public LocalTime getPickingEndTime() {
        return pickingEndTime;
    }

    @Override
    public String toString() {
        return "ShopConfiguration{" +
                "pickers=" + pickers +
                ", pickingStartTime=" + pickingStartTime +
                ", pickingEndTime=" + pickingEndTime +
                '}';
    }
}
