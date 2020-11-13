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

public class OwTempDto implements Serializable {

    /**
     */
    private static final long serialVersionUID = 7819531072129905198L;
    //Day temperature.
    @SerializedName("day")
    @Expose
    private Double day;
    //Min daily temperature.
    @SerializedName("min")
    @Expose
    private Double min;
    //Max daily temperature.
    @SerializedName("max")
    @Expose
    private Double max;
    //Night temperature.
    @SerializedName("night")
    @Expose
    private Double night;
    //Evening temperature.
    @SerializedName("eve")
    @Expose
    private Double eve;
    //Morning temperature.
    @SerializedName("morn")
    @Expose
    private Double morn;

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }

    public Double getEve() {
        return eve;
    }

    public void setEve(Double eve) {
        this.eve = eve;
    }

    public Double getMorn() {
        return morn;
    }

    public void setMorn(Double morn) {
        this.morn = morn;
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
