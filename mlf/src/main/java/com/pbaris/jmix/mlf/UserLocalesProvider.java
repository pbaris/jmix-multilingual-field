package com.pbaris.jmix.mlf;

import java.util.List;

import com.pbaris.jmix.mlf.entity.UserLocalesSettings;
import io.jmix.appsettings.AppSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

@Component("mlf_UserLocalesProvider")
@ConditionalOnClass(AppSettings.class)
@RequiredArgsConstructor
public class UserLocalesProvider {

    private final AppSettings appSettings;

    public List<String> getUserLocales() {
        appSettings.
        return appSettings.load(UserLocalesSettings.class).getLocalesList();
    }
}