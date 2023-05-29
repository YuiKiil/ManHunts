package fr.girlstream.manhunts.commands;

import fr.girlstream.manhunts.main;
import fr.girlstream.manhunts.managers.GameState;
import fr.girlstream.manhunts.managers.lang.Lang;
import fr.girlstream.manhunts.managers.lang.LangValue;
import fr.girlstream.manhunts.managers.teams.Team;
import fr.girlstream.manhunts.managers.teams.TeamManager;
import fr.girlstream.manhunts.managers.teams.TeamUnit;
import fr.girlstream.manhunts.tools.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private final main instance = main.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        /**
         *      /manhunts start | Launch game
         *      /manhunts interrupt | Iterrupt game launching
         *      /manhunts teamaddplayer <team/help> <player>
         *      /manhunts config <config> <value>
         */

        if(s.equalsIgnoreCase("manhunts")){

            if(!commandSender.hasPermission("manhunts.admin")){
                commandSender.sendMessage(Lang.PREFIX_ERROR.get()  + "§f " + Lang.NO_PERMISSION.get());
                return false;
            }

            if(!GameState.isState(GameState.LOBBY)){
                return false;
            }

            if(strings.length < 1){
                commandSender.sendMessage(Lang.PREFIX_ERROR.get()  + "§c Aucun argument donné");
                return false;
            }

            if(strings.length == 1){
                if(strings[0].equalsIgnoreCase("start")){
                    TeamManager teamManager = instance.getTeamManager();
                    if(!teamManager.areAllInTeam()){
                        commandSender.sendMessage(Lang.PREFIX_ERROR.get()  + "§f " + Lang.START_PLAYER_NOT_IN_TEAM.get());
                        return false;
                    }

                    if(teamManager.getHunters().isEmpty() || teamManager.getRunners().isEmpty()){
                        commandSender.sendMessage(Lang.PREFIX_ERROR.get()  + "§f " + Lang.START_TEAM_EMPTY.get());
                        return false;
                    }

                    if(instance.getGame().getStartRunnable().isStarting()){
                        commandSender.sendMessage(Lang.PREFIX_ERROR.get()  + "§f " + Lang.GAME_ALREADY_STARTING.get());
                        return false;
                    }

                    instance.getGame().startCountdown();
                    commandSender.sendMessage(Lang.getPrefix()  + "§f" + Lang.COMMAND_START_LAUNCH.get());

                    return true;
                }

                if(strings[0].equalsIgnoreCase("interrupt")){
                    if(!instance.getGame().getStartRunnable().isStarting()){
                        commandSender.sendMessage(Lang.PREFIX_ERROR.get()  + "§f " + Lang.COMMAND_INTERRUPT_NOT_LAUNCHED.get());
                        return false;
                    }

                    Bukkit.broadcastMessage(Lang.getPrefix()  + "§f" + Lang.COMMAND_INTERRUPT.get());
                    instance.getGame().stopCountdown();
                    for(Player player : Bukkit.getOnlinePlayers()){
                        player.playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 1f, 1f);
                    }
                    return true;

                }
            }

            if(strings[0].equalsIgnoreCase("teamaddplayer")){
                if(strings.length < 2 || strings[1].isEmpty() || strings[1].equalsIgnoreCase("help")){
                    commandSender.sendMessage(Lang.getPrefix()  + "§f" + Lang.TEAM_HELP.get());
                    commandSender.sendMessage(TeamUnit.HUNTERS.getColoredName() + "§7 (hunters)");
                    commandSender.sendMessage(TeamUnit.RUNNERS.getColoredName() + "§7 (runners)");
                    return false;
                }

                if(strings.length < 3 || strings[2].isEmpty()){
                    commandSender.sendMessage(Lang.PREFIX_ERROR.get()  + "§f " + Lang.PLAYER_NOT_CONNECTED_OR_DOESNT_EXIST.get());
                    return false;
                }

                Player player = Bukkit.getPlayerExact(strings[2]);
                if(player == null){
                    commandSender.sendMessage(Lang.PREFIX_ERROR.get()  + "§f " + Lang.PLAYER_NOT_CONNECTED_OR_DOESNT_EXIST.get());
                    return false;
                }

                if(strings[1].equalsIgnoreCase("hunters")){
                    TeamUnit team = TeamUnit.HUNTERS;
                    instance.getTeamManager().join(player, team);
                    Bukkit.broadcastMessage(Lang.getPrefix() + "§f" + Lang.TEAM_BEEN_ADD.get().replace(LangValue.PLAYER.toName(), player.getName()).replace(LangValue.TEAM.toName(), team.getColoredName()));
                    return true;
                }

                if(strings[1].equalsIgnoreCase("runners")){
                    TeamUnit team = TeamUnit.RUNNERS;
                    instance.getTeamManager().join(player, team);
                    Bukkit.broadcastMessage(Lang.getPrefix() + "§f" + Lang.TEAM_BEEN_ADD.get().replace(LangValue.PLAYER.toName(), player.getName()).replace(LangValue.TEAM.toName(), team.getColoredName()));
                    return true;
                }
            }

        }

        return false;
    }
}
