package me.menexia.dynafish;

import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.TNTPrimed;
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
Entity ent = event.getEntity();
if (ent instanceof TNTPrimed && (ent.getLocation().getBlock().getRelative(org.bukkit.block.BlockFace.UP).isLiquid())) {
int ic = 9;
List<Block> blocks = event.blockList();
Block block = blocks.get(ic);
Location pos = block.getLocation();
Random random = new Random();
int amt = random.nextInt(32);
ItemStack i = new ItemStack(349);
i.setAmount(amt);
block.getWorld().dropItemNaturally(pos, i);


}


	}
	// End of EntityExplodeEvent method.
	
	
	
	
	
	/*public void onEntityExplode(EntityExplodeEvent event) {
Entity ent = event.getEntity();
if (ent instanceof TNTPrimed && (ent.getLocation().getBlock().getRelative(BlockFace.UP).isLiquid())) {
	World w = event.getLocation().getWorld();
	Random random = new Random();
	int amt = random.nextInt(32);
	ItemStack i = new ItemStack(349);
	i.setAmount(amt);
	w.dropItemNaturally(event.getEntity().getLocation(), i);
}
	}*/
	// End of the OLD onEntityExplode method.
}