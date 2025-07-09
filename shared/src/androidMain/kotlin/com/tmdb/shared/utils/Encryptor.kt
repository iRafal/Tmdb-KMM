package com.tmdb.shared.utils

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.Key
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec

private const val KEY_ALIAS = "KEY_ALIAS"
private const val GCM_TAG_LENGTH = 128

actual class Encryptor {
    /**
     * toByteArray(StandardCharsets.UTF_8)
     */
    actual fun encrypt(data: ByteArray): EncryptionResult {
        val secretKey = loadKey()
        val cipher = getCypher().apply { init(Cipher.ENCRYPT_MODE, secretKey) }

        val iv = cipher.iv
        val encryptedData: ByteArray = cipher.doFinal(data)

        return EncryptionResult(encryptedData = encryptedData, iv = iv)
    }

    /**
     * Decrypts the given encrypted data using the provided IV (Initialization Vector).
     * All inputs expected to be decoded from Base64.
     */
    actual fun decrypt(encryptedData: ByteArray, iv: ByteArray): ByteArray {
        val secretKey = loadKey()
        val cipher = getCypher().apply {
            init(
                Cipher.DECRYPT_MODE,
                secretKey,
                GCMParameterSpec(GCM_TAG_LENGTH, iv),
            )
        }
        return cipher.doFinal(encryptedData)
    }

    /**
     * Generate or retrieve the encryption key from the Android Keystore
     */
    private fun loadKey(): Key {
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)
        // If the key does not exist, create a new one
        return if (keyStore.containsAlias(KEY_ALIAS)) {
            keyStore.getKey(KEY_ALIAS, null)
        } else {
            val keyGenerator = KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_AES,
                "AndroidKeyStore",
            )
            keyGenerator.init(
                KeyGenParameterSpec.Builder(
                    KEY_ALIAS,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT,
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build(),
            )
            keyGenerator.generateKey()
        }
    }

    fun getCypher(): Cipher = Cipher.getInstance("AES/GCM/NoPadding")
}
