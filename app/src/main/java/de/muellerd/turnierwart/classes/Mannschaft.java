package de.muellerd.turnierwart.classes;

import java.io.Serializable;

/**
 * Created by daniel on 26.05.2016.
 */
public class Mannschaft implements Serializable {
    private String name;

    public Mannschaft(String na){
        name = na;
    }

    public String getName() {
        return name;
    }
}
