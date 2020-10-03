/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FactDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3314160996635052088L;

    //temp    Температура (°C).   Число
    @SerializedName("temp")
    @Expose
    private Integer temp;
    //feels_like  Ощущаемая температура (°C). Число
    @SerializedName("feels_like")
    @Expose
    private Integer feelsLike;
    //Температура воды (°C). Параметр возвращается для населенных пунктов, где данная информация актуальна.   Число
    @SerializedName("temp_water")
    @Expose
    private Integer tempWater;
    //icon    Код иконки погоды. Иконка доступна по адресу https://yastatic.net/weather/i/icons/blueye/color/svg/<значение из поля icon>.svg. Строка
    @SerializedName("icon")
    @Expose
    private String icon;
    /*
    condition   Код расшифровки погодного описания. Возможные значения:
        clear — ясно.
        partly-cloudy — малооблачно.
        cloudy — облачно с прояснениями.
        overcast — пасмурно.
        partly-cloudy-and-light-rain — небольшой дождь.
        partly-cloudy-and-rain — дождь.
        overcast-and-rain — сильный дождь.
        overcast-thunderstorms-with-rain — сильный дождь, гроза.
        cloudy-and-light-rain — небольшой дождь.
        overcast-and-light-rain — небольшой дождь.
        cloudy-and-rain — дождь.
        overcast-and-wet-snow — дождь со снегом.
        partly-cloudy-and-light-snow — небольшой снег.
        partly-cloudy-and-snow — снег.
        overcast-and-snow — снегопад.
        cloudy-and-light-snow — небольшой снег.
        overcast-and-light-snow — небольшой снег.
        cloudy-and-snow — снег.
        Строка
        */
    @SerializedName("condition")
    @Expose
    private String conditionw;
    //wind_speed  Скорость ветра (в м/с). Число
    @SerializedName("wind_speed")
    @Expose
    private Double windSpeed;
    //wind_gust   Скорость порывов ветра (в м/с). Число
    @SerializedName("wind_gust")
    @Expose
    private Double windGust;
    /*
    wind_dir    Направление ветра. Возможные значения:
    «nw» — северо-западное.
    «n» — северное.
    «ne» — северо-восточное.
    «e» — восточное.
    «se» — юго-восточное.
    «s» — южное.
    «sw» — юго-западное.
    «w» — западное.
    «с» — штиль.
    Строка
    */
    @SerializedName("wind_dir")
    @Expose
    private String windDir;
    //pressure_mm Давление (в мм рт. ст.).    Число
    @SerializedName("pressure_mm")
    @Expose
    private Integer pressureMm;
    //pressure_pa Давление (в гектопаскалях). Число
    @SerializedName("pressure_pa")
    @Expose
    private Integer pressurePa;
    //humidity    Влажность воздуха (в процентах).    Число
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    /*
    daytime Светлое или темное время суток. Возможные значения:
        «d» — светлое время суток.
        «n» — темное время суток.
        Строка
        */
    @SerializedName("daytime")
    @Expose
    private String daytime;
    //polar   Признак полярного дня или ночи. Логический
    @SerializedName("polar")
    @Expose
    private Boolean polar;
    /*
    season  Время года в данном населенном пункте. Возможные значения:
        «summer» — лето.
        «autumn» — осень.
        «winter» — зима.
        «spring» — весна.
        Строка
        */
    @SerializedName("season")
    @Expose
    private String season;
    //obs_time    Время замера погодных данных в формате Unixtime.    Число
    @SerializedName("obs_time")
    @Expose
    private Long obsTime;

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(final Integer temp) {
        this.temp = temp;
    }

    public Integer getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(final Integer feelsLike) {
        this.feelsLike = feelsLike;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(final String icon) {
        this.icon = icon;
    }

    public String getConditionw() {
        return conditionw;
    }

    public void setConditionw(final String conditionw) {
        this.conditionw = conditionw;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(final Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(final Double windGust) {
        this.windGust = windGust;
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(final String windDir) {
        this.windDir = windDir;
    }

    public Integer getPressureMm() {
        return pressureMm;
    }

    public void setPressureMm(final Integer pressureMm) {
        this.pressureMm = pressureMm;
    }

    public Integer getPressurePa() {
        return pressurePa;
    }

    public void setPressurePa(final Integer pressurePa) {
        this.pressurePa = pressurePa;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(final Integer humidity) {
        this.humidity = humidity;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(final String daytime) {
        this.daytime = daytime;
    }

    public Boolean getPolar() {
        return polar;
    }

    public void setPolar(final Boolean polar) {
        this.polar = polar;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(final String season) {
        this.season = season;
    }

    public Long getObsTime() {
        return obsTime;
    }

    public void setObsTime(final Long obsTime) {
        this.obsTime = obsTime;
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
