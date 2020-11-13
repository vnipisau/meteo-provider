/**
 * 2002-2020
 * woodapiary.com
 */

package com.woodapiary.meteo_p.ow.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwRainDto implements Serializable {

    /**
     * current.rain.1h 
     */
    private static final long serialVersionUID = -5939472267104943328L;

    //(where available) Rain volume for last hour, mm
    @SerializedName("1h")
    @Expose
    private Double m1h;

    public Double getM1h() {
        return m1h;
    }

    public void set1h(Double m1h) {
        this.m1h = m1h;
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
