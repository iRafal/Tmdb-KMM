package com.tmdb.store.env.impl

import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.contract.AppEnv.Database
import com.tmdb.store.env.contract.AppEnv.Network

fun createAppEnvImpl(appNetwork: Network, appDatabase: Database): AppEnv {
    return object : AppEnv {
        override val network: Network = appNetwork
        override val database: Database = appDatabase
    }
}
