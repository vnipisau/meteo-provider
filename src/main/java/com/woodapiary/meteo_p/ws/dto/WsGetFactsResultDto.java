/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ws.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class WsGetFactsResultDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6814981993984095537L;

    List<WsCurrentDto> facts;

    public WsGetFactsResultDto() {

    }

    public WsGetFactsResultDto(List<WsCurrentDto> facts) {
        super();
        this.facts = facts;
    }

    public List<WsCurrentDto> getFacts() {
        return facts;
    }

    public void setFacts(List<WsCurrentDto> facts) {
        this.facts = facts;
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
