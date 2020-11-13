/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YaInfoDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2923408305406240018L;
    //lat Широта (в градусах).    Число
    @SerializedName("lat")
    @Expose
    private Double lat;
    //lon Долгота (в градусах).   Число
    @SerializedName("lon")
    @Expose
    private Double lon;
    //url Страница населенного пункта на сайте Яндекс.Погода. Строка
    @SerializedName("url")
    @Expose
    private String url;

    public Double getLat() {
        return lat;
    }

    public void setLat(final Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(final Double lon) {
        this.lon = lon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
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
