package me.ksbdude.main;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
 
public class Events implements Listener {
	@EventHandler
	public void onPlayerInteract(final PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_AIR){
			if(e.getItem().getType() == Material.BLAZE_ROD){
				Fireball s = e.getPlayer().launchProjectile(Fireball.class);
				World w = s.getWorld();
				w.playEffect(e.getPlayer().getLocation(), Effect.SMOKE, 10);
			}
			if(e.getItem().getType() == Material.IRON_SPADE){
				Snowball s = e.getPlayer().launchProjectile(Snowball.class);
				World w = s.getWorld();
				w.playEffect(e.getPlayer().getLocation(), Effect.SMOKE, 10);
			}
		}
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		Projectile p = e.getEntity(); // states that we will now be able to call the projectile "p"
		if(p instanceof Snowball){
			Snowball s = (Snowball) p;
			s.getWorld().createExplosion(s.getLocation(), 2.0F); //we create a 10 block explosion where the snowball lands
			for(Entity en : s.getNearbyEntities(5, 30, 5)) { //we get the entities in a 5 block radius of where the snowball hit and call them "en"
				if(en instanceof Player) { // we check that "en" are players
					Player pl = (Player) en; // we call the player "ens" "pl"
					if(!(pl == e.getEntity().getShooter())) pl.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
					//add blindness to all "pls" for 5 seconds. In minecraft, 20 ticks is one second. Therefore 20*5 = 100. 
					//No effect will be added to the shooter.
				}
			}
		}
		if(p instanceof Fireball){
			Fireball f = (Fireball) p;
			f.getWorld().createExplosion(f.getLocation(), 3.0F); //we create a 10 block explosion where the snowball lands
			for(Entity en : f.getNearbyEntities(5, 30, 5)) { //we get the entities in a 5 block radius of where the snowball hit and call them "en"
				if(en instanceof Player) { // we check that "en" are players
					Player pl = (Player) en; // we call the player "ens" "pl"
					if(!(pl == e.getEntity().getShooter())) pl.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
					//add blindness to all "pls" for 5 seconds. In minecraft, 20 ticks is one second. Therefore 20*5 = 100. 
					//No effect will be added to the shooter.
				}
			}
		}
	}
}