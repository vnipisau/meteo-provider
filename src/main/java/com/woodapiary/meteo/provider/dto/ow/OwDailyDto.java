
package com.woodapiary.meteo.provider.dto.ow;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwDailyDto implements Serializable {

    /**
     * daily.dt Time of the forecasted data, Unix, UTC
    daily.sunrise Sunrise time, Unix, UTC
    daily.sunset Sunset time, Unix, UTC
    daily.temp Units – default: kelvin, metric: Celsius, imperial: Fahrenheit. How to change units used
    daily.temp.morn Morning temperature.
    daily.temp.day Day temperature.
    daily.temp.eve Evening temperature.
    daily.temp.night Night temperature.
    daily.temp.min Min daily temperature.
    daily.temp.max Max daily temperature.
    daily.feels_like This accounts for the human perception of weather. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit. How to change units used
    daily.feels_like.morn Morning temperature.
    daily.feels_like.day Day temperature.
    daily.feels_like.eve Evening temperature.
    daily.feels_like.night Night temperature.
    daily.pressure Atmospheric pressure on the sea level, hPa
    daily.humidity Humidity, %
    daily.dew_point Atmospheric temperature (varying according to pressure and humidity) below which water droplets begin to condense and dew can form. Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    daily.wind_speed Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour. How to change units used
    daily.wind_gust (where available) Wind gust. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour. How to change units used
    daily.wind_deg Wind direction, degrees (meteorological)
    daily.clouds Cloudiness, %
    daily.uvi Midday UV index
    daily.visibility Average visibility, metres
    daily.pop Probability of precipitation
    daily.rain (where available) Precipitation volume, mm
    daily.snow (where available) Snow volume, mm
     */
    private static final long serialVersionUID = 2558912614499617185L;
    @SerializedName("dt")
    @Expose
    private Integer dt;
    @SerializedName("sunrise")
    @Expose
    private Integer sunrise;
    @SerializedName("sunset")
    @Expose
    private Integer sunset;
    @SerializedName("temp")
    @Expose
    private OwTempDto temp;
    @SerializedName("feels_like")
    @Expose
    private OwFeelsLikeDto feelsLike;
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("dew_point")
    @Expose
    private Double dewPoint;
    @SerializedName("wind_speed")
    @Expose
    private Double windSpeed;
    @SerializedName("wind_deg")
    @Expose
    private Integer windDeg;
    @SerializedName("weather")
    @Expose
    private List<OwWeatherDto> weather = null;
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    @SerializedName("pop")
    @Expose
    private Double pop;
    @SerializedName("uvi")
    @Expose
    private Double uvi;
    @SerializedName("rain")
    @Expose
    private Double rain;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

    public OwTempDto getTemp() {
        return temp;
    }

    public void setTemp(OwTempDto temp) {
        this.temp = temp;
    }

    public OwFeelsLikeDto getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(OwFeelsLikeDto feelsLike) {
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

    public Double getRain() {
        return rain;
    }

    public void setRain(Double rain) {
        this.rain = rain;
    }

}
