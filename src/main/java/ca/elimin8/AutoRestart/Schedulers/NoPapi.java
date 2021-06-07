package ca.elimin8.AutoRestart.Schedulers;

import ca.elimin8.AutoRestart.Restarter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class NoPapi {
    Restarter plugin;
    int i;
    public NoPapi(Restarter restarter, int i) {
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
                System.out.println(coolDownMsg);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(consoleCoolDownMsg);
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
                for (Player p : Bukkit.getOnlinePlayers()) {
                    String kickMsg = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("kickmsg")).replace("%time%", String.valueOf(i));
                    p.kickPlayer(kickMsg);
                }
            }
        }, (long) ((plugin.getConfig().getDouble("time") * 72000) - 20));
    }
}
