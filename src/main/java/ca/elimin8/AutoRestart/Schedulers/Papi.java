package ca.elimin8.AutoRestart.Schedulers;

import ca.elimin8.AutoRestart.Restarter;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Papi {
    Restarter plugin;
    int i;
    public Papi(Restarter restarter, int i) {
        plugin = restarter;
        this.i = i;
    }

    public void countDownBukkitRunnable() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                i--;
                if (i < 1) return;
                String coolDownMsg = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("countdownmsg")).replace("%time%", String.valueOf(i));
                String consoleCoolDownMsg = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("countdownmsg")).replace("%time%", String.valueOf(i));
                consoleCoolDownMsg = PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer("CONSOLE"), consoleCoolDownMsg);
                System.out.println(consoleCoolDownMsg);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    coolDownMsg = PlaceholderAPI.setPlaceholders(p, coolDownMsg);
                    p.sendMessage(coolDownMsg);
                }
            }
        }, (long) ((plugin.getConfig().getDouble("time") * 72000) - (i * 20)), 20);
    }
    public void bukkitRunnable() {
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.spigot().restart();
            }
        }.runTaskLater(plugin, (long) (plugin.getConfig().getDouble("time") * 72000));
    }
    public void kickmsgRunnable() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                String kickMsg = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("kickmsg")).replace("%time%", String.valueOf(i));
                for (Player p : Bukkit.getOnlinePlayers()) {
                    kickMsg = PlaceholderAPI.setPlaceholders(p, kickMsg);
                    p.kickPlayer(kickMsg);
                }
            }
        }, (long) ((plugin.getConfig().getDouble("time") * 72000) - 20));
    }
}
