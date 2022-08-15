package com.ja90n.settlersmc.instances;

import com.ja90n.settlersmc.SettlersMC;
import com.ja90n.settlersmc.enums.SettlerState;
import com.ja90n.settlersmc.enums.Team;
import com.ja90n.settlersmc.managers.SettlersManager;
import com.ja90n.settlersmc.runnables.MoveSettlerRunnable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;


public class Settler {

    private SettlersManager settlersManager;
    private ArmorStand armorStand;
    private Team team;
    private SettlerState settlerState;

    private MoveSettlerRunnable moveSettlerRunnable;

    public Settler(SettlersManager settlersManager, Location spawnLocation, Team team) {

        // Saving the settlers manager
        this.settlersManager = settlersManager;

        // Putting the settler in the right team
        this.team = team;

        // Spawning an Armorstand at the spawnLocation
        armorStand = (ArmorStand) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.ARMOR_STAND);

        // Giving the Armorstand the correct properties
        armorStand.setBasePlate(false);
        armorStand.setInvisible(true);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.getEquipment().setHelmet(new ItemStack(Material.STICK));

        // Setting the starting settler state
        settlerState = SettlerState.WANDERING;

        // Creates and saves the move settler runnable
        moveSettlerRunnable = new MoveSettlerRunnable(this,settlersManager.getSettlersMC());

    }

    public Team getTeam() {
        return team;
    }

    public ArmorStand getArmorStand() {
        return armorStand;
    }

    public SettlerState getSettlerState() {
        return settlerState;
    }

    public void setSettlerState(SettlerState settlerState){
        this.settlerState = settlerState;
    }

    public MoveSettlerRunnable getMoveSettlerRunnable() {
        return moveSettlerRunnable;
    }
}
