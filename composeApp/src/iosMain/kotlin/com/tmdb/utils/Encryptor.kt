package com.tmdb.utils

actual class Encryptor {
    actual fun encrypt(data: ByteArray): EncryptionResult {
        return EncryptionResult(data, byteArrayOf())
    }

    actual fun decrypt(encryptedData: ByteArray, iv: ByteArray): ByteArray {
        return byteArrayOf()
    }
}
