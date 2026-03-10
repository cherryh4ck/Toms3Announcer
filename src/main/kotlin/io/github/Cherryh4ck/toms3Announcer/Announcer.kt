package io.github.Cherryh4ck.toms3Announcer

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

import net.kyori.adventure.text.minimessage.MiniMessage

class Announcer(private val plugin: Toms3Announcer) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val minimessage = MiniMessage.miniMessage()

        if (sender !is Player) {
            if (!args.isEmpty()) {
                val lastArg = args.last()
                val timesSent = lastArg.toIntOrNull()

                val messageContent: String
                val repeatTimes: Int

                if (timesSent != null) {
                    messageContent = args.dropLast(1).joinToString(" ")
                    repeatTimes = timesSent
                } else {
                    messageContent = args.joinToString(" ")
                    repeatTimes = 1
                }

                if (messageContent.isEmpty()){
                    plugin.sendError("El mensaje del announcer no puede estar vacío.")
                }
                else{
                    val message = minimessage.deserialize("<yellow>[SERVER] $messageContent</yellow>")
                    repeat (repeatTimes) {
                        Bukkit.broadcast(message)
                    }
                }
            }
            else{
                plugin.sendError("El mensaje del announcer no puede estar vacío.")
            }
        }
        else{
            plugin.sendError("${sender.name} (OP) intentó ejecutar el comando /announce.")
            val message = minimessage.deserialize("<red>No puedes ejecutar este comando si no eres la consola.</red>")
            sender.sendMessage(message)
        }
        return true
    }
}