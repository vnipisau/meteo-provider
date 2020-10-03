/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.entity;

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

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "forecast")
public class Forecast implements Serializable {

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
    private Message message;
    @OneToMany(mappedBy = "partId")
    private List<Part> parts;

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

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(final List<Part> parts) {
        this.parts = parts;
    }

    public Part addPart(final Part part) {
        getParts().add(part);
        part.setForecast(this);
        return part;
    }

    public Part removePart(final Part part) {
        getParts().remove(part);
        part.setForecast(null);
        return part;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(final Message message) {
        this.message = message;
        message.setMforecast(this);
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
