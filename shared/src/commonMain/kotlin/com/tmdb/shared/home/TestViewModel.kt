package com.tmdb.shared.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.data.db.dataStore.PreferencesStore
import com.tmdb.shared.utils.BytesTranscoder
import com.tmdb.shared.utils.BytesTranscoderImpl
import com.tmdb.shared.utils.permission.AppPermission
import com.tmdb.shared.utils.permission.PermissionHandler
import com.tmdb.shared.utils.permission.PermissionRequestResult.DENIED_ALWAYS
import com.tmdb.shared.utils.permission.PermissionRequestResult.GRANTED
import com.tmdb.shared.utils.permission.PermissionRequestResult.NOT_GRANTED
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import com.tmdb.shared.utils.Encryptor

class TestViewModel(
    private val permissionHandler: PermissionHandler,
    private val preferencesStore: PreferencesStore,
) : ViewModel() {

    init {
        viewModelScope.launch {
            testPrefsStore()
            testEncryptionAndTranscoding()
        }
    }

    private suspend fun testPrefsStore() {
        preferencesStore.setToken("token_123")
        val token = preferencesStore.token.first()
        println(token)
    }

    private fun testEncryptionAndTranscoding() {
        val encryptor = Encryptor()
        val (encryptedData, iv) = encryptor.encrypt("test".encodeToByteArray())

        val transcoder: BytesTranscoder = BytesTranscoderImpl()
        val encodedEncryptedData = transcoder.encode(encryptedData) ?: byteArrayOf()
        val encodedIV = transcoder.encode(iv) ?: byteArrayOf()

        val decodedEncryptedData = transcoder.decode(encodedEncryptedData)  ?: byteArrayOf()
        val decodedIV = transcoder.decode(encodedIV)

        val decryptedData = encryptor.decrypt(decodedEncryptedData, iv)
        println(decryptedData.decodeToString())
    }

    fun requestPermissions() {
        viewModelScope.launch {
            handlePermission(AppPermission.RecordAudio)
            handlePermission(AppPermission.Location.Approximate)
        }
    }

    private suspend fun handlePermission(permission: AppPermission) {
        val result = permissionHandler.handlePermission(permission)
        when (result) {
            GRANTED -> { /*INFO do nothing */
            }

            NOT_GRANTED -> { /*INFO do nothing */
            }

            DENIED_ALWAYS -> permissionHandler.openSettingsApp()
        }
    }
}
