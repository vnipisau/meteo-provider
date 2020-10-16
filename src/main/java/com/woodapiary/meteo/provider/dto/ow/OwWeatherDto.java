
package com.woodapiary.meteo.provider.dto.ow;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwWeatherDto implements Serializable {

    /**
     * current.weather.id Weather condition id
    current.weather.main Group of weather parameters (Rain, Snow, Extreme etc.)
    current.weather.description Weather condition within the group (full list of weather conditions). Get the output in your language
    current.weather.icon Weather icon id. How to get icons
     */
    private static final long serialVersionUID = 2212839116570912068L;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("description")
    @Expose
    private String description;
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

}
