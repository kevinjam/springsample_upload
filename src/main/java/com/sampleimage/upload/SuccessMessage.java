
package com.sampleimage.upload;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SuccessMessage {

    @JsonProperty("status")
    private String mStatus;
    @JsonProperty("message")
    private String mMessage;


    public SuccessMessage() {
    }

    public SuccessMessage(String mStatus, String mMessage) {
        this.mStatus = mStatus;
        this.mMessage = mMessage;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }
}
