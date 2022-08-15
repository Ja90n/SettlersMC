package com.ja90n.settlersmc;

import com.ja90n.settlersmc.commands.MainCommand;
import com.ja90n.settlersmc.managers.SettlersManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class SettlersMC extends JavaPlugin {

    private SettlersManager settlersManager;

    @Override
    public void onEnable() {

        //saveDefaultConfig(); // Create the default config if it is not there

        settlersManager = new SettlersManager(this); // Create an instance of the settlers manager class

        getCommand("settler").setExecutor(new MainCommand(this)); // Register command

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public SettlersManager getSettlersManager() {
        return settlersManager;
    }
}
