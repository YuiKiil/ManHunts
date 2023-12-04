package fr.girlstream.manhunts.game;

import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.managers.config.Settings;
import fr.girlstream.manhunts.managers.lang.Lang;
import fr.girlstream.manhunts.managers.lang.LangValue;
import fr.girlstream.manhunts.managers.users.User;
import fr.girlstream.manhunts.tools.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StartRunnable extends BukkitRunnable {

    private main instance = main.getInstance();
    private Settings settings = main.getInstance().getSettings();

    private final Game game;
    private boolean starting;
    private int countdown;

    public StartRunnable(Game game) {
        this.game = game;
        this.starting = false;
        this.countdown = 30;
    }

    @Override
    public void run() {
        if(countdown == 30 || countdown == 10 || (countdown <= 5 && countdown >= 1)){
            Bukkit.broadcastMessage(Lang.getPrefix() + "§f" + Lang.STARTING_COUNTDOWN.get().replace(LangValue.TIME.toName(), Integer.toString(countdown)));


            for(Player player : Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HARP, 1f, 1f);
                Title title = new Title("§eLancement", "§eDans §c" + countdown + " §eseconds");
                title.send(player, 1, 3, 1);
            }
        }

        if(countdown == 0){
            Bukkit.broadcastMessage(Lang.getPrefix() + "§f" + Lang.STARTING.get());

            for(Player player : Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1f, 1f);
                Title title = new Title("§eBonne Chance !");
                title.send(player, 1, 3, 1);
                User user = instance.getUserManager().getUser(player).get();
                user.setInGame(true);
            }
            game.startGame();
            cancel();
        }

        countdown--;
    }


    public boolean isStarting() {
        return starting;
    }

    public void setStarting(boolean starting) {
        this.starting = starting;
    }

    public int getCountdown() {
        return countdown;
    }

    public void setCountdown(int countdown) {
        this.countdown = countdown;
    }



}
