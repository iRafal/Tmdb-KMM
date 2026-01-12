package com.tmdb.utils

/**
 * Stub implementation for wasmJs.
 * TODO: Implement using Web Crypto API when available in Kotlin/Wasm
 */
actual class Encryptor {

    actual fun encrypt(data: ByteArray): EncryptionResult {
        // Stub: returns data as-is with empty IV
        // No actual encryption - placeholder until Web Crypto API is available
        return EncryptionResult(encryptedData = data, iv = ByteArray(12))
    }

    actual fun decrypt(encryptedData: ByteArray, iv: ByteArray): ByteArray {
        // Stub: returns data as-is
        // No actual decryption - placeholder until Web Crypto API is available
        return encryptedData
    }
}
