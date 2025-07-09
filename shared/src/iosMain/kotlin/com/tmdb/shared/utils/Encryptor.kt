package com.tmdb.shared.utils

actual class Encryptor {
    actual fun encrypt(data: ByteArray): EncryptionResult {
        TODO()
    }

    actual fun decrypt(encryptedData: ByteArray, iv: ByteArray): ByteArray {
        return byteArrayOf()
    }
}
