package me.menexia.dynafish;

import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.inventory.ItemStack;

public class DFEntityListener extends EntityListener {
	public static DynaFish plugin;
	public DFEntityListener(DynaFish instance) {
		plugin = instance;
	}
	// End of public DFBlockListener method
	
	public void onEntityExplode(EntityExplodeEvent event) {
		if (event.isCancelled()) return;
Entity ent = event.getEntity();
if (ent instanceof TNTPrimed && (ent.getLocation().getBlock().getRelative(org.bukkit.block.BlockFace.UP).isLiquid())) {
Location loc = ent.getLocation();
World w = loc.getWorld();
	
event.setCancelled(true);
w.createExplosion(loc, 0.0F, false);
w.playEffect(loc, Effect.SMOKE, 1);

	/*double x = ent.getLocation().getX();
	double y = ent.getLocation().getY();
	double z = ent.getLocation().getZ();*/
	
}
	}
	// NEW EXPLODE METHOD.
	
	
	/*public void onEntityExplode(EntityExplodeEvent event) {
Entity ent = event.getEntity();
if (ent instanceof TNTPrimed && (ent.getLocation().getBlock().getRelative(org.bukkit.block.BlockFace.UP).isLiquid())) {
	World w = event.getLocation().getWorld();
	Random random = new Random();
	int amt = random.nextInt(32);
	ItemStack i = new ItemStack(349);
	i.setAmount(amt);
	w.dropItemNaturally(event.getEntity().getLocation(), i);
}
	}*/
	// End of the onEntityExplode method.
	
	public void onEntityDamage(EntityDamageEvent event) {
		if (event.isCancelled()) return;
	if (event instanceof EntityDamageByEntityEvent) {
		EntityDamageByEntityEvent ebdeEvent = (EntityDamageByEntityEvent)event;
		Entity damager = ebdeEvent.getDamager();
		Entity damagee = event.getEntity();
		if (damager instanceof TNTPrimed && damagee instanceof Item) {
			event.setCancelled(true);
		}
	}
	}
	
}
