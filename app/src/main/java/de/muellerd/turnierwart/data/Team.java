package de.muellerd.turnierwart.data;

import java.io.Serializable;

/**
 * Created by daniel on 26.05.2016.
 */
public class Team implements Serializable {
    private String name;

    public Team(String na){
        name = na;
    }

    public String getName() {
        return name;
    }
}
