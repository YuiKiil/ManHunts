package fr.girlstream.manhunts.managers.users;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserManager {

    //TODO rewrite methode isAlive in game and isTeamAlive

    private final Set<User> users = new HashSet<>();

    public void onLogin(Player player){
        users.add(new User(player));
    }

    public Optional<User> getUser(UUID uuid){
        return  users.stream().filter(user -> user.getUuid().toString().equals(uuid.toString())).findFirst();
    }

    public Optional<User> getUser(Player player){
        return getUser(player.getUniqueId());
    }

    public Set<User> getAliveUsers(){
        return users.stream().filter(User::isAlive).collect(Collectors.toSet());
    }

    public Set<User> getSpectatorUsers(){
        return users.stream().filter(user -> !user.isAlive()).collect(Collectors.toSet());
    }

    public void delete(UUID uuid){
        getUser(uuid).ifPresent(users::remove);
    }

    public void delete(Player player){
        delete(player.getUniqueId());
    }

    public Set<User> getUsers() {
        return users;
    }
}
