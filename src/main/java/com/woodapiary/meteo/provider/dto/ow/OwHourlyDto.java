/**
 * 2002-2020
 * woodapiary.com
 */

package com.woodapiary.meteo.provider.dto.ow;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwHourlyDto implements Serializable {

    /**
     */
    private static final long serialVersionUID = 3708410789004563386L;
    //Time of the forecasted data, Unix, UTC
    @SerializedName("dt")
    @Expose
    private Long dt;
    //Temperature. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    @SerializedName("temp")
    @Expose
    private Double temp;
    //Temperature. This accounts for the human perception of weather. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    @SerializedName("feels_like")
    @Expose
    private Double feelsLike;
    //Atmospheric pressure on the sea level, hPa
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    //Humidity, %
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    //Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    @SerializedName("dew_point")
    @Expose
    private Double dewPoint;
    //Cloudiness, %
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    //Average visibility, metres
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    //Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour
    @SerializedName("wind_speed")
    @Expose
    private Double windSpeed;
    //Wind gust. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour.
    @SerializedName("wind_gust")
    @Expose
    private Double windGust;
    //Wind direction, degrees (meteorological)
    @SerializedName("wind_deg")
    @Expose
    private Integer windDeg;
    //
    @SerializedName("weather")
    @Expose
    private List<OwWeatherDto> weather = null;
    //Probability of precipitation
    @SerializedName("pop")
    @Expose
    private Double pop;
    //Rain volume 
    @SerializedName("rain")
    @Expose
    private OwRainDto rain;
    //Snow volume
    @SerializedName("snow")
    @Expose
    private OwRainDto snow;

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
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

    public Integer getWindDeg() {
        return windDeg;
    }

    public void setWindDeg(Integer windDeg) {
        this.windDeg = windDeg;
    }

    public List<OwWeatherDto> getWeather() {
        return weather;
    }

    public void setWeather(List<OwWeatherDto> weather) {
        this.weather = weather;
    }

    public Double getPop() {
        return pop;
    }

    public void setPop(Double pop) {
        this.pop = pop;
    }

    public OwRainDto getRain() {
        return rain;
    }

    public void setRain(OwRainDto rain) {
        this.rain = rain;
    }

    public OwRainDto getSnow() {
        return snow;
    }

    public void setSnow(OwRainDto snow) {
        this.snow = snow;
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
