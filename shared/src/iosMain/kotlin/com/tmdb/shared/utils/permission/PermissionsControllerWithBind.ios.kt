package com.tmdb.shared.utils.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.tmdb.shared.utils.permission.common.AppPermissionsController
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory

@Composable
actual fun rememberPermissionsControllerWithBind(): AppPermissionsController {
    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val permissionsController: PermissionsController = remember(factory) { factory.createPermissionsController() }
    BindEffect(permissionsController)
    return AppPermissionsController(permissionsController)
}
