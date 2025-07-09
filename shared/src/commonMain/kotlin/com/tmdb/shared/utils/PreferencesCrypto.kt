package com.tmdb.shared.utils

expect class PreferencesCrypto() {
    fun encrypt(data: ByteArray): ByteArray

    fun decrypt(data: ByteArray): ByteArray
}
