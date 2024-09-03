package com.pbaris.jmix.autoconfigure.mlf;

import com.pbaris.jmix.mlf.MultilingualFieldConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({MultilingualFieldConfiguration.class})
public class MultilingualFieldAutoConfiguration {
}

