package fr.girlstream.manhunts.listeners;

import fr.girlstream.manhunts.managers.GameState;
import fr.girlstream.manhunts.main;
import org.bukkit.GameRule;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class Cancels implements Listener {

    private main instance = main.getInstance();

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        e.setCancelled(GameState.isState(GameState.LOBBY));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        e.setCancelled(GameState.isState(GameState.LOBBY, GameState.END));

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        e.setCancelled(GameState.isState(GameState.LOBBY, GameState.END));
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        e.setCancelled(GameState.isState(GameState.LOBBY, GameState.END));
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        e.setCancelled(GameState.isState(GameState.LOBBY, GameState.END));
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e){
        e.setCancelled(GameState.isState(GameState.LOBBY, GameState.END));
    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent e){
        e.setCancelled(GameState.isState(GameState.LOBBY, GameState.END));
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        if(e.toWeatherState()){
            e.setCancelled(true);
            e.getWorld().setStorm(false);
            e.getWorld().setThundering(false);
            e.getWorld().setWeatherDuration(0);
        }
    }

}
