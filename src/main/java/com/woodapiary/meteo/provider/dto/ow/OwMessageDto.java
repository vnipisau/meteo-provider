/**
 * 2002-2020
 * woodapiary.com
 */

package com.woodapiary.meteo.provider.dto.ow;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwMessageDto implements Serializable {

    /**
     * lat Geographical coordinates of the location (latitude)
    lon Geographical coordinates of the location (longitude)
    timezone Timezone name for the requested location
    timezone_offset Shift in seconds from UTC
    current Current weather data API response
    hourly Hourly forecast weather data API response
    daily Daily forecast weather data API response
    alerts Government weather alerts data from major national weather warning systems
     */
    private static final long serialVersionUID = 7018276095573932055L;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("timezone_offset")
    @Expose
    private Integer timezoneOffset;
    @SerializedName("current")
    @Expose
    private OwCurrentDto current;
    @SerializedName("hourly")
    @Expose
    private List<OwHourlyDto> hourly = null;
    @SerializedName("daily")
    @Expose
    private List<OwDailyDto> daily = null;
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

}
