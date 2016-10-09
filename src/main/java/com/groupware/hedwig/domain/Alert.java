package com.groupware.hedwig.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Alert.
 */

@Document(collection = "alert")

public class Alert implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("generated_on")
    private String generated_on;

    @Field("version")
    private String version;

    @Field("system_id")
    private String system_id;

    @Field("serial_num") 
    private String serial_num;

    @Field("hostname")
    private String hostname;

    @Field("sequence")
    private Integer sequence;

    @Field("snmp_location")
    private String snmp_location;

    @Field("partner_system_id")
    private String partner_system_id;

    @Field("partner_serial_num")
    private String partner_serial_num;

    @Field("partner_hostname")
    private String partner_hostname;

    @Field("boot_clustered")
    private String boot_clustered;

    @Field("alerts")
    private String alerts;
    
    @Field("asup_type")
    private String asup_type;
    
    @Field("asup_severity")
    private String asup_severity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenerated_on() {
        return generated_on;
    }

    public void setGenerated_on(String generated_on) {
        this.generated_on = generated_on;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSystem_id() {
        return system_id;
    }

    public void setSystem_id(String system_id) {
        this.system_id = system_id;
    }

    public String getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getSnmp_location() {
        return snmp_location;
    }

    public void setSnmp_location(String snmp_location) {
        this.snmp_location = snmp_location;
    }

    public String getPartner_system_id() {
        return partner_system_id;
    }

    public void setPartner_system_id(String partner_system_id) {
        this.partner_system_id = partner_system_id;
    }

    public String getPartner_serial_num() {
        return partner_serial_num;
    }

    public void setPartner_serial_num(String partner_serial_num) {
        this.partner_serial_num = partner_serial_num;
    }

    public String getPartner_hostname() {
        return partner_hostname;
    }

    public void setPartner_hostname(String partner_hostname) {
        this.partner_hostname = partner_hostname;
    }

    public String getBoot_clustered() {
        return boot_clustered;
    }

    public void setBoot_clustered(String boot_clustered) {
        this.boot_clustered = boot_clustered;
    }

    public String getAlerts() {
        return alerts;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }
    
    public String getAsup_type() {
		return asup_type;
	}

	public void setAsup_type(String asup_type) {
		this.asup_type = asup_type;
	}

	public String getAsup_severity() {
		return asup_severity;
	}

	public void setAsup_severity(String asup_severity) {
		this.asup_severity = asup_severity;
	}

	@JsonAnySetter
    public void handleUnknown(String key, Object value) {
      // do something: put to a Map; log a warning, whatever
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Alert alert = (Alert) o;
        if(alert.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, alert.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Alert{" +
            "id=" + id +
            ", generated_on='" + generated_on + "'" +
            ", version='" + version + "'" +
            ", system_id='" + system_id + "'" +
            ", serial_num='" + serial_num + "'" +
            ", hostname='" + hostname + "'" +
            ", sequence='" + sequence + "'" +
            ", snmp_location='" + snmp_location + "'" +
            ", partner_system_id='" + partner_system_id + "'" +
            ", partner_serial_num='" + partner_serial_num + "'" +
            ", partner_hostname='" + partner_hostname + "'" +
            ", boot_clustered='" + boot_clustered + "'" +
            '}';
    }
}
