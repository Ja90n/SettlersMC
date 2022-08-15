package com.ja90n.settlersmc.managers;

import com.ja90n.settlersmc.SettlersMC;
import com.ja90n.settlersmc.enums.Team;
import com.ja90n.settlersmc.instances.Settler;
import org.bukkit.Location;


import java.util.ArrayList;

public class SettlersManager {

    private SettlersMC settlersMC;
    private ArrayList<Settler> settlers;

    public SettlersManager(SettlersMC settlersMC) {
        this.settlersMC = settlersMC;

        settlers = new ArrayList<>();
    }

    public void createSettler(Location spawnLocation, Team team) {
        settlers.add(new Settler(this,spawnLocation,team));
    }

    public void setTarget(Location destination){
        for (Settler settler : settlers){
            settler.getMoveSettlerRunnable().moveSettler(destination);
        }
    }

    public SettlersMC getSettlersMC() {
        return settlersMC;
    }
}
