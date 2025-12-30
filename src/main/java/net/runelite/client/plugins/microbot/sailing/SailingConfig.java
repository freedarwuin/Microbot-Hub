package net.runelite.client.plugins.microbot.sailing;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

import java.awt.*;

@ConfigGroup(SailingConfig.configGroup)
public interface SailingConfig extends Config {
	String configGroup = "micro-sailing";

	@ConfigSection(
		name = "General",
		description = "General Plugin Settings",
		position = 0
	)
	String generalSection = "general";

	@ConfigSection(
		name = "Salvaging Highlight",
		description = "Shipwreck highlighting settings",
		position = 1
	)
	String highlightSection = "highlight";

	@ConfigItem(
		keyName = "Salvgaging",
		name = "Salvgaging",
		description = "Enable this option to use salvaging.",
		position = 0,
		section = generalSection
	)
	default boolean salvaging()
	{
		return false;
	}

	@ConfigItem(
		keyName = "enableAlching",
		name = "Enable Alching",
		description = "Enable high alching items when inventory is full.",
		position = 1,
		section = generalSection
	)
	default boolean enableAlching()
	{
		return false;
	}

	@ConfigItem(
		keyName = "alchItems",
		name = "Alch items",
		description = "Comma-separated list of items to high alch when salvaging.",
		position = 2,
		section = generalSection
	)
	default String alchItems()
	{
		return "gold ring, sapphire ring, emerald ring, ruby ring, diamond ring, ruby bracelet, emerald bracelet, diamond bracelet, mithril scimitar";
	}

	@ConfigItem(
		keyName = "dropItems",
		name = "Drop items",
		description = "Comma-separated list of items to drop when salvaging.",
		position = 3,
		section = generalSection
	)
	default String dropItems()
	{
		return "casket, oyster pearl, oyster pearls, teak logs, steel nails, mithril nails, giant seaweed, mithril cannonball, adamant cannonball, elkhorn frag, plank, oak plank, hemp seed, flax seed, mahogany repair kit, teak repair kit, rum";
	}

	@ConfigItem(
		keyName = "salvagingHighlight",
		name = "Enable Highlighting",
		description = "Enable shipwreck highlighting overlay.",
		position = 0,
		section = highlightSection
	)
	default boolean salvagingHighlight()
	{
		return true;
	}

	@ConfigItem(
		keyName = "salvagingHighlightActiveWrecks",
		name = "Highlight Active Wrecks",
		description = "Highlight shipwrecks you can salvage.",
		position = 1,
		section = highlightSection
	)
	default boolean salvagingHighlightActiveWrecks()
	{
		return true;
	}

	@Alpha
	@ConfigItem(
		keyName = "salvagingHighlightActiveWrecksColour",
		name = "Active Wrecks Colour",
		description = "Colour for active shipwrecks.",
		position = 2,
		section = highlightSection
	)
	default Color salvagingHighlightActiveWrecksColour()
	{
		return Color.GREEN;
	}

	@ConfigItem(
		keyName = "salvagingHighlightInactiveWrecks",
		name = "Highlight Inactive Wrecks",
		description = "Highlight depleted shipwrecks (stumps).",
		position = 3,
		section = highlightSection
	)
	default boolean salvagingHighlightInactiveWrecks()
	{
		return false;
	}

	@Alpha
	@ConfigItem(
		keyName = "salvagingHighlightInactiveWrecksColour",
		name = "Inactive Wrecks Colour",
		description = "Colour for inactive shipwrecks (stumps).",
		position = 4,
		section = highlightSection
	)
	default Color salvagingHighlightInactiveWrecksColour()
	{
		return Color.GRAY;
	}

	@ConfigItem(
		keyName = "salvagingHighlightHighLevelWrecks",
		name = "Highlight High Level Wrecks",
		description = "Highlight shipwrecks above your sailing level.",
		position = 5,
		section = highlightSection
	)
	default boolean salvagingHighlightHighLevelWrecks()
	{
		return false;
	}

	@Alpha
	@ConfigItem(
		keyName = "salvagingHighLevelWrecksColour",
		name = "High Level Wrecks Colour",
		description = "Colour for shipwrecks above your level.",
		position = 6,
		section = highlightSection
	)
	default Color salvagingHighLevelWrecksColour()
	{
		return Color.RED;
	}
}
