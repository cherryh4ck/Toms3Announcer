package io.github.Cherryh4ck.toms3Announcer

import org.bukkit.plugin.java.JavaPlugin

class Toms3Announcer : JavaPlugin() {

    override fun onEnable() {
        logger.info("Plugin enabled.")
        getCommand("announce")?.setExecutor(Announcer(this))
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

    fun sendError(message : String){
        logger.warning(message)
    }
}
