/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dto.ws;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WsCurrentDto implements Serializable {

    private static final long serialVersionUID = 8794343058508168997L;

    //Returns the UTC time for when the returned whether data was collected.
    @SerializedName("observation_time")
    @Expose
    private String observationTime;
    //Returns the temperature in the selected unit. (Default: Celsius)
    @SerializedName("temperature")
    @Expose
    private Integer temperature;
    //Returns the universal weather condition code associated with the current weather condition. You can download all available weather codes using this link: Download Weather Codes (ZIP file)
    @SerializedName("weather_code")
    @Expose
    private Integer weatherCode;
    //Returns one or more PNG weather icons associated with the current weather condition.
    @SerializedName("weather_icons")
    @Expose
    private List<String> weatherIcons = null;
    //Returns one or more weather description texts associated with the current weather condition.
    @SerializedName("weather_descriptions")
    @Expose
    private List<String> weatherDescriptions = null;
    //Returns the wind speed in the selected unit. (Default: kilometers/hour)
    @SerializedName("wind_speed")
    @Expose
    private Integer windSpeed;
    //Returns the wind degree.
    @SerializedName("wind_degree")
    @Expose
    private Integer windDegree;
    //Returns the wind direction.
    @SerializedName("wind_dir")
    @Expose
    private String windDir;
    //Returns the air pressure in the selected unit. (Default: MB - millibar)
    @SerializedName("pressure")
    @Expose
    private Integer pressure;
    //Returns the precipitation level in the selected unit. (Default: MM - millimeters)
    @SerializedName("precip")
    @Expose
    private Integer precip;
    //Returns the air humidity level in percentage.
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    //Returns the cloud cover level in percentage.
    @SerializedName("cloudcover")
    @Expose
    private Integer cloudcover;
    //Returns the "Feels Like" temperature in the selected unit. (Default: Celsius)
    @SerializedName("feelslike")
    @Expose
    private Integer feelslike;
    //Returns the UV index associated with the current weather condition
    @SerializedName("uv_index")
    @Expose
    private Integer uvIndex;
    //Returns the visibility level in the selected unit. (Default: kilometers)
    @SerializedName("visibility")
    @Expose
    private Integer visibility;

    public String getObservationTime() {
        return observationTime;
    }

    public void setObservationTime(String observationTime) {
        this.observationTime = observationTime;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(Integer weatherCode) {
        this.weatherCode = weatherCode;
    }

    public List<String> getWeatherIcons() {
        return weatherIcons;
    }

    public void setWeatherIcons(List<String> weatherIcons) {
        this.weatherIcons = weatherIcons;
    }

    public List<String> getWeatherDescriptions() {
        return weatherDescriptions;
    }

    public void setWeatherDescriptions(List<String> weatherDescriptions) {
        this.weatherDescriptions = weatherDescriptions;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Integer windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Integer getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(Integer windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getPrecip() {
        return precip;
    }

    public void setPrecip(Integer precip) {
        this.precip = precip;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(Integer cloudcover) {
        this.cloudcover = cloudcover;
    }

    public Integer getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(Integer feelslike) {
        this.feelslike = feelslike;
    }

    public Integer getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Integer uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
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
