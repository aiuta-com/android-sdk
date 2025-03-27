package com.aiuta.fashionsdk.tryon.compose.domain.internal.selector

import androidx.compose.runtime.mutableStateMapOf
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized

internal class SelectedHolder<T> : SynchronizedObject() {
    private val itemsMap = mutableStateMapOf<T, Int>()

    fun reset(items: List<T>) {
        removeAll()
        putAll(items)
    }

    fun removeAll() {
        synchronized(this) {
            itemsMap.clear()
        }
    }

    fun putOrRemove(item: T) {
        if (contain(item)) {
            remove(item)
        } else {
            add(item)
        }
    }

    fun put(item: T) {
        if (!contain(item)) {
            add(item)
        }
    }

    fun putAll(items: List<T>) {
        synchronized(this) {
            itemsMap.putAll(items.map { it to 1 })
        }
    }

    fun getList(): List<T> {
        return itemsMap.keys.toList()
    }

    fun getCount(): Int {
        return itemsMap.size
    }

    fun isEmpty(): Boolean = itemsMap.isEmpty()

    fun contain(item: T?): Boolean {
        return item?.let { itemsMap.contains(it) } ?: false
    }

    fun remove(item: T) {
        synchronized(this) {
            itemsMap.remove(item)
        }
    }

    fun remove(items: List<T>) {
        items.forEach { item -> remove(item) }
    }

    private fun add(item: T) {
        synchronized(this) {
            itemsMap[item] = 1
        }
    }
}
