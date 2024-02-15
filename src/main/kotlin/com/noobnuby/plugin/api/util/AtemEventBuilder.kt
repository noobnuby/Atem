package com.noobnuby.plugin.api

import org.bukkit.entity.Player

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
