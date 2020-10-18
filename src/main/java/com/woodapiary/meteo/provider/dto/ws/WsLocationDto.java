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

public class WsLocationDto implements Serializable {

    private static final long serialVersionUID = 8248317316256527238L;

    //Returns the name of the location used for this request.
    @SerializedName("name")
    @Expose
    private String name;
    //Returns the country name associated with the location used for this request.
    @SerializedName("country")
    @Expose
    private String country;
    //Returns the region name associated with the location used for this request.
    @SerializedName("region")
    @Expose
    private String region;
    //Returns the  longitude coordinate associated with the location used for this request.
    @SerializedName("lat")
    @Expose
    private String lat;
    //Returns the latitude coordinate associated with the location used for this request.
    @SerializedName("lon")
    @Expose
    private String lon;
    //Returns the timezone ID associated with the location used for this request. (Example: America/New_York)
    @SerializedName("timezone_id")
    @Expose
    private String timezoneId;
    //Returns the local time of the location used for this request. (Example: 2019-09-07 08:14)
    @SerializedName("localtime")
    @Expose
    private String localtime;
    //Returns the local time (as UNIX timestamp) of the location used for this request. (Example: 1567844040)
    @SerializedName("localtime_epoch")
    @Expose
    private Integer localtimeEpoch;
    //Returns the UTC offset (in hours) of the timezone associated with the location used for this request. (Example: -4.0)
    @SerializedName("utc_offset")
    @Expose
    private String utcOffset;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    public Integer getLocaltimeEpoch() {
        return localtimeEpoch;
    }

    public void setLocaltimeEpoch(Integer localtimeEpoch) {
        this.localtimeEpoch = localtimeEpoch;
    }

    public String getUtcOffset() {
        return utcOffset;
    }

    public void setUtcOffset(String utcOffset) {
        this.utcOffset = utcOffset;
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
