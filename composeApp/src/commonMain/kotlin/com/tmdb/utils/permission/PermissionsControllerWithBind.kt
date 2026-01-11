package com.tmdb.utils.permission

import androidx.compose.runtime.Composable
import com.tmdb.utils.permission.common.AppPermissionsController

@Composable
expect fun rememberPermissionsControllerWithBind(): AppPermissionsController
