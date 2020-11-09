/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * The persistent class for the source database table.
 *
 */
//TODO добавить timezone и timezoneoffset
//TODO добавить unit
//TODO разделить на Location и Request
@Entity
@Table(name = "source")
public class Source implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8737177901256236364L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sourceId;
    @Column(nullable = false)
    private String sourceName;
    private LocalDateTime modified;
    @Column(nullable = false)
    private String provider;
    private String apiVersion;
    @Column(nullable = false)
    private String url;
    private String geoname;
    private Double lon;
    private Double lat;
    public Integer geonameId;
    @Column(nullable = false)
    private Boolean enabled;

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(final Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(final String sourceName) {
        this.sourceName = sourceName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String getGeoname() {
        return geoname;
    }

    public void setGeoname(final String geoname) {
        this.geoname = geoname;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(final Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(final Double lat) {
        this.lat = lat;
    }

    public Integer getGeonameId() {
        return geonameId;
    }

    public void setGeonameId(final Integer geonameId) {
        this.geonameId = geonameId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
