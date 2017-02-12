package com.hello.android.srinivas.personalorganizer;

import java.util.ArrayList;

/**
 * Created by srinivas on 2/11/17.
 */

public class crudOperation {

    ArrayList<Information> informations;

    public crudOperation(ArrayList<Information> informations) {
        this.informations = informations;
    }

    // Adding data
    public  boolean addNewInfo(Information information) {
        try {
            informations.add(information);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Getting data
    public ArrayList<Information> getInformations() {
        return informations;
    }

    // Updating data
    public boolean updateInfo(int pos, Information newInfo) {
        try {
            informations.remove(pos);
            informations.add(pos, newInfo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Deleting
    public boolean deleteInfo(int pos) {
        try {
            informations.remove(pos);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 }
