package com.tmdb.shared.utils

import com.tmdb.shared.Encryptor
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.refTo
import kotlinx.cinterop.usePinned
import platform.Foundation.NSData
import platform.Foundation.create
import platform.Foundation.getBytes

@OptIn(ExperimentalForeignApi::class)
actual class PreferencesCrypto {

    private val keyBytes = ByteArray(32) { 0x42 } // Example 256-bit key

    @OptIn(BetaInteropApi::class)
    actual fun encrypt(data: ByteArray): ByteArray {
        return data.usePinned { pinned ->
            val nsData = NSData.create(bytes = pinned.addressOf(0), length = data.size.toULong())
            val encrypted = Encryptor().encrypt(nsData)
            encrypted.toByteArray()
        }
    }

    fun NSData.toByteArray(): ByteArray {
        val size = this.length.toInt()
        val byteArray = ByteArray(size)
        memScoped {
            val buffer = byteArray.refTo(0).getPointer(this)
            this@toByteArray.getBytes(buffer, size.toULong())
        }
        return byteArray
    }

    actual fun decrypt(data: ByteArray): ByteArray {
        return data
    }
}
