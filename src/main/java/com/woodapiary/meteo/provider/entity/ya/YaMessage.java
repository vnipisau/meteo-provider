/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.entity.ya;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.woodapiary.meteo.provider.entity.Source;

/**
 * The persistent class for the yaweather database table.
 *
 */
@Entity
@Table(name = "ya_message")
public class YaMessage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3593193177301016001L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private LocalDateTime modified;
    private LocalDateTime now;
    private LocalDateTime nowDt;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "source_id")
    private Source source;
    @OneToOne(optional = true, mappedBy = "message", cascade = CascadeType.ALL)
    private YaFact mfact;
    @OneToOne(optional = true, mappedBy = "message", cascade = CascadeType.ALL)
    private YaForecast mforecast;
    private Double lat;
    private Double lon;
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

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(final Long messageId) {
        this.messageId = messageId;
    }

    public LocalDateTime getNowDt() {
        return nowDt;
    }

    public void setNowDt(final LocalDateTime nowDt) {
        this.nowDt = nowDt;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(final Source source) {
        this.source = source;
    }

    public YaFact getMfact() {
        return mfact;
    }

    public void setMfact(final YaFact fact) {
        this.mfact = fact;
    }

    public YaForecast getMforecast() {
        return mforecast;
    }

    public void setMforecast(final YaForecast forecast) {
        this.mforecast = forecast;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public LocalDateTime getNow() {
        return now;
    }

    public void setNow(LocalDateTime now) {
        this.now = now;
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
