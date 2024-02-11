package com.noobnuby.plugin.api

import com.noobnuby.plugin.api.util.Downstream
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

object Atem:Listener {
    fun register(
        material: Material,
        amount: Int = 1,
        name: Component,
        lore: List<Component>? = null,
        block: AtemEventBuilder.() -> Unit
    ): ItemStack {
        val item = ItemStack(material, amount).apply {
            itemMeta = itemMeta?.apply {
                this.displayName(name)
                this.lore(lore)
            }
        }

        Bukkit.getServer().pluginManager.registerEvents(this, Downstream.pullPlugin())

        val eventBuilder = AtemEventBuilder().apply(block)

        @EventHandler
        fun onPlayerInteract(event: PlayerInteractEvent) {
            val player = event.player
            val action = event.action
            val clickedItem = event.item

            if (clickedItem?.isSimilar(item) != true) return

            if (action.isLeftClick) eventBuilder.leftClickEvent?.invoke(player)
            if (action.isRightClick) eventBuilder.rightClickEvent?.invoke(player)
            when (action) {
                Action.LEFT_CLICK_AIR -> eventBuilder.leftClickAirEvent?.invoke(player)
                Action.LEFT_CLICK_BLOCK -> eventBuilder.leftClickBlockEvent?.invoke(player)
                Action.RIGHT_CLICK_AIR -> eventBuilder.rightClickAirEvent?.invoke(player)
                Action.RIGHT_CLICK_BLOCK -> eventBuilder.rightClickBlockEvent?.invoke(player)
                else -> {}
            }
        }

        return item
    }

    class AtemEventBuilder {
        var leftClickEvent: ((Player) -> Unit)? = null
        var rightClickEvent: ((Player) -> Unit)? = null
        var leftClickAirEvent: ((Player) -> Unit)? = null
        var leftClickBlockEvent: ((Player) -> Unit)? = null
        var rightClickAirEvent: ((Player) -> Unit)? = null
        var rightClickBlockEvent: ((Player) -> Unit)? = null

        fun leftClickEvent(block: (Player) -> Unit) {
            leftClickEvent = block
        }

        fun rightClickEvent(block: (Player) -> Unit) {
            rightClickEvent = block
        }

        fun leftClickAirEvent(block: (Player) -> Unit) {
            leftClickAirEvent = block
        }

        fun leftClickBlockEvent(block: (Player) -> Unit) {
            leftClickBlockEvent = block
        }

        fun rightClickAirEvent(block: (Player) -> Unit) {
            rightClickAirEvent = block
        }

        fun rightClickBlockEvent(block: (Player) -> Unit) {
            rightClickBlockEvent = block
        }
    }
}