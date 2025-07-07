package com.tmdb.shared.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.data.db.dataStore.PreferencesStore
import com.tmdb.shared.utils.permission.AppPermission
import com.tmdb.shared.utils.permission.PermissionHandler
import com.tmdb.shared.utils.permission.PermissionRequestResult.DENIED_ALWAYS
import com.tmdb.shared.utils.permission.PermissionRequestResult.GRANTED
import com.tmdb.shared.utils.permission.PermissionRequestResult.NOT_GRANTED
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TestViewModel(
    private val permissionHandler: PermissionHandler,
    private val preferencesStore: PreferencesStore,
) : ViewModel() {

    init {
        viewModelScope.launch {
            testPrefsStore()
        }
    }

    private suspend fun testPrefsStore() {
        preferencesStore.setToken("token_123")
        val token = preferencesStore.token.first()
        println(token)
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
