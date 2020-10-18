/**
 * 2002-2020
 * woodapiary.com
 */

package com.woodapiary.meteo.provider.dto.ow;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwHourlyDto implements Serializable {

    /**
     * hourly.dt Time of the forecasted data, Unix, UTC
    hourly.temp Temperature. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit. How to change units used
    hourly.feels_like Temperature. This accounts for the human perception of weather. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    hourly.pressure Atmospheric pressure on the sea level, hPa
    hourly.humidity Humidity, %
    hourly.dew_point Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    hourly.clouds Cloudiness, %
    hourly.visibility Average visibility, metres
    hourly.wind_speed Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour.How to change units used
    hourly.wind_gust (where available) Wind gust. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour. How to change units used
    chourly.wind_deg Wind direction, degrees (meteorological)
    hourly.pop Probability of precipitation
    hourly.rain
    hourly.rain.1h (where available) Rain volume for last hour, mm
    hourly.snow
    hourly.snow.1h (where available) Snow volume for last hour, mm
     */
    private static final long serialVersionUID = 3708410789004563386L;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("feels_like")
    @Expose
    private Double feelsLike;
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("dew_point")
    @Expose
    private Double dewPoint;
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    @SerializedName("wind_speed")
    @Expose
    private Double windSpeed;
    @SerializedName("wind_deg")
    @Expose
    private Integer windDeg;
    @SerializedName("weather")
    @Expose
    private List<OwWeatherDto> weather = null;
    @SerializedName("pop")
    @Expose
    private Double pop;
    @SerializedName("rain")
    @Expose
    private OwRainDto rain;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
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

}
