package com.bukkit.mcteam.factions;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.bukkit.*;
import org.bukkit.entity.CreatureType;

import com.bukkit.mcteam.util.DiscUtil;

public class Conf {
	public static transient File file = new File(Factions.instance.getDataFolder(), "conf.json");
	
	// Colors
	public static ChatColor colorMember = ChatColor.GREEN;
	public static ChatColor colorAlly = ChatColor.LIGHT_PURPLE;
	public static ChatColor colorNeutral = ChatColor.WHITE;
	public static ChatColor colorEnemy = ChatColor.RED;
	
	public static ChatColor colorSystem = ChatColor.YELLOW;
	public static ChatColor colorChrome = ChatColor.GOLD;
	public static ChatColor colorCommand = ChatColor.AQUA;
	public static ChatColor colorParameter = ChatColor.DARK_AQUA;
	
	// Power
	public static double powerPlayerMax = 10;
	public static double powerPlayerMin = -10;
	public static double powerPerMinute = 0.2; // Default health rate... it takes 5 min to heal one power
	public static double powerPerDeath = 2; //A death makes you loose 2 power
	
	public static String prefixAdmin = "**";
	public static String prefixMod = "*";
	
	public static int factionTagLengthMin = 3;
	public static int factionTagLengthMax = 10;
	public static boolean factionTagForceUpperCase = false;
	
	// Configuration on the Faction tag in chat messages.
	
	public static boolean chatTagEnabled = true;
	public static boolean chatTagRelationColored = true;
	public static int chatTagInsertIndex = 1;
	public static String chatTagFormat = "%s"+ChatColor.WHITE+" ";
	public static String factionChatFormat = "%s"+ChatColor.WHITE+" %s";
	
	public static boolean allowNoSlashCommand = true;
	
	public static double autoLeaveAfterDaysOfInactivity = 14;
	
	public static boolean homesEnabled = true;
	public static boolean homesTeleportToOnDeath = true;

	public static double territoryShieldFactor = 0.5;
	public static boolean territoryBlockCreepers = false;
	public static boolean territoryBlockFireballs = false;
	
	public static Set<Material> territoryProtectedMaterials = new HashSet<Material>();
	public static Set<Material> territoryDenyUseageMaterials = new HashSet<Material>();
	
	public static transient Set<CreatureType> safeZoneNerfedCreatureTypes = new HashSet<CreatureType>();
	
	public static transient Set<Material> instaDestroyMaterials = new HashSet<Material>(); // This one is not really configuration therefore transient
	public static transient int mapHeight = 8;
	public static transient int mapWidth = 49;
	
	static {
		territoryProtectedMaterials.add(Material.WOODEN_DOOR);
		territoryProtectedMaterials.add(Material.DISPENSER);
		territoryProtectedMaterials.add(Material.CHEST);
		territoryProtectedMaterials.add(Material.FURNACE);
		
		territoryDenyUseageMaterials.add(Material.REDSTONE);
		territoryDenyUseageMaterials.add(Material.SIGN);
		territoryDenyUseageMaterials.add(Material.FLINT_AND_STEEL);
		territoryDenyUseageMaterials.add(Material.BED);
		territoryDenyUseageMaterials.add(Material.BUCKET);
		territoryDenyUseageMaterials.add(Material.WATER_BUCKET);
		territoryDenyUseageMaterials.add(Material.DIODE);
		territoryDenyUseageMaterials.add(Material.SUGAR_CANE);
		
		instaDestroyMaterials.add(Material.SAPLING);
		instaDestroyMaterials.add(Material.TORCH);
		instaDestroyMaterials.add(Material.REDSTONE_WIRE);
		instaDestroyMaterials.add(Material.CROPS);
		instaDestroyMaterials.add(Material.REDSTONE_TORCH_OFF);
		instaDestroyMaterials.add(Material.REDSTONE_TORCH_ON);
		instaDestroyMaterials.add(Material.SUGAR_CANE_BLOCK);
		instaDestroyMaterials.add(Material.DIODE_BLOCK_OFF);
		instaDestroyMaterials.add(Material.DIODE_BLOCK_ON);
		instaDestroyMaterials.add(Material.PAINTING);
		instaDestroyMaterials.add(Material.RED_ROSE);
		instaDestroyMaterials.add(Material.YELLOW_FLOWER);
		
		safeZoneNerfedCreatureTypes.add(CreatureType.CREEPER);
		safeZoneNerfedCreatureTypes.add(CreatureType.GHAST);
		safeZoneNerfedCreatureTypes.add(CreatureType.PIG_ZOMBIE);
		safeZoneNerfedCreatureTypes.add(CreatureType.SKELETON);
		safeZoneNerfedCreatureTypes.add(CreatureType.SPIDER);
		safeZoneNerfedCreatureTypes.add(CreatureType.SLIME);
		safeZoneNerfedCreatureTypes.add(CreatureType.ZOMBIE);
	}
	
	// -------------------------------------------- //
	// Persistance
	// -------------------------------------------- //
	
	public static boolean save() {
		//Factions.log("Saving config to disk.");
		
		try {
			DiscUtil.write(file, Factions.gson.toJson(new Conf()));
		} catch (IOException e) {
			e.printStackTrace();
			Factions.log("Failed to save the config to disk.");
			return false;
		}
		return true;
	}
	
	public static boolean load() {
		Factions.log("Loading conf from disk");
		
		if ( ! file.exists()) {
			Factions.log("No conf to load from disk. Creating new file.");
			save();
			return true;
		}
		
		try {
			Factions.gson.fromJson(DiscUtil.read(file), Conf.class);
		} catch (IOException e) {
			e.printStackTrace();
			Factions.log("Failed to load the config from disk.");
			return false;
		}
		
		return true;
	}
}

