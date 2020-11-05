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

public class OwCurrentDto implements Serializable {

    /**
    
     */
    private static final long serialVersionUID = -2449923890786053212L;
    //Current time, Unix, UTC
    @SerializedName("dt")
    @Expose
    private Long dt;
    //Sunrise time, Unix, UTC
    @SerializedName("sunrise")
    @Expose
    private Long sunrise;
    //Sunset time, Unix, UTC
    @SerializedName("sunset")
    @Expose
    private Long sunset;
    //Temperature. Units - default: kelvin, metric: Celsius, imperial: Fahrenheit.
    @SerializedName("temp")
    @Expose
    private Double temp;
    //Temperature. This temperature parameter accounts for the human perception of weather. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
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
    //UV index
    @SerializedName("uvi")
    @Expose
    private Double uvi;
    //Cloudiness, %
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    //Average visibility, metres
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    //Wind speed. Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour. How to change units used
    @SerializedName("wind_speed")
    @Expose
    private Double windSpeed;
    //Wind direction, degrees (meteorological)
    @SerializedName("wind_deg")
    @Expose
    private Integer windDeg;
    //Wind gust. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour
    @SerializedName("wind_gust")
    @Expose
    private Integer windGust;
    @SerializedName("weather")
    @Expose
    private List<OwWeatherDto> weather = null;
    //Rain volume for last hour, mm
    @SerializedName("rain")
    @Expose
    private OwRainDto rain;
    //Snow volume for last hour, mm
    @SerializedName("snow")
    @Expose
    private OwRainDto snow;

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
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

    public Integer getWindGust() {
        return windGust;
    }

    public void setWindGust(Integer windGust) {
        this.windGust = windGust;
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
