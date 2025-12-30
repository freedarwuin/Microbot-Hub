package net.runelite.client.plugins.microbot.sailing;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.microbot.PluginConstants;
import net.runelite.client.plugins.microbot.sailing.features.salvaging.SalvagingHighlight;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;

@PluginDescriptor(
	name = PluginConstants.MOCROSOFT + "Sailing",
	description = "Microbot Sailing Plugin",
	tags = {"sailing"},
	authors = { "Mocrosoft" },
	version = MSailingPlugin.version,
	minClientVersion = "2.1.0",
	enabledByDefault = PluginConstants.DEFAULT_ENABLED,
	isExternal = PluginConstants.IS_EXTERNAL,
    cardUrl = "https://chsami.github.io/Microbot-Hub/MSailingPlugin/assets/card.jpg",
    iconUrl = "https://chsami.github.io/Microbot-Hub/MSailingPlugin/assets/icon.jpg"
)
@Slf4j
public class MSailingPlugin extends Plugin {

	static final String version = "1.0.1";

    @Inject
    private SailingConfig config;
    @Provides
    SailingConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(SailingConfig.class);
    }

    @Inject
    private OverlayManager overlayManager;
    @Inject
    private SailingOverlay sailingOverlay;
    @Inject
    private SalvagingHighlight salvagingHighlight;

    @Inject
    private SailingScript sailingScript;

    @Override
    protected void startUp() throws AWTException {
        if (overlayManager != null) {
            overlayManager.add(sailingOverlay);
            overlayManager.add(salvagingHighlight);
        }
        sailingScript.run();
    }

    protected void shutDown() {
        sailingScript.shutdown();
        overlayManager.remove(sailingOverlay);
        overlayManager.remove(salvagingHighlight);
    }
}
