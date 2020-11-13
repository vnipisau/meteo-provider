/**
 * 2002-2020
 * woodapiary.com
 */

package com.woodapiary.meteo_p.ow.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwMessageDto implements Serializable {

    /**
     */
    private static final long serialVersionUID = 7018276095573932055L;
    //Geographical coordinates of the location (latitude)
    @SerializedName("lat")
    @Expose
    private Double lat;
    //Geographical coordinates of the location (longitude)
    @SerializedName("lon")
    @Expose
    private Double lon;
    //Timezone name for the requested location
    @SerializedName("timezone")
    @Expose
    private String timezone;
    //timezone_offset Shift in seconds from UTC
    @SerializedName("timezone_offset")
    @Expose
    private Integer timezoneOffset;
    //Current weather data API response
    @SerializedName("current")
    @Expose
    private OwCurrentDto current;
    //Hourly forecast weather data API response
    @SerializedName("hourly")
    @Expose
    private List<OwHourlyDto> hourly = null;
    //Daily forecast weather data API response
    @SerializedName("daily")
    @Expose
    private List<OwDailyDto> daily = null;
    //Government weather alerts data from major national weather warning systems
    @SerializedName("alerts")
    @Expose
    private List<OwAlertDto> alerts = null;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Integer getTimezoneOffset() {
        return timezoneOffset;
    }

    public void setTimezoneOffset(Integer timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    public OwCurrentDto getCurrent() {
        return current;
    }

    public void setCurrent(OwCurrentDto current) {
        this.current = current;
    }

    public List<OwHourlyDto> getHourly() {
        return hourly;
    }

    public void setHourly(List<OwHourlyDto> hourly) {
        this.hourly = hourly;
    }

    public List<OwDailyDto> getDaily() {
        return daily;
    }

    public void setDaily(List<OwDailyDto> daily) {
        this.daily = daily;
    }

    public List<OwAlertDto> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<OwAlertDto> alerts) {
        this.alerts = alerts;
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
