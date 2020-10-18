/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dto.ws;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsMessageDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 7401386479201275560L;
    @SerializedName("request")
    @Expose
    private WsRequestDto request;
    @SerializedName("location")
    @Expose
    private WsLocationDto location;
    @SerializedName("current")
    @Expose
    private WsCurrentDto current;

    public WsRequestDto getRequest() {
        return request;
    }

    public void setRequest(WsRequestDto request) {
        this.request = request;
    }

    public WsLocationDto getLocation() {
        return location;
    }

    public void setLocation(WsLocationDto location) {
        this.location = location;
    }

    public WsCurrentDto getCurrent() {
        return current;
    }

    public void setCurrent(WsCurrentDto current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
