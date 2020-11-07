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
import javax.persistence.FetchType;
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
@Table(name = "ow_hourly")
public class OwHourly implements Serializable {

    /**
     */
    private static final long serialVersionUID = 3708410789004563386L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hourlyId;
    private LocalDateTime dt;
    private Double temp;
    private Double feelsLike;
    private Double pressure;
    private Integer humidity;
    private Double dewPoint;
    private Integer clouds;
    private Integer visibility;
    private Double windSpeed;
    private Double windGust;
    private Integer windDeg;
    private Double pop;
    private Double rain1h;
    private Double snow1h;
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = "ow_weather_hourly", joinColumns = { @JoinColumn(name = "hourly_id") }, inverseJoinColumns = { @JoinColumn(name = "weather_id") })
    private List<OwWeather> weather;
    @OneToOne
    @JoinColumn(name = "message_id", referencedColumnName = "messageId")
    private OwMessage message;

    public Long getHourlyId() {
        return hourlyId;
    }

    public void setHourlyId(Long hourlyId) {
        this.hourlyId = hourlyId;
    }

    public LocalDateTime getDt() {
        return dt;
    }

    public void setDt(LocalDateTime dt) {
        this.dt = dt;
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

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
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

    public Double getPop() {
        return pop;
    }

    public void setPop(Double pop) {
        this.pop = pop;
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

    public OwMessage getMessage() {
        return message;
    }

    public void setMessage(OwMessage message) {
        this.message = message;
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
