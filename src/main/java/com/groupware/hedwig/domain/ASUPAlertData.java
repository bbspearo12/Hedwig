package com.groupware.hedwig.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ASUPAlertData.
 */

@Document(collection = "asup_alert_data")
public class ASUPAlertData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("asup_alert_id")
    private String asup_alert_id;

    @Field("asup_alert_file_name")
    private String asup_alert_file_name;

    @Field("asup_alert_file_data")
    private String asup_alert_file_data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAsup_alert_id() {
        return asup_alert_id;
    }

    public void setAsup_alert_id(String asup_alert_id) {
        this.asup_alert_id = asup_alert_id;
    }

    public String getAsup_alert_file_name() {
        return asup_alert_file_name;
    }

    public void setAsup_alert_file_name(String asup_alert_file_name) {
        this.asup_alert_file_name = asup_alert_file_name;
    }

    public String getAsup_alert_file_data() {
        return asup_alert_file_data;
    }

    public void setAsup_alert_file_data(String asup_alert_file_data) {
        this.asup_alert_file_data = asup_alert_file_data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ASUPAlertData aSUPAlertData = (ASUPAlertData) o;
        if(aSUPAlertData.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, aSUPAlertData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ASUPAlertData{" +
            "id=" + id +
            ", asup_alert_id='" + asup_alert_id + "'" +
            ", asup_alert_file_name='" + asup_alert_file_name + "'" +
            ", asup_alert_file_data='" + asup_alert_file_data + "'" +
            '}';
    }
}
