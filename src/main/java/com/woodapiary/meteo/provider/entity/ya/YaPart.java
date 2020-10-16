/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.entity.ya;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "ya_part")
public class YaPart implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1687189660155358383L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partId;
    private String partName;
    private Integer tempMin;
    private Integer tempMax;
    private Integer tempAvg;
    private Integer feelsLike;
    private String icon;
    private String conditionw;
    private String daytime;
    private Boolean polar;
    private Double windSpeed;
    private Double windGust;
    private String windDir;
    private Integer pressureMm;
    private Integer pressurePa;
    private Integer humidity;
    private Double precMm;
    private Integer precPeriod;
    private Integer precProb;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "forecast_id")
    private YaForecast forecast;

    public Long getPartId() {
        return partId;
    }

    public void setPartId(final Long partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(final String partName) {
        this.partName = partName;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public void setTempMin(final Integer tempMin) {
        this.tempMin = tempMin;
    }

    public Integer getTempMax() {
        return tempMax;
    }

    public void setTempMax(final Integer tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getTempAvg() {
        return tempAvg;
    }

    public void setTempAvg(final Integer tempAvg) {
        this.tempAvg = tempAvg;
    }

    public Integer getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(final Integer feelsLike) {
        this.feelsLike = feelsLike;
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

    public Double getPrecMm() {
        return precMm;
    }

    public void setPrecMm(final Double precMm) {
        this.precMm = precMm;
    }

    public Integer getPrecPeriod() {
        return precPeriod;
    }

    public void setPrecPeriod(final Integer precPeriod) {
        this.precPeriod = precPeriod;
    }

    public Integer getPrecProb() {
        return precProb;
    }

    public void setPrecProb(final Integer precProb) {
        this.precProb = precProb;
    }

    public YaForecast getForecast() {
        return forecast;
    }

    public void setForecast(final YaForecast forecast) {
        this.forecast = forecast;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

}
