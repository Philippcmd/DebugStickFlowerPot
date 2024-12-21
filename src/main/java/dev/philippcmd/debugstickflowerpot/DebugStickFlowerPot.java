package dev.philippcmd.debugstickflowerpot;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class DebugStickFlowerPot extends JavaPlugin implements Listener {

    private final List<Material> pottedPlants = new ArrayList<>();

    @Override
    public void onEnable() {
        // Initialize the list of potted plants
        pottedPlants.add(Material.FLOWER_POT); // Empty pot
        pottedPlants.add(Material.POTTED_DANDELION);
        pottedPlants.add(Material.POTTED_POPPY);
        pottedPlants.add(Material.POTTED_BLUE_ORCHID);
        pottedPlants.add(Material.POTTED_ALLIUM);
        pottedPlants.add(Material.POTTED_AZURE_BLUET);
        pottedPlants.add(Material.POTTED_RED_TULIP);
        pottedPlants.add(Material.POTTED_ORANGE_TULIP);
        pottedPlants.add(Material.POTTED_WHITE_TULIP);
        pottedPlants.add(Material.POTTED_PINK_TULIP);
        pottedPlants.add(Material.POTTED_OXEYE_DAISY);
        pottedPlants.add(Material.POTTED_CORNFLOWER);
        pottedPlants.add(Material.POTTED_LILY_OF_THE_VALLEY);
        pottedPlants.add(Material.POTTED_WITHER_ROSE);
        pottedPlants.add(Material.POTTED_TORCHFLOWER);
        pottedPlants.add(Material.POTTED_OAK_SAPLING);
        pottedPlants.add(Material.POTTED_SPRUCE_SAPLING);
        pottedPlants.add(Material.POTTED_BIRCH_SAPLING);
        pottedPlants.add(Material.POTTED_JUNGLE_SAPLING);
        pottedPlants.add(Material.POTTED_ACACIA_SAPLING);
        pottedPlants.add(Material.POTTED_DARK_OAK_SAPLING);
        pottedPlants.add(Material.POTTED_CHERRY_SAPLING);
        pottedPlants.add(Material.POTTED_RED_MUSHROOM);
        pottedPlants.add(Material.POTTED_BROWN_MUSHROOM);
        pottedPlants.add(Material.POTTED_FERN);
        pottedPlants.add(Material.POTTED_DEAD_BUSH);
        pottedPlants.add(Material.POTTED_CACTUS);
        pottedPlants.add(Material.POTTED_BAMBOO);
        pottedPlants.add(Material.POTTED_AZALEA_BUSH);
        pottedPlants.add(Material.POTTED_FLOWERING_AZALEA_BUSH);
        pottedPlants.add(Material.POTTED_CRIMSON_FUNGUS);
        pottedPlants.add(Material.POTTED_WARPED_FUNGUS);
        pottedPlants.add(Material.POTTED_CRIMSON_ROOTS);
        pottedPlants.add(Material.POTTED_WARPED_ROOTS);
        pottedPlants.add(Material.POTTED_MANGROVE_PROPAGULE);

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.hasBlock() || !event.hasItem()) return;

        // Check if the player is holding a Debug Stick
        if (event.getItem().getType() != Material.DEBUG_STICK) return;

        Block block = event.getClickedBlock();
        if (block == null) return;

        // Check if the clicked block is a flower pot
        Material currentMaterial = block.getType();
        if (!pottedPlants.contains(currentMaterial)) return;

        // Get the current pot type and find its index in the list
        int currentIndex = pottedPlants.indexOf(currentMaterial);
        int nextIndex = (currentIndex + 1) % pottedPlants.size(); // Cycle to the next plant

        // Update the block to the next material
        Material nextMaterial = pottedPlants.get(nextIndex);
        block.setType(nextMaterial, false);

        // Play particle and sound effects
        block.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, block.getLocation().add(0.5, 0.5, 0.5), 10);
        block.getWorld().playSound(block.getLocation(), Sound.BLOCK_GRASS_PLACE, 1.0f, 1.0f);
    }
}
