/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.dto;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GetFactsResultDto implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6814981993984095537L;

    List<FactDto> facts;

    public GetFactsResultDto() {

    }

    public GetFactsResultDto(List<FactDto> facts) {
        super();
        this.facts = facts;
    }

    public List<FactDto> getFacts() {
        return facts;
    }

    public void setFacts(List<FactDto> facts) {
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
