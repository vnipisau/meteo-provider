/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dto.ya;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YaPartDto implements Serializable {

    private static final long serialVersionUID = -562699448803647061L;
    /*   
    part_name   Название времени суток. Возможные значения:
        night — ночь.
        morning — утро.
        day — день.
        evening — вечер.
        Строка.
        */
    @SerializedName("part_name")
    @Expose
    private String partName;
    //temp_min    Минимальная температура для времени суток (°C). Число
    @SerializedName("temp_min")
    @Expose
    private Integer tempMin;
    //temp_max    Максимальная температура для времени суток (°C).    Число
    @SerializedName("temp_max")
    @Expose
    private Integer tempMax;
    //temp_avg    Средняя температура для времени суток (°C). Число
    @SerializedName("temp_avg")
    @Expose
    private Integer tempAvg;
    //feels_like  Ощущаемая температура (°C). Число
    @SerializedName("feels_like")
    @Expose
    private Integer feelsLike;
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
    //prec_mm Прогнозируемое количество осадков (в мм).   Число
    @SerializedName("prec_mm")
    @Expose
    private Float precMm;
    //prec_period Прогнозируемый период осадков (в минутах).  Число
    @SerializedName("prec_period")
    @Expose
    private Integer precPeriod;
    //prec_prob   Вероятность выпадения осадков.  Число
    @SerializedName("prec_prob")
    @Expose
    private Integer precProb;

    public String getPartName() {
        return partName;
    }

    public void setPartName(final String partName) {
        this.partName = partName;
    }

    public Integer getTempMin() {
        return tempMin;
    }

    public void setTempMin(final Integer tempMin) {
        this.tempMin = tempMin;
    }

    public Integer getTempMax() {
        return tempMax;
    }

    public void setTempMax(final Integer tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getTempAvg() {
        return tempAvg;
    }

    public void setTempAvg(final Integer tempAvg) {
        this.tempAvg = tempAvg;
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

    public String getConditionW() {
        return conditionw;
    }

    public void setConditionw(final String conditionw) {
        this.conditionw = conditionw;
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

    public Float getPrecMm() {
        return precMm;
    }

    public void setPrecMm(final Float precMm) {
        this.precMm = precMm;
    }

    public Integer getPrecPeriod() {
        return precPeriod;
    }

    public void setPrecPeriod(final Integer precPeriod) {
        this.precPeriod = precPeriod;
    }

    public Integer getPrecProb() {
        return precProb;
    }

    public void setPrecProb(final Integer precProb) {
        this.precProb = precProb;
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
