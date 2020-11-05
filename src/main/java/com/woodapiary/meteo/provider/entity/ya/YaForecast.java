/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.entity.ya;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "ya_forecast")
public class YaForecast implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2610405231438049060L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long forecastId;
    private LocalDate date;
    private LocalDateTime dateTs;
    private Integer week;
    private LocalTime sunrise;
    private LocalTime sunset;
    private Integer moonCode;
    private String moonText;
    @OneToOne
    @JoinColumn(name = "message_id", referencedColumnName = "messageId")
    private YaMessage message;
    @OneToMany(mappedBy = "partId")
    private List<YaPart> parts;

    public Long getForecastId() {
        return forecastId;
    }

    public void setForecastId(final Long forecastId) {
        this.forecastId = forecastId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getDateTs() {
        return dateTs;
    }

    public void setDateTs(final LocalDateTime dateTs) {
        this.dateTs = dateTs;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(final Integer week) {
        this.week = week;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(final LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(final LocalTime sunset) {
        this.sunset = sunset;
    }

    public Integer getMoonCode() {
        return moonCode;
    }

    public void setMoonCode(final Integer moonCode) {
        this.moonCode = moonCode;
    }

    public String getMoonText() {
        return moonText;
    }

    public void setMoonText(final String moonText) {
        this.moonText = moonText;
    }

    public List<YaPart> getParts() {
        return parts;
    }

    public void setParts(final List<YaPart> parts) {
        this.parts = parts;
    }

    public YaPart addPart(final YaPart part) {
        getParts().add(part);
        part.setForecast(this);
        return part;
    }

    public YaPart removePart(final YaPart part) {
        getParts().remove(part);
        part.setForecast(null);
        return part;
    }

    public YaMessage getMessage() {
        return message;
    }

    public void setMessage(final YaMessage message) {
        this.message = message;
        message.setForecast(this);
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
