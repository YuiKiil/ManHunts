package fr.girlstream.manhunts.managers;

import fr.girlstream.manhunts.listeners.*;
import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.commands.Commands;
import fr.girlstream.manhunts.scordboard.ScordBoard;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class Registration {
    private Registration() {
        throw new IllegalStateException("Utility Class");
    }

    public static void register() {
        main instance = main.getInstance();
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PlayerJoin(), instance);
        pm.registerEvents(new ServerListPing(), instance);
        pm.registerEvents(new Cancels(), instance);
        pm.registerEvents(new DieEvent(), instance);
        pm.registerEvents(new PlayerChat(), instance);
        pm.registerEvents(new ScordBoard(), instance);


        Commands cmd = new Commands();
        instance.getCommand("manhunts").setExecutor(cmd);
    }

}
