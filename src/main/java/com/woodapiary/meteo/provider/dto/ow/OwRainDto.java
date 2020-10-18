/**
 * 2002-2020
 * woodapiary.com
 */

package com.woodapiary.meteo.provider.dto.ow;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OwRainDto implements Serializable {

    /**
     * current.rain.1h (where available) Rain volume for last hour, mm
     */
    private static final long serialVersionUID = -5939472267104943328L;
    @SerializedName("1h")
    @Expose
    private Double _1h;

    public Double get1h() {
        return _1h;
    }

    public void set1h(Double _1h) {
        this._1h = _1h;
    }

}
