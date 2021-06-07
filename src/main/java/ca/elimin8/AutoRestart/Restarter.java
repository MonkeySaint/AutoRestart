package ca.elimin8.AutoRestart;

import ca.elimin8.AutoRestart.Schedulers.NoPapi;
import ca.elimin8.AutoRestart.Schedulers.Papi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Restarter extends JavaPlugin {


    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        int i = getConfig().getInt("countdownstart");
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Papi papi = new Papi(this, i);
            if (getConfig().getBoolean("countdown"))
                papi.countDownBukkitRunnable();
            if (getConfig().getBoolean("kick"))
                papi.kickmsgRunnable();
            papi.bukkitRunnable();
            return;
        }
        NoPapi noPapi = new NoPapi(this, i);
        if (getConfig().getBoolean("countdown"))
            noPapi.countDownBukkitRunnable();
        if (getConfig().getBoolean("kick"))
            noPapi.kickmsgRunnable();
        noPapi.bukkitRunnable();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
