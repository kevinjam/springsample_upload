package com.sampleimage.upload;

/**
 * Created by kevinjanvier on 15/06/2017.
 */
public enum Status {
    running(0),
    expired(1),
    inactive(2);

    private int id;
    //
    private Status(int campaign_id) {
        this.id = campaign_id;
    }

    public int getId() {
        return id;
    }
}
