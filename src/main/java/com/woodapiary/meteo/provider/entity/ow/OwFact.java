/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.entity.ow;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "ow_fact")
public class OwFact implements Serializable {

    private static final long serialVersionUID = -1561022396921610712L;
    /*
    
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long factId;
    private LocalDateTime dt;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    private Double temp;
    private Double feelsLike;
    private Integer pressure;
    private Integer humidity;
    private Double dewPoint;
    private Double uvi;
    private Integer clouds;
    private Integer visibility;
    private Integer windSpeed;
    private Integer windDeg;
    private Integer windGust;
    private Double rain1h;
    private Double snow1h;
    @OneToMany(mappedBy = "weatherId", fetch = FetchType.EAGER)
    private List<OwWeather> weather;
    @OneToOne
    @JoinColumn(name = "message_id", referencedColumnName = "messageId")
    private OwMessage message;

    public Long getFactId() {
        return factId;
    }

    public void setFactId(Long factId) {
        this.factId = factId;
    }

    public OwMessage getMessage() {
        return message;
    }

    public void setMessage(OwMessage message) {
        this.message = message;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
    }

    public LocalDateTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalDateTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalDateTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalDateTime sunset) {
        this.sunset = sunset;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(Double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Double getUvi() {
        return uvi;
    }

    public void setUvi(Double uvi) {
        this.uvi = uvi;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Integer windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(Integer windDeg) {
        this.windDeg = windDeg;
    }

    public Integer getWindGust() {
        return windGust;
    }

    public void setWindGust(Integer windGust) {
        this.windGust = windGust;
    }

    public Double getRain1h() {
        return rain1h;
    }

    public void setRain1h(Double rain1h) {
        this.rain1h = rain1h;
    }

    public Double getSnow1h() {
        return snow1h;
    }

    public void setSnow1h(Double snow1h) {
        this.snow1h = snow1h;
    }

    public List<OwWeather> getWeather() {
        return weather;
    }

    public void setWeather(final List<OwWeather> weather) {
        this.weather = weather;
    }

    public OwWeather addWeather(final OwWeather weather) {
        getWeather().add(weather);
        weather.setFact(this);
        return weather;
    }

    public OwWeather removeWeather(final OwWeather weather) {
        getWeather().remove(weather);
        weather.setFact(null);
        return weather;
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
