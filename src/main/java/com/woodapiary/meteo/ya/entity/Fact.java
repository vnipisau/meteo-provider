/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "fact")
public class Fact implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1561022396921610712L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long factId;
    private Integer temp;
    private Integer feelsLike;
    private Integer tempWater;
    private String icon;
    private String conditionw;
    private Double windSpeed;
    private Double windGust;
    private String windDir;
    private Integer pressureMm;
    private Integer pressurePa;
    private Integer humidity;
    private String daytime;
    private Boolean polar;
    private String season;
    private LocalDateTime obsTime;
    @OneToOne
    @JoinColumn(name = "message_id", referencedColumnName = "messageId")
    private Message message;

    public Long getFactId() {
        return factId;
    }

    public void setFactId(final Long factId) {
        this.factId = factId;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(final Integer temp) {
        this.temp = temp;
    }

    public Integer getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(final Integer feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Integer getTempWater() {
        return tempWater;
    }

    public void setTempWater(Integer tempWater) {
        this.tempWater = tempWater;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }

    public String getConditionw() {
        return conditionw;
    }

    public void setConditionw(final String conditionw) {
        this.conditionw = conditionw;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(final Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(final Double windGust) {
        this.windGust = windGust;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(final String windDir) {
        this.windDir = windDir;
    }

    public Integer getPressureMm() {
        return pressureMm;
    }

    public void setPressureMm(final Integer pressureMm) {
        this.pressureMm = pressureMm;
    }

    public Integer getPressurePa() {
        return pressurePa;
    }

    public void setPressurePa(final Integer pressurePa) {
        this.pressurePa = pressurePa;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(final Integer humidity) {
        this.humidity = humidity;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(final String daytime) {
        this.daytime = daytime;
    }

    public Boolean getPolar() {
        return polar;
    }

    public void setPolar(final Boolean polar) {
        this.polar = polar;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(final String season) {
        this.season = season;
    }

    public LocalDateTime getObsTime() {
        return obsTime;
    }

    public void setObsTime(final LocalDateTime obsTime) {
        this.obsTime = obsTime;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(final Message message) {
        this.message = message;
        message.setMfact(this);
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
