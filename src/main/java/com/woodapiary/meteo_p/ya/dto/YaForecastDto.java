/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YaForecastDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7768269235017628347L;
    //date Дата прогноза в формате ГГГГ-ММ-ДД.Строка
    @SerializedName("date")
    @Expose
    private String date;
    //date_ts Дата прогноза в формате Unixtime.Число
    @SerializedName("date_ts")
    @Expose
    private Long dateTs;
    //week Порядковый номер недели. Число
    @SerializedName("week")
    @Expose
    private Integer week;
    //sunrise Время восхода Солнца,локальное время(может отсутствовать для полярных регионов).Строка
    @SerializedName("sunrise")
    @Expose
    private String sunrise;
    //sunset Время заката Солнца,локальное время(может отсутствовать для полярных регионов).Строка
    @SerializedName("sunset")
    @Expose
    private String sunset;
    /*
    moon_code Код фазы Луны.Возможные значения:
        0—полнолуние.
        1-3—убывающая Луна.
        4—последняя четверть.
        5-7—убывающая Луна.
        8—новолуние.
        9-11—растущая Луна.
        12—первая четверть.
        13-15—растущая Луна.
        Число
        */
    @SerializedName("moon_code")
    @Expose
    private Integer moonCode;
    /*
    moon_text Текстовый код для фазы Луны.Возможные значения:
        full-moon—полнолуние.
        decreasing-moon—убывающая Луна.
        last-quarter—последняя четверть.
        new-moon—новолуние.
        growing-moon—растущая Луна.
        first-quarter—первая четверть.
        Строка
        */
    @SerializedName("moon_text")
    @Expose
    private String moonText;
    /*
    parts Прогнозы по времени суток.Содержит следующие поля:part_name temp_min temp_max temp_avg feels_like
    icon condition daytime polar wind_speed wind_gust wind_dir pressure_mm pressure_pa humidity prec_mm prec_period prec_prob
    Все прогнозы погоды на время суток имеют одинаковый набор полей.
    Ответ содержит прогноз на 2 ближайших периода.
    Объект
    */
    @SerializedName("parts")
    @Expose
    private List<YaPartDto> parts = null;

    public String getDate() {
        return date;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public Long getDateTs() {
        return dateTs;
    }

    public void setDateTs(final Long dateTs) {
        this.dateTs = dateTs;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(final Integer week) {
        this.week = week;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(final String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(final String sunset) {
        this.sunset = sunset;
    }

    public Integer getMoonCode() {
        return moonCode;
    }

    public void setMoonCode(final Integer moonCode) {
        this.moonCode = moonCode;
    }

    public String getMoonText() {
        return moonText;
    }

    public void setMoonText(final String moonText) {
        this.moonText = moonText;
    }

    public List<YaPartDto> getParts() {
        return parts;
    }

    public void setParts(final List<YaPartDto> parts) {
        this.parts = parts;
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
