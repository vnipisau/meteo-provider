/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.misc.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SourceDto implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8737177901256236364L;

    private String provider;
    private String geoname;

    public String getGeoname() {
        return geoname;
    }

    public void setGeoname(final String geoname) {
        this.geoname = geoname;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
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
