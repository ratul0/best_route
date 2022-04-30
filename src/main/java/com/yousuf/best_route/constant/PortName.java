package com.yousuf.best_route.constant;

import java.util.HashMap;

public class PortName {
    public static final HashMap<Ports, String> LISTOFNAMES = new HashMap<>();
    static {
        LISTOFNAMES.put(Ports.DEHAM, "Hamburg");
        LISTOFNAMES.put(Ports.DEBRV, "Bremerhaven");
    }
}