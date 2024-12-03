package gr.netmechanics.jmix.autoconfigure.mlf;

import gr.netmechanics.jmix.mlf.MultilingualFieldConfiguration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

/**
 * @author Panos Bariamis (pbaris)
 */
@AutoConfiguration
@Import({MultilingualFieldConfiguration.class})
public class MultilingualFieldAutoConfiguration {
}

