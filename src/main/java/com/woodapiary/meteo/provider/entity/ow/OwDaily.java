/**
 * 2002-2020
 * woodapiary.com
 */

package com.woodapiary.meteo.provider.entity.ow;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name = "ow_daily")
public class OwDaily implements Serializable {

    /**
     */
    private static final long serialVersionUID = 2558912614499617185L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dailyId;
    private LocalDateTime dt;
    private LocalDateTime sunrise;
    private LocalDateTime sunset;
    private Double dayTemp;
    private Double minTemp;
    private Double maxTemp;
    private Double nightTemp;
    private Double eveTemp;
    private Double mornTemp;
    private Double dayFeelsLike;
    private Double nightFeelsLike;
    private Double eveFeelsLike;
    private Double mornFeelsLike;
    private Integer pressure;
    private Integer humidity;
    private Double dewPoint;
    private Double windSpeed;
    private Double windGust;
    private Integer windDeg;
    private Integer clouds;
    private Double pop;
    private Double uvi;
    private Integer visibility;
    private Double rain1h;
    private Double snow1h;
    @ManyToMany(cascade = { CascadeType.REFRESH })
    @JoinTable(name = "ow_weather_daily", joinColumns = { @JoinColumn(name = "daily_id") }, inverseJoinColumns = { @JoinColumn(name = "weather_id") })
    private List<OwWeather> weather;
    @OneToOne
    @JoinColumn(name = "message_id", referencedColumnName = "messageId")
    private OwMessage message;

    public OwMessage getMessage() {
        return message;
    }

    public void setMessage(OwMessage message) {
        this.message = message;
    }

    public Long getDailyId() {
        return dailyId;
    }

    public void setDailyId(Long dailyId) {
        this.dailyId = dailyId;
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

    public Double getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(Double dayTemp) {
        this.dayTemp = dayTemp;
    }

    public Double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(Double minTemp) {
        this.minTemp = minTemp;
    }

    public Double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(Double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public Double getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(Double nightTemp) {
        this.nightTemp = nightTemp;
    }

    public Double getEveTemp() {
        return eveTemp;
    }

    public void setEveTemp(Double eveTemp) {
        this.eveTemp = eveTemp;
    }

    public Double getMornTemp() {
        return mornTemp;
    }

    public void setMornTemp(Double mornTemp) {
        this.mornTemp = mornTemp;
    }

    public Double getDayFeelsLike() {
        return dayFeelsLike;
    }

    public void setDayFeelsLike(Double dayFeelsLike) {
        this.dayFeelsLike = dayFeelsLike;
    }

    public Double getNightFeelsLike() {
        return nightFeelsLike;
    }

    public void setNightFeelsLike(Double nightFeelsLike) {
        this.nightFeelsLike = nightFeelsLike;
    }

    public Double getEveFeelsLike() {
        return eveFeelsLike;
    }

    public void setEveFeelsLike(Double eveFeelsLike) {
        this.eveFeelsLike = eveFeelsLike;
    }

    public Double getMornFeelsLike() {
        return mornFeelsLike;
    }

    public void setMornFeelsLike(Double mornFeelsLike) {
        this.mornFeelsLike = mornFeelsLike;
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

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(Double windGust) {
        this.windGust = windGust;
    }

    public Integer getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(Integer windDeg) {
        this.windDeg = windDeg;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Double getPop() {
        return pop;
    }

    public void setPop(Double pop) {
        this.pop = pop;
    }

    public Double getUvi() {
        return uvi;
    }

    public void setUvi(Double uvi) {
        this.uvi = uvi;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Double getRain1h() {
        return rain1h;
    }

    public void setRain1h(Double rain) {
        this.rain1h = rain;
    }

    public Double getSnow1h() {
        return snow1h;
    }

    public void setSnow1h(Double snow) {
        this.snow1h = snow;
    }

    public List<OwWeather> getWeather() {
        return weather;
    }

    public void setWeather(List<OwWeather> weather) {
        this.weather = weather;
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
