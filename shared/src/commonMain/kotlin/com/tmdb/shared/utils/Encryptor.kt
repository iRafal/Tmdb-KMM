package com.tmdb.shared.utils

expect class Encryptor() {
    fun encrypt(data: ByteArray): EncryptionResult

    fun decrypt(encryptedData: ByteArray, iv: ByteArray): ByteArray
}

data class EncryptionResult(val encryptedData: ByteArray, val iv: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as EncryptionResult

        if (!encryptedData.contentEquals(other.encryptedData)) return false
        if (!iv.contentEquals(other.iv)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = encryptedData.contentHashCode()
        result = 31 * result + iv.contentHashCode()
        return result
    }
}
