package fr.girlstream.manhunts.managers.teams;

import fr.girlstream.manhunts.managers.lang.Lang;
import org.bukkit.ChatColor;

public enum TeamUnit {
    HUNTERS(Lang.TEAM_HUNTERS.get(), Lang.TEAM_HUNTERS_PREFIX.get() + " ", Lang.TEAM_HUNTERS_COLOR.get()),
    RUNNERS(Lang.TEAM_RUNNERS.get(), Lang.TEAM_RUNNERS_PREFIX.get() + " ", Lang.TEAM_RUNNERS_COLOR.get()),
    NONE(Lang.TEAM_NONE.get(), "§7", "§7");

    private final String name;
    private final String prefix;
    private final String color;

    TeamUnit(String name, String prefix, String color) {
        this.name = name;
        this.prefix = prefix;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return ChatColor.translateAlternateColorCodes('&', prefix);
    }

    public String getColor() {
        return ChatColor.translateAlternateColorCodes('&', color);
    }

    public String getColoredName(){
        return getColor() + name;
    }
}
