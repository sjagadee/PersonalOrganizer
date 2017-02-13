package com.hello.android.srinivas.personalorganizer;

/**
 * Created by srinivas on 2/2/17.
 */

public class Information {

    String priorityName;
    String itemName;
    int id;

    public Information(int id, String itemName, String priorityName) {
        this.priorityName = priorityName;
        this.itemName = itemName;
        this.id = id;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
