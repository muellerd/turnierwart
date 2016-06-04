package de.muellerd.turnierwart.data;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by daniel on 26.05.2016.
 */
public class Game {

    private Team teamA;
    private Team teamB;
    private Date datum;

    /* at the end, stores the result
        Key "A" for Team A
        Key "B" for Team B*/
    private HashMap<String, Integer> result;

    //true if game is over
    private boolean finished;

    public Game(){
        result = new HashMap<String, Integer>();
        result.put("A", 0);
        result.put("B", 0);
        finished = false;
    }
}
