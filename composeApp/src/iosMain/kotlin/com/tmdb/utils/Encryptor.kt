package com.tmdb.utils

actual class Encryptor {
    actual fun encrypt(data: ByteArray): EncryptionResult = EncryptionResult(data, byteArrayOf())

    actual fun decrypt(encryptedData: ByteArray, iv: ByteArray): ByteArray = byteArrayOf()
}
