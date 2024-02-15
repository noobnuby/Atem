package com.noobnuby.plugin.api

import com.noobnuby.plugin.api.util.Downstream
import it.unimi.dsi.fastutil.Hash
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack

object Atem : Listener {
    private val items = HashMap<ItemStack, AtemEventBuilder>()

    init {
        Bukkit.getServer().pluginManager.registerEvents(this, Downstream.pullPlugin())
    }

    fun register(
        material: Material,
        amount: Int = 1,
        name: Component,
        lore: List<Component>? = null,
        block: (AtemEventBuilder.() -> Unit)? = null
    ): ItemStack {
        val item = ItemStack(material, amount).apply {
            itemMeta = itemMeta?.apply {
                this.displayName(name)
                this.lore(lore)
            }
        }

        val eventBuilder = AtemEventBuilder()
        block?.let {
            eventBuilder.apply(it)
        }

        items[item] = eventBuilder

        return item
    }

    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        val clickedItem = event.item

        if (clickedItem == null) return

        val eventBuilder = items.keys.find { it.isSimilar(clickedItem) }?.let { items[it] } ?: return

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
}
