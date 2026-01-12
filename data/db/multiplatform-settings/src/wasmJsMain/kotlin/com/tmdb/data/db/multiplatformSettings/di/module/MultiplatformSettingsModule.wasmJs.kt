package com.tmdb.data.db.multiplatformSettings.di.module

import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * In-memory Settings implementation for wasmJs.
 * Data is not persisted across page refreshes.
 */
private class WasmJsSettings : Settings {
    private val storage = mutableMapOf<String, Any?>()

    override val keys: Set<String> get() = storage.keys
    override val size: Int get() = storage.size

    override fun clear() = storage.clear()
    override fun remove(key: String) { storage.remove(key) }
    override fun hasKey(key: String): Boolean = storage.containsKey(key)

    override fun putInt(key: String, value: Int) { storage[key] = value }
    override fun getInt(key: String, defaultValue: Int): Int = storage[key] as? Int ?: defaultValue
    override fun getIntOrNull(key: String): Int? = storage[key] as? Int

    override fun putLong(key: String, value: Long) { storage[key] = value }
    override fun getLong(key: String, defaultValue: Long): Long = storage[key] as? Long ?: defaultValue
    override fun getLongOrNull(key: String): Long? = storage[key] as? Long

    override fun putString(key: String, value: String) { storage[key] = value }
    override fun getString(key: String, defaultValue: String): String = storage[key] as? String ?: defaultValue
    override fun getStringOrNull(key: String): String? = storage[key] as? String

    override fun putFloat(key: String, value: Float) { storage[key] = value }
    override fun getFloat(key: String, defaultValue: Float): Float = storage[key] as? Float ?: defaultValue
    override fun getFloatOrNull(key: String): Float? = storage[key] as? Float

    override fun putDouble(key: String, value: Double) { storage[key] = value }
    override fun getDouble(key: String, defaultValue: Double): Double = storage[key] as? Double ?: defaultValue
    override fun getDoubleOrNull(key: String): Double? = storage[key] as? Double

    override fun putBoolean(key: String, value: Boolean) { storage[key] = value }
    override fun getBoolean(key: String, defaultValue: Boolean): Boolean = storage[key] as? Boolean ?: defaultValue
    override fun getBooleanOrNull(key: String): Boolean? = storage[key] as? Boolean
}

actual fun multiplatformSettingsModule(): Module = module {
    single<Settings> { WasmJsSettings() }
}
