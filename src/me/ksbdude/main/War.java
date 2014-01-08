package me.ksbdude.main;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class War extends JavaPlugin implements Listener{
	public void onEnable(){
		Bukkit.getServer().getPluginManager().registerEvents(new Events(), this);
	}
	public void PlayerJoinEvent(PlayerEvent p){
		p.getPlayer().setLevel(5);
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		BarAPI.setMessage(e.getEntity().getPlayer(), "You have " + e.getEntity().getPlayer().getLevel() + ChatColor.YELLOW + " lives");
		LivingEntity p = e.getEntity();
		if(p.getKiller() instanceof Player) {
			Player k = p.getKiller();
			Player d =  e.getEntity().getPlayer();
			{
				k.playSound(p.getKiller().getLocation(), Sound.BAT_DEATH, 5, 1);
				k.sendMessage(ChatColor.BLACK + "[" + ChatColor.DARK_RED + "War Zone" + ChatColor.BLACK + "]: " + ChatColor.GREEN + "You " + ChatColor.RED + "killed " + ChatColor.GREEN + "a Player!");
				d.sendMessage(ChatColor.DARK_RED + "[WarZone]" + k + ChatColor.RED +"Killed you!");
				e.setDeathMessage(d + " was killed by " + k);
				d.playSound(p.getKiller().getLocation(), Sound.BLAZE_DEATH, 5, 1);
				d.setLevel(-1);
			}
			if(d.getLevel() == 0){
				e.setDeathMessage(d + " was eliminated by " + k);
				k.sendMessage("You eliminated " + d);
			}
		}
	}
}
