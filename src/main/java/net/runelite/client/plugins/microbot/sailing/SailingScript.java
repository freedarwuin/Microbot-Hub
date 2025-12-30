package net.runelite.client.plugins.microbot.sailing;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.plugins.microbot.Microbot;
import net.runelite.client.plugins.microbot.Script;
import net.runelite.client.plugins.microbot.sailing.features.salvaging.SalvagingScript;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SailingScript extends Script {

    private final SailingConfig config;
    private final SalvagingScript salvagingFeature;

	@Inject
	public SailingScript(SailingConfig config, SalvagingScript salvagingFeature) {
		this.config = config;
		this.salvagingFeature = salvagingFeature;
	}

    public boolean run() {
        Microbot.enableAutoRunOn = false;
        mainScheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                if (!Microbot.isLoggedIn()) return;
                if (!super.run()) return;
                long startTime = System.currentTimeMillis();

                if (config.salvaging()) {
                    salvagingFeature.run(config);
                }


                long endTime = System.currentTimeMillis();
                long totalTime = endTime - startTime;
                log.info("Total time for loop {}ms", totalTime);

            } catch (Exception ex) {
                log.trace("Exception in main loop: ", ex);
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
        return true;
    }
    
    @Override
    public void shutdown() {
        super.shutdown();
    }
}
