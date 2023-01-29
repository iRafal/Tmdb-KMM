package com.tmdb.ui.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform