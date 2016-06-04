package de.muellerd.turnierwart.data;



/**
 * Created by daniel on 04.06.2016.
 */
public class GroupTuple {
    private String name;
    private String description;

    public GroupTuple(String n, String d){
        name = n;
        description = d;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
