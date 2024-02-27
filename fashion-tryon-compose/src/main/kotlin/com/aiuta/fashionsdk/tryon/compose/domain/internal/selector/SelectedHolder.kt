package com.aiuta.fashionsdk.tryon.compose.domain.internal.selector

import androidx.compose.runtime.mutableStateMapOf

internal class SelectedHolder<T> {
    private val itemsMap = mutableStateMapOf<T, Int>()

    fun reset(items: List<T>) {
        itemsMap.clear()
        putAll(items)
    }

    fun removeAll() {
        itemsMap.clear()
    }

    fun putOrRemove(item: T) {
        if (contain(item)) {
            remove(item)
        } else {
            add(item)
        }
    }

    fun putAll(items: List<T>) {
        itemsMap.putAll(items.map { it to 1 })
    }

    fun getList(): List<T> {
        return itemsMap.keys.toList()
    }

    fun getCount(): Int {
        return itemsMap.size
    }

    fun isEmpty(): Boolean = itemsMap.isEmpty()

    fun contain(item: T?): Boolean {
        return itemsMap.contains(item)
    }

    private fun add(item: T) {
        itemsMap.put(item, 1)
    }

    private fun remove(item: T) {
        itemsMap.remove(item)
    }
}
