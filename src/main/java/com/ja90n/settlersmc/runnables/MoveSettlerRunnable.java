package com.ja90n.settlersmc.runnables;

import com.ja90n.settlersmc.SettlersMC;
import com.ja90n.settlersmc.enums.SettlerState;
import com.ja90n.settlersmc.enums.Team;
import com.ja90n.settlersmc.instances.Settler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class MoveSettlerRunnable extends BukkitRunnable {

    private Settler settler;
    private Team team;
    private ArrayList<Location> line;

    private ArmorStand armorStand;


    public MoveSettlerRunnable(Settler settler, SettlersMC settlersMC){

        this.settler = settler; // Saving the settlers

        team = settler.getTeam(); // Saving the settlers team

        armorStand = settler.getArmorStand(); // Saving the Armorstand

        runTaskTimer(settlersMC,0,1); // Stating the runnable

    }

    public void moveSettler(Location destination) {

        // Cancels the movement of the settler
        if (settler.getSettlerState().equals(SettlerState.MOVING)){
            cancelMove();
        }

        // Calculate how many points need to be in the line
        int pointsInLine = (int) (settler.getArmorStand().getLocation().distance(destination));

        // Creating and saving that line
        line = createLine(settler.getArmorStand().getLocation(),destination,pointsInLine*20);

        // Sets the correct state
        settler.setSettlerState(SettlerState.MOVING);
    }

    public void cancelMove() {

        // Remove all the locations from the line
        line.clear();

        // Sets the settlers state to wandering
        settler.setSettlerState(SettlerState.WANDERING);
    }

    @Override
    public void run() {
        if (settler.getSettlerState().equals(SettlerState.MOVING)){
            // Checks if the line is empty
            if (!line.isEmpty()){
                // Save the destination
                Location destination = line.get(0);

                // Check if there is another point in the line besides the current destination
                if (line.size() != 1){
                    // Telport the armorstand to the destination and making it look to the next point
                    armorStand.teleport(destination.setDirection(armorStand.getLocation().toVector().subtract(line.get(1).toVector())));
                } else {
                    // Telport the armorstand to the destination
                    armorStand.teleport(destination);
                }
                // Remove the current destination from the line
                line.remove(0);
            } else {
                // If the line is empty then it will cancel the movement of the settler
                cancelMove();
            }
        }
    }

    // Black magic
    public ArrayList<Location> createLine(Location point1, Location point2, int pointsInLine) {
        double p1X = point1.getX();
        double p1Y = point1.getY();
        double p1Z = point1.getZ();
        double p2X = point2.getX();
        double p2Y = point2.getY();
        double p2Z = point2.getZ();

        double lineAveX = (p2X-p1X)/pointsInLine;
        double lineAveY = (p2Y-p1Y)/pointsInLine;
        double lineAveZ = (p2Z-p1Z)/pointsInLine;

        World world = point1.getWorld();
        ArrayList<Location> line = new ArrayList<>();
        for(int i = 0; i <= pointsInLine; i++){
            Location loc = new Location(world, p1X + lineAveX * i, p1Y + lineAveY * i, p1Z + lineAveZ * i);
            line.add(loc);
        }
        return line;
    }
}
