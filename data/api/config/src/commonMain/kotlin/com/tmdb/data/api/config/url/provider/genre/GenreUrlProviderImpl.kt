package com.tmdb.data.api.config.url.provider.genre

class GenreUrlProviderImpl constructor(genreBaseUrl: String) : GenreUrlProvider {
    override val genreMovieListUrl: String = "${genreBaseUrl}genre/movie/list"
    override val genreTvListUrl: String = "${genreBaseUrl}genre/tv/list"
}
