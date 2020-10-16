
package com.woodapiary.meteo.provider.dto.ow;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwAlertDto implements Serializable {

    /**
     * alerts.sender_name Name of the alert source
    alerts.event Alert event name
    alerts.start Date and time of the start of the alert, Unix, UTC
    alerts.end Date and time of the end of the alert, Unix, UTC
    alerts.description Description of the alert
     */
    private static final long serialVersionUID = 5657108068648895897L;
    @SerializedName("sender_name")
    @Expose
    private String senderName;
    @SerializedName("event")
    @Expose
    private String event;
    @SerializedName("start")
    @Expose
    private Integer start;
    @SerializedName("end")
    @Expose
    private Integer end;
    @SerializedName("description")
    @Expose
    private String description;

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
