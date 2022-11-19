package fr.girlstream.manhunts.managers.lang;

import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.managers.HuntFiles;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum Lang {
    PREFIX,
    PREFIX_ERROR,

    PLAYER_JOIN_LOBBY,

    SERVER_MOTD_LOBBY,
    SERVER_MOTD_INGAME,

    TEAM_NONE,

    TEAM_HUNTERS,
    TEAM_HUNTERS_PREFIX,
    TEAM_HUNTERS_COLOR,

    TEAM_RUNNERS,
    TEAM_RUNNERS_PREFIX,
    TEAM_RUNNERS_COLOR,

    STARTING_COUNTDOWN,
    STARTING,

    NO_PERMISSION,
    START_PLAYER_NOT_IN_TEAM,
    START_TEAM_EMPTY,
    GAME_ALREADY_STARTING,
    COMMAND_START_LAUNCH,
    COMMAND_INTERRUPT_NOT_LAUNCHED,
    COMMAND_INTERRUPT,
    PLAYER_NOT_CONNECTED_OR_DOESNT_EXIST,
    TEAM_HELP,
    TEAM_BEEN_ADD,

    SCOREBOARD_TITLE,
    SCOREBOARD_LOBBY,
    SCOREBOARD_INGAME;

    private static final Map<Lang, String> VALUES = new HashMap<>();

    static {
        for(Lang lang : values()){
            VALUES.put(lang ,lang.getFromFile());
        }

        main.getInstance().getLogger().info("[ManHunts] Lang file readed !");
    }

    public static String getPrefix(){
        return PREFIX.get() + "§f" + " ";
    }

    public String get(){
        return VALUES.get(this);
    }

    private String getFromFile(){
        FileConfiguration config = HuntFiles.LANG.getConfig();
        String key = name().toLowerCase(Locale.ROOT).replace('_', '-');
        String value = config.getString(key);

        if(value == null){
            value = "";
        }
        return ChatColor.translateAlternateColorCodes('&', value);
    }
}
