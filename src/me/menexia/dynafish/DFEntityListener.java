package me.menexia.dynafish;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.inventory.ItemStack;

public class DFEntityListener extends EntityListener {
	public static DynaFish plugin;
	private static Random random = new Random();
	public DFEntityListener(DynaFish instance) {
		plugin = instance;
	}
	
	public void onEntityExplode(EntityExplodeEvent event) {
			if (event.isCancelled()) return;
			for(Player pl: plugin.user) {
				if (plugin.user.contains(pl)) {
Entity ent = event.getEntity();
if (ent instanceof org.bukkit.entity.TNTPrimed && (ent.getLocation().getBlock().getRelative(org.bukkit.block.BlockFace.UP).isLiquid())) {
	if (DynaFish.OVERALL_CHANCE > 0) {
		Location sym = ent.getLocation();
		World inull = sym.getWorld();
		final int ersh = random.nextInt(DynaFish.OVERALL_CHANCE) + 1;
		if (ersh == 1) {
			double x = sym.getX() + random.nextInt(5) - 2;
			double y = sym.getY() + 1;
			double z = sym.getZ() + random.nextInt(5) - 2;
			for (int a=0; a<33; a++) {
				int p = random.nextInt(DynaFish.CHANCE_PER_DROP) + 1;
				// bah, math. chance per drop needs to be somewhat incremental.
				// if user sets chance per drop as 2...
				// TODO: NEED TO MAKE INTO PERCENTAGES.
				if (p == 1) continue;
				inull.dropItemNaturally(new Location(inull, x, y, z), new ItemStack(349, 1));
		}

	} else {

} // end of for loop
} // end of else statement
} // end of if TNTPrimed
} // end of if player is in HashSet
} // end of player for statement
} // end of onEntityExplode method
}