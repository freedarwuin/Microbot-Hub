package net.runelite.client.plugins.microbot.CalcifiedRockMiner;

import com.google.inject.Provides;
import net.runelite.api.Skill;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.StatChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.microbot.PluginConstants;
import net.runelite.client.plugins.microbot.CalcifiedRockMiner.CalcifiedRockMinerConfig;
import net.runelite.client.plugins.microbot.CalcifiedRockMiner.CalcifiedRockMinerOverlay;
import net.runelite.client.plugins.microbot.CalcifiedRockMiner.CalcifiedRockMinerScript;
import net.runelite.client.plugins.microbot.util.misc.TimeUtils;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.awt.*;
import java.time.Instant;

@PluginDescriptor(
        name = PluginDescriptor.TaFCat + "Calcified Rock Miner",
        description = "Calcified Rock Miner",
        tags = {"prayer", "rock", "calcified", "taf", "microbot"},
        version = CalcifiedRockMinerPlugin.version,
        minClientVersion = "2.0.13",
        cardUrl = "",
        iconUrl = "",
        enabledByDefault = PluginConstants.DEFAULT_ENABLED,
        isExternal = PluginConstants.IS_EXTERNAL
)
public class CalcifiedRockMinerPlugin extends Plugin {

    public final static String version = "1.1.1";

    private Instant scriptStartTime;
    @Inject
    private net.runelite.client.plugins.microbot.CalcifiedRockMiner.CalcifiedRockMinerConfig config;
    @Inject
    private OverlayManager overlayManager;
    @Inject
    private CalcifiedRockMinerOverlay calcifiedRockMinerOverlay;
    @Inject
    private CalcifiedRockMinerScript calcifiedRockMinerScript;

    protected String getTimeRunning() {
        return scriptStartTime != null ? TimeUtils.getFormattedDurationBetween(scriptStartTime, Instant.now()) : "";
    }

    @Override
    protected void startUp() throws AWTException {
        scriptStartTime = Instant.now();
        if (overlayManager != null) {
            overlayManager.add(calcifiedRockMinerOverlay);
        }
        calcifiedRockMinerScript.run(config);
    }

    @Override
    protected void shutDown() {
        calcifiedRockMinerScript.shutdown();
        overlayManager.remove(calcifiedRockMinerOverlay);
        gameTickCounter = 0;
        lastGainedXpTick = 0;
        calcifiedRockMinerScript.shouldTryMiningAgain = true;
    }

    @Provides
    net.runelite.client.plugins.microbot.CalcifiedRockMiner.CalcifiedRockMinerConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(CalcifiedRockMinerConfig.class);
    }

    private long gameTickCounter = 0;
    private long lastGainedXpTick = 0;
    @Subscribe
    public void onGameTick(GameTick tick)
    {
       gameTickCounter++;
       if (gameTickCounter == Long.MAX_VALUE) {
          gameTickCounter = 0;
       }
       if (lastGainedXpTick == 0) {
           return;
       }
        calcifiedRockMinerScript.shouldTryMiningAgain = lastGainedXpTick + 14 < gameTickCounter;
    }

    @Subscribe
    public void onStatChanged(StatChanged statChanged)
    {
        final Skill skill = statChanged.getSkill();
        if (skill == Skill.MINING) {
            lastGainedXpTick = gameTickCounter;
        }
    }
}
