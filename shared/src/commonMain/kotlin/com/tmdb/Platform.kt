package com.tmdb

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform