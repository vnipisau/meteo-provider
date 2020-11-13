/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ws.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsRequestDto implements Serializable {

    private static final long serialVersionUID = 4039647166094692867L;
    /*
    Returns the type of location lookup used for this request. Possible values:
        City
        LatLon
        IP
        Zipcode
        */
    @SerializedName("type")
    @Expose
    private String type;
    //Returns the exact location identifier query used for this request.
    @SerializedName("query")
    @Expose
    private String query;
    //Returns the ISO-Code of the language used for this request
    @SerializedName("language")
    @Expose
    private String language;
    /*
    Returns the unit identifier used for this request:
        m for Metric
        s for Scientific
        f for Fahrenheit
        */
    @SerializedName("unit")
    @Expose
    private String unit;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
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
