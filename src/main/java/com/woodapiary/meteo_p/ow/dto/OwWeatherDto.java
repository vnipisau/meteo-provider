/**
 * 2002-2020
 * woodapiary.com
 */

package com.woodapiary.meteo_p.ow.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwWeatherDto implements Serializable {

    /**
     */
    private static final long serialVersionUID = 2212839116570912068L;
    //Weather condition id
    @SerializedName("id")
    @Expose
    private Integer id;
    //Group of weather parameters (Rain, Snow, Extreme etc.)
    @SerializedName("main")
    @Expose
    private String main;
    //Weather condition within the group (full list of weather conditions). Get the output in your language
    @SerializedName("description")
    @Expose
    private String description;
    //Weather icon id. How to get icons
    @SerializedName("icon")
    @Expose
    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
