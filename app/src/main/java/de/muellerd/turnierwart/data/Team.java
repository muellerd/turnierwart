package de.muellerd.turnierwart.data;

import java.io.Serializable;

public class Team implements Serializable {
    private String name;
    private Tournament tournament;
    private Group group;
    private long teamDbId;

    public Team(String na, Tournament tour){
        name = na;
        tournament = tour;
    }

    public String getName() {
        return name;
    }

    public void setDbId(long dbId) {
        this.teamDbId = dbId;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }
}
