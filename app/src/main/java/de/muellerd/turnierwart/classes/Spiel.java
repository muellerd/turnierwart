package de.muellerd.turnierwart.classes;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by daniel on 26.05.2016.
 */
public class Spiel {

    private Mannschaft mannschaftA;
    private Mannschaft mannschaftB;
    private Date datum;

    /* enthält am Ende das Ergebnis
        Key "A" für Mannschaft A
        Key "B" für Mannschaft B*/
    private HashMap<String, Integer> ergebnis;

    //wird auf true gesetzt, sobald das Spiel beendet wurde
    private boolean gespielt;

    public Spiel(){
        ergebnis = new HashMap<String, Integer>();
        ergebnis.put("A", 0);
        ergebnis.put("B", 0);
        gespielt = false;
    }
}
