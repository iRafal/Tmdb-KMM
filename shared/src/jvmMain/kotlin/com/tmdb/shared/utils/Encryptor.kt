package com.tmdb.shared.utils

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.security.Key
import java.security.KeyStore
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

private const val GCM_IV_LENGTH = 12
private const val GCM_TAG_LENGTH = 128

private const val KEY_ALIAS = "KEY_ALIAS"

private const val KEYSTORE_PASSWORD = "KEYSTORE_PASSWORD"

actual class Encryptor {

    actual fun encrypt(data: ByteArray): EncryptionResult {
        val iv = getIV()
        val key = loadKey()
        val keySpec: SecretKey = SecretKeySpec(key.encoded, "AES")
        val cipher = getCypher().apply {
            init(
                Cipher.ENCRYPT_MODE,
                keySpec,
                GCMParameterSpec(GCM_TAG_LENGTH, iv),
            )
        }
        val encryptedData = cipher.doFinal(data)

        return EncryptionResult(encryptedData = encryptedData, iv = iv)
    }

    private fun getIV(): ByteArray {
        return ByteArray(GCM_IV_LENGTH).apply { SecureRandom().nextBytes(this) }
    }

    actual fun decrypt(encryptedData: ByteArray, iv: ByteArray): ByteArray {
        val key = loadKey()
        val keySpec: SecretKey = SecretKeySpec(key.encoded, "AES")
        val cipher = getCypher().apply {
            init(
                Cipher.DECRYPT_MODE,
                keySpec,
                GCMParameterSpec(GCM_TAG_LENGTH, iv),
            )
        }
        return cipher.doFinal(encryptedData)
    }

    fun getCypher(): Cipher = Cipher.getInstance("AES/GCM/NoPadding")

    private fun loadKey(): Key {
        val keyStore = KeyStore.getInstance("JCEKS")
        val keystoreFileName = "src/jvmMain/resources/keystore.jceks"
        val keystorePassword = KEYSTORE_PASSWORD.toCharArray()
        val keystoreFile = File(keystoreFileName)

        if (keystoreFile.exists()) {
            FileInputStream(keystoreFile).use { keyStoreFile ->
                keyStore.load(keyStoreFile, keystorePassword)
            }
        } else {
            keyStore.load(null, keystorePassword)
        }

        if (!keyStore.containsAlias(KEY_ALIAS)) {
            val keyGen = KeyGenerator.getInstance("AES").apply { init(GCM_TAG_LENGTH) }
            val entry = KeyStore.SecretKeyEntry(keyGen.generateKey())
            val protParam = KeyStore.PasswordProtection(keystorePassword)
            keyStore.setEntry(KEY_ALIAS, entry, protParam)
            keyStore.store(FileOutputStream(keystoreFile), keystorePassword)
        }

        val protParam = KeyStore.PasswordProtection(keystorePassword)
        val entry = keyStore.getEntry(KEY_ALIAS, protParam) as KeyStore.SecretKeyEntry
        return entry.secretKey
    }
}
