package com.tmdb.shared.utils.permission

import androidx.compose.runtime.Composable
import com.tmdb.shared.utils.permission.common.AppPermissionsController

@Composable
expect fun rememberPermissionsControllerWithBind(): AppPermissionsController
