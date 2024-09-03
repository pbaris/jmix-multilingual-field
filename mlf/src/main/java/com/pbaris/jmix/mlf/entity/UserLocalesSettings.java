package com.pbaris.jmix.mlf.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.jmix.appsettings.defaults.AppSettingsDefault;
import io.jmix.appsettings.entity.AppSettingsEntity;
import io.jmix.core.metamodel.annotation.InstanceName;
import io.jmix.core.metamodel.annotation.JmixEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@Getter
@Setter
@JmixEntity
@Table(name = "MLF_USER_LOCALES_SETTINGS")
@Entity(name = "mlf_UserLocalesSettings")
public class UserLocalesSettings extends AppSettingsEntity {

    @InstanceName
    @AppSettingsDefault("en")
    @Column(name = "LOCALES")
    private String locales;

    @CreatedBy
    @Column(name = "CREATED_BY", length = 50)
    private String createdBy;

    @CreatedDate
    @Column(name = "CREATE_TS")
    private Date createTs;

    @LastModifiedBy
    @Column(name = "UPDATED_BY", length = 50)
    private String updatedBy;

    @LastModifiedDate
    @Column(name = "UPDATE_TS")
    private Date updateTs;

    public List<String> getLocalesList() {
        if (StringUtils.isNotBlank(locales)) {
            return Arrays.stream(locales.split(","))
                .map(String::trim)
                .filter(StringUtils::isNotBlank)
                .toList();
        }

        return Collections.emptyList();
    }
}