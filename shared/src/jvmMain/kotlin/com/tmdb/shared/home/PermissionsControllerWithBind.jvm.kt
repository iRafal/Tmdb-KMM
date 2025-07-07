package com.tmdb.shared.home

import androidx.compose.runtime.Composable
import com.tmdb.shared.utils.permission.common.AppPermissionsController

@Composable
actual fun rememberPermissionsControllerWithBind() = AppPermissionsController()
