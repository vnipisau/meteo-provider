/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.entity.ow;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.woodapiary.meteo.provider.entity.Source;

/**
 * The persistent class for the wsweather database table.
 *
 */
@Entity
@Table(name = "ow_message")
public class OwMessage implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3593193177301016001L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    private LocalDateTime modified;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "source_id")
    private Source source;
    @OneToOne(optional = true, mappedBy = "message", cascade = CascadeType.ALL)
    private OwFact mfact;
    @OneToMany(mappedBy = "alertId")
    private List<OwAlert> alerts;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public OwFact getMfact() {
        return mfact;
    }

    public void setMfact(OwFact mfact) {
        this.mfact = mfact;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public List<OwAlert> getAlerts() {
        return alerts;
    }

    public void setParts(final List<OwAlert> alerts) {
        this.alerts = alerts;
    }

    public OwAlert addAlert(final OwAlert alert) {
        getAlerts().add(alert);
        alert.setMessage(this);
        return alert;
    }

    public OwAlert removePart(final OwAlert alert) {
        getAlerts().remove(alert);
        alert.setMessage(null);
        return alert;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
