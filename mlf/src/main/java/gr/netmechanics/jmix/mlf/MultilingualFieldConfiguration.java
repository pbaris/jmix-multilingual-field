package gr.netmechanics.jmix.mlf;

import gr.netmechanics.jmix.mlf.component.MultilingualField;
import io.jmix.core.annotation.JmixModule;
import io.jmix.eclipselink.EclipselinkConfiguration;
import io.jmix.flowui.FlowuiConfiguration;
import io.jmix.flowui.sys.registration.ComponentRegistration;
import io.jmix.flowui.sys.registration.ComponentRegistrationBuilder;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Panos Bariamis (pbaris)
 */
@Configuration
@ComponentScan
@ConfigurationPropertiesScan
@JmixModule(dependsOn = {EclipselinkConfiguration.class, FlowuiConfiguration.class})
@PropertySource(name = "gr.netmechanics.jmix.mlf", value = "classpath:/gr/netmechanics/jmix/mlf/module.properties")
public class MultilingualFieldConfiguration {

    @Bean
    public ComponentRegistration multilingualStringField() {
        return ComponentRegistrationBuilder.create(MultilingualField.class)
            .withComponentLoader("multilingualField", MultilingualFieldLoader.class)
            .build();
    }
}
