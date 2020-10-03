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

public class MessageDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2689995031303075019L;
    //now Время сервера в формате Unixtime.   Число
    @SerializedName("now")
    @Expose
    private Long now;
    //now_dt  Время сервера в UTC.    Строка
    @SerializedName("now_dt")
    @Expose
    private String nowDt;
    //info    Объект информации о населенном пункте.  Объект
    @SerializedName("info")
    @Expose
    private InfoDto info;
    //fact    Объект фактической информации о погоде. Объект
    @SerializedName("fact")
    @Expose
    private FactDto fact;
    //forecast    Объект прогнозной информации о погоде.  Объект
    @SerializedName("forecast")
    @Expose
    private ForecastDto forecast;

    public Long getNow() {
        return now;
    }

    public void setNow(final Long now) {
        this.now = now;
    }

    public String getNowDt() {
        return nowDt;
    }

    public void setNowDt(final String nowDt) {
        this.nowDt = nowDt;
    }

    public InfoDto getInfo() {
        return info;
    }

    public void setInfo(final InfoDto info) {
        this.info = info;
    }

    public FactDto getFact() {
        return fact;
    }

    public void setFact(final FactDto fact) {
        this.fact = fact;
    }

    public ForecastDto getForecast() {
        return forecast;
    }

    public void setForecast(final ForecastDto forecast) {
        this.forecast = forecast;
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
