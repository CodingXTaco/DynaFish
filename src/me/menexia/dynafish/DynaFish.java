package me.menexia.dynafish;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class DynaFish extends JavaPlugin {
	public static DynaFish plugin;
	
	public final Logger logger = Logger.getLogger("Minecraft");
	public Set<Player> user = new HashSet<Player>();
	// HashSet is a command tracker.
	ChatColor ese = ChatColor.RED;
	
	 static boolean ENABLED_FOR_ALL = true;
	// ignores all commands and permissions
	// overrides permissions and commands check
	 static int OVERALL_CHANCE = 90;
	 static int AMOUNT_TO_DROP = 32;
	 static int CHANCE_PER_DROP = 50; 
	// variables are mentioned for reference
	 
@Override
public void onDisable() {
	PluginDescriptionFile pdf = this.getDescription();
	this.logger.info("[DynaFish]" + " version " + pdf.getVersion() + " by MeneXia successfully disabled!");
}

@Override
public void onEnable() {
	try {
		File fileconfig = new File(getDataFolder(), "config.yml");
		if (!fileconfig.exists()) {
			getDataFolder().mkdir();
			//new File(getDataFolder(), "config.yml");
			this.getConfig().addDefault("ENABLED_FOR_ALL", false);
			this.getConfig().addDefault("OVERALL_CHANCE", 90);
			this.getConfig().addDefault("AMOUNT_TO_DROP", 32);
			this.getConfig().addDefault("CHANCE_PER_DROP", 50);
			this.getConfig().options().copyDefaults(true);
			
			this.getConfig().options().header("DynaFish config file\n" +
					"When ENABLED_FOR_ALL is true, it will ignore all commands and permissions, making DynaFish\n" +
					"usable for all.\n" +
					"When OVERALL_CHANCE is 90, there will be a 90% chance of the explosion dropping fish.\n" +
					"The other 10% will not drop fish.\n" +
					"When AMOUNT_TO_DROP is 32, the explosion can yield a total of 32 fish.\n" +
					"When CHANCE_PER_DROP is 50, there will be a 50% chance that 1 fish will drop from the total amount (32).");
			this.getConfig().options().copyHeader(true);
			
			this.saveConfig();
		}
	} catch (Exception e1){
		e1.printStackTrace();
	}
	emap_asym();
	//PluginManager pm = this.getServer().getPluginManager();
	this.getServer().getPluginManager().registerEvents(new DFEntityListener(this), this);
	//DEPRECATED: pm.registerEvent(Event.Type.ENTITY_EXPLODE, this.entityListener, Event.Priority.Monitor, this);
	PluginDescriptionFile pdf = this.getDescription();
	this.logger.info("[DynaFish]" + " version " + pdf.getVersion() + " by MeneXia sucessfully enabled!");
}

public boolean hasUser(Player player) {
	return user.contains(player);
}

public void setUser(Player player, boolean enabled) {
	if (enabled) {
		user.add(player);
		player.sendMessage(ese + "[DynaFish]" + ChatColor.WHITE + " enabled.");
	} else {
		user.remove(player);
		player.sendMessage(ese + "[DynaFish]" + ChatColor.WHITE + " disabled.");
	}
}

public boolean onCommand(CommandSender sender, Command cmd, String zhf, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be in-game to use DynaFish.");
			return true; //return ends current code.
			}
	if (ENABLED_FOR_ALL == true) {return false;}
		if(sender.hasPermission("dynafish.use")) {
			if (zhf.equalsIgnoreCase("dynafish") || zhf.equalsIgnoreCase("df")) {
				if (sender instanceof Player) {
				Player player = (Player)sender;
				setUser(player, !hasUser(player));
					}
				}
			} else {
				sender.sendMessage(ese + "Sorry, no DynaFish for you!");
				}
		return true;
		}

public void emap_asym() {
	ENABLED_FOR_ALL = this.getConfig().getBoolean("ENABLED_FOR_ALL");
	OVERALL_CHANCE = this.getConfig().getInt("OVERALL_CHANCE");
	AMOUNT_TO_DROP = this.getConfig().getInt("AMOUNT_TO_DROP");
	CHANCE_PER_DROP = this.getConfig().getInt("CHANCE_PER_DROP"); 
}


}
