package gr.netmechanics.jmix.mlf.locales;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Panos Bariamis (pbaris)
 */
@Component("mlstr_AppLifecycleEventListener")
@RequiredArgsConstructor
public class AppLifecycleEventListener {

    private final LocalesProvider localesProvider;

    @EventListener
    public void onApplicationStarted(final ApplicationStartedEvent event) {
        localesProvider.getDefaultLocale();
    }
}
