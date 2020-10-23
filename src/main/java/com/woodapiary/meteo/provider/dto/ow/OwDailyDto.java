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

public class OwDailyDto implements Serializable {

    /**
     */
    private static final long serialVersionUID = 2558912614499617185L;
    //Time of the forecasted data, Unix, UTC
    @SerializedName("dt")
    @Expose
    private Integer dt;
    //Sunrise time, Unix, UTC
    @SerializedName("sunrise")
    @Expose
    private Integer sunrise;
    //Sunset time, Unix, UTC
    @SerializedName("sunset")
    @Expose
    private Integer sunset;
    //Units – default: kelvin, metric: Celsius, imperial: Fahrenheit.
    @SerializedName("temp")
    @Expose
    private OwTempDto temp;
    //This accounts for the human perception of weather. Units – default: kelvin, metric
    @SerializedName("feels_like")
    @Expose
    private OwFeelsLikeDto feelsLike;
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
    //Wind speed. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour. 
    @SerializedName("wind_speed")
    @Expose
    private Double windSpeed;
    //Wind gust. Units – default: metre/sec, metric: metre/sec, imperial: miles/hour
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
    //Cloudiness, %
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    //Probability of precipitation
    @SerializedName("pop")
    @Expose
    private Double pop;
    //Midday UV index
    @SerializedName("uvi")
    @Expose
    private Double uvi;
    //Average visibility, metres
    @SerializedName("visibility")
    @Expose
    private Integer visibility;
    //Precipitation volume, mm
    @SerializedName("rain")
    @Expose
    private Double rain;
    //Precipitation volume, mm
    @SerializedName("snow")
    @Expose
    private Double snow;

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

    public Double getSnow() {
        return snow;
    }

    public void setSnow(Double snow) {
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
