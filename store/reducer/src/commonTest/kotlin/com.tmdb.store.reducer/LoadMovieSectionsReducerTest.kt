package com.tmdb.store.reducer

import com.tmdb.data.api.model.data.DataPage
import com.tmdb.data.api.model.genre.Genre
import com.tmdb.data.api.model.movie.Movie
import com.tmdb.data.api.model.person.Person
import com.tmdb.data.api.model.util.ApiException
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.store.action.home.HomeAction
import com.tmdb.store.reducer.home.HomeFeatureEffects
import com.tmdb.store.reducer.home.HomeFeatureSlice
import com.tmdb.store.reducer.home.HomeFeatureSliceImpl
import com.tmdb.store.reducer.util.ModelUtil
import com.tmdb.store.state.FeatureState
import com.tmdb.store.state.FeatureState.Success
import com.tmdb.store.state.app.AppState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class LoadMovieSectionsReducerTest {

    private val discoverSource = object: DiscoverRemoteDataSource {
        override suspend fun discoverMovie(
            language: String?,
            page: Int?,
            region: String?
        ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
            TODO("Not yet implemented")
        }

        override suspend fun discoverTv(
            language: String?,
            page: Int?,
            region: String?
        ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
            TODO("Not yet implemented")
        }
    }

    private val genreSource = object : GenreRemoteDataSource {
        override suspend fun genreMovieList(
            language: String?
        ): ApiResponse<List<Genre>, NetworkErrorModel> {
            TODO("Not yet implemented")
        }

        override suspend fun genreTvList(
            language: String?
        ): ApiResponse<List<Genre>, NetworkErrorModel> {
            TODO("Not yet implemented")
        }
    }

    private val personSource = object: PersonRemoteDataSource {
        override suspend fun personDetails(
            personId: Int,
            language: String?,
            appendToResponse: String?
        ): ApiResponse<Person, NetworkErrorModel> {
            TODO("Not yet implemented")
        }
    }

    private val movieLocalDataSource = object: MovieLocalDataSource {
        override suspend fun movie(movieId: Int): MovieDataModel? {
            TODO("Not yet implemented")
        }

        override suspend fun nowPlayingMovies(page: Int?, pageSize: Int?): List<MovieDataModel> {
            TODO("Not yet implemented")
        }

        override suspend fun nowPopularMovies(page: Int?, pageSize: Int?): List<MovieDataModel> {
            TODO("Not yet implemented")
        }

        override suspend fun topRatedMovies(page: Int?, pageSize: Int?): List<MovieDataModel> {
            TODO("Not yet implemented")
        }

        override suspend fun upcomingMovies(page: Int?, pageSize: Int?): List<MovieDataModel> {
            TODO("Not yet implemented")
        }

        override suspend fun insert(movie: MovieDataModel) {
            TODO("Not yet implemented")
        }

        override suspend fun insertAll(movies: List<MovieDataModel>) {
            TODO("Not yet implemented")
        }

        override suspend fun insertByCategories(
            nowPlaying: List<MovieDataModel>,
            nowPopular: List<MovieDataModel>,
            topRatedMovies: List<MovieDataModel>,
            upcomingMovies: List<MovieDataModel>
        ) {
            TODO("Not yet implemented")
        }

        override suspend fun delete(movie: MovieDataModel) {
            TODO("Not yet implemented")
        }

        override fun observeAll(): Flow<List<MovieDataModel>> {
            TODO("Not yet implemented")
        }

        override suspend fun getAll(): List<MovieDataModel> {
            TODO("Not yet implemented")
        }

        override suspend fun getById(id: Int): MovieDataModel? {
            TODO("Not yet implemented")
        }

    }

    private val testDispatcher: CoroutineDispatcher = Dispatchers.Unconfined

    @Test
    fun `reduce load movie sections success`() = runTest {
        val movies = listOf(ModelUtil.movieDataModel)
        val successResult = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            )
        )

        val appState = AppState.INITIAL
        val dataSuccessMovies = DataState.Success(movies)
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper =  { dataSuccessMovies },
            moviesDataToFeatureStateMapper = { Success(movies) },
            homeFeatureEffects
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        var nowPlayingMoviesMethodCallingCounter = 0
        var nowPopularMoviesMethodCallingCounter = 0
        var topRatedMoviesMethodCallingCounter = 0
        var upcomingMoviesMethodCallingCounter = 0

        val movieSource = object: MovieRemoteDataSource {
            override suspend fun movie(
                movieId: Int,
                language: String?,
                appendToResponse: String?
            ): ApiResponse<Movie, NetworkErrorModel> {
                TODO("Not yet implemented")
            }

            override suspend fun latestMovie(
                language: String?
            ): ApiResponse<Movie, NetworkErrorModel> {
                TODO("Not yet implemented")
            }

            override suspend fun nowPlayingMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                nowPlayingMoviesMethodCallingCounter++
                return successResult
            }

            override suspend fun nowPopularMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                nowPopularMoviesMethodCallingCounter++
                return successResult
            }

            override suspend fun topRatedMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                topRatedMoviesMethodCallingCounter++
                return successResult
            }

            override suspend fun upcomingMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                upcomingMoviesMethodCallingCounter++
                return successResult
            }
        }

        var dispatchMethodCallCount: Int = 0
        val dispatchMethodCallCountCallback: (Int) -> Unit = { count ->
            dispatchMethodCallCount = count
        }

        val executor = createMockEffectExecutor(
            discoverSource,
            genreSource,
            movieSource,
            personSource,
            movieLocalDataSource,
            dispatchMethodCallCountCallback
        )
        effect?.invoke(executor)

        assertEquals(
            expected = nowPlayingMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::nowPlayingMovies need to call 1 time"
        )
        assertEquals(
            expected = nowPopularMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::nowPopularMovies need to call 1 time"
        )
        assertEquals(
            expected = topRatedMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::topRatedMovies need to call 1 time"
        )
        assertEquals(
            expected = upcomingMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::upcomingMovies need to call 1 time"
        )

        dispatchMethodCallCount

        executor.effectExecutorScope.dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataSuccessMovies,
                nowPopularMovies = dataSuccessMovies,
                topRatedMovies = dataSuccessMovies,
                upcomingMovies = dataSuccessMovies,
            )
        )

        assertEquals(
            expected = dispatchMethodCallCount,
            actual = 1,
            message = "[executor.effectExecutorScope.dispatch] need to call 1 time"
        )
    }

    @Test
    fun `reduce load movie sections api error`() = runTest {
        val apiErrorResult: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.ApiError()

        val appState = AppState.INITIAL
        val dataErrorMovies = DataState.Error<List<MovieDataModel>>(ApiException.BadRequest())
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataErrorMovies },
            moviesDataToFeatureStateMapper = { FeatureState.Error() },
            homeFeatureEffects
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        var nowPlayingMoviesMethodCallingCounter = 0
        var nowPopularMoviesMethodCallingCounter = 0
        var topRatedMoviesMethodCallingCounter = 0
        var upcomingMoviesMethodCallingCounter = 0

        val movieSource = object: MovieRemoteDataSource {
            override suspend fun movie(
                movieId: Int,
                language: String?,
                appendToResponse: String?
            ): ApiResponse<Movie, NetworkErrorModel> {
                TODO("Not yet implemented")
            }

            override suspend fun latestMovie(
                language: String?
            ): ApiResponse<Movie, NetworkErrorModel> {
                TODO("Not yet implemented")
            }

            override suspend fun nowPlayingMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                nowPlayingMoviesMethodCallingCounter++
                return apiErrorResult
            }

            override suspend fun nowPopularMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                nowPopularMoviesMethodCallingCounter++
                return apiErrorResult
            }

            override suspend fun topRatedMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                topRatedMoviesMethodCallingCounter++
                return apiErrorResult
            }

            override suspend fun upcomingMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                upcomingMoviesMethodCallingCounter++
                return apiErrorResult
            }
        }

        var dispatchMethodCallCount: Int = 0
        val dispatchMethodCallCountCallback: (Int) -> Unit = { count ->
            dispatchMethodCallCount = count
        }

        val executor = createMockEffectExecutor(
            discoverSource,
            genreSource,
            movieSource,
            personSource,
            movieLocalDataSource,
            dispatchMethodCallCountCallback
        )
        effect?.invoke(executor)

        assertEquals(
            expected = nowPlayingMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::nowPlayingMovies need to call 1 time"
        )

        assertEquals(
            expected = nowPopularMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::nowPopularMovies need to call 1 time"
        )
        assertEquals(
            expected = topRatedMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::topRatedMovies need to call 1 time"
        )
        assertEquals(
            expected = upcomingMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::upcomingMovies need to call 1 time"
        )

        executor.effectExecutorScope.dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataErrorMovies,
                nowPopularMovies = dataErrorMovies,
                topRatedMovies = dataErrorMovies,
                upcomingMovies = dataErrorMovies,
            )
        )
        assertEquals(
            expected = dispatchMethodCallCount,
            actual = 1,
            message = "[executor.effectExecutorScope.dispatch] need to call 1 time"
        )
    }

    @Test
    fun `reduce load movie sections network error`() = runTest {
        val networkErrorResult: ApiResponse<DataPage<Movie>, NetworkErrorModel> =
            ApiResponse.NetworkError()

        val appState = AppState.INITIAL
        val dataErrorMovies =
            DataState.NetworkError<List<MovieDataModel>>(ApiException.NetworkError())
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataErrorMovies },
            moviesDataToFeatureStateMapper = { FeatureState.NetworkError() },
            homeFeatureEffects
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        var nowPlayingMoviesMethodCallingCounter = 0
        var nowPopularMoviesMethodCallingCounter = 0
        var topRatedMoviesMethodCallingCounter = 0
        var upcomingMoviesMethodCallingCounter = 0

        val movieSource = object: MovieRemoteDataSource {
            override suspend fun movie(
                movieId: Int,
                language: String?,
                appendToResponse: String?
            ): ApiResponse<Movie, NetworkErrorModel> {
                TODO("Not yet implemented")
            }

            override suspend fun latestMovie(
                language: String?
            ): ApiResponse<Movie, NetworkErrorModel> {
                TODO("Not yet implemented")
            }

            override suspend fun nowPlayingMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                nowPlayingMoviesMethodCallingCounter++
                return networkErrorResult
            }

            override suspend fun nowPopularMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                nowPopularMoviesMethodCallingCounter++
                return networkErrorResult
            }

            override suspend fun topRatedMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                topRatedMoviesMethodCallingCounter++
                return networkErrorResult
            }

            override suspend fun upcomingMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                upcomingMoviesMethodCallingCounter++
                return networkErrorResult
            }
        }

        var dispatchMethodCallCount: Int = 0
        val dispatchMethodCallCountCallback: (Int) -> Unit = { count ->
            dispatchMethodCallCount = count
        }

        val executor = createMockEffectExecutor(
            discoverSource,
            genreSource,
            movieSource,
            personSource,
            movieLocalDataSource,
            dispatchMethodCallCountCallback
        )
        effect?.invoke(executor)

        assertEquals(
            expected = nowPlayingMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::nowPlayingMovies need to call 1 time"
        )

        assertEquals(
            expected = nowPopularMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::nowPopularMovies need to call 1 time"
        )
        assertEquals(
            expected = topRatedMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::topRatedMovies need to call 1 time"
        )
        assertEquals(
            expected = upcomingMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::upcomingMovies need to call 1 time"
        )

        executor.effectExecutorScope.dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataErrorMovies,
                nowPopularMovies = dataErrorMovies,
                topRatedMovies = dataErrorMovies,
                upcomingMovies = dataErrorMovies,
            )
        )
        assertEquals(
            expected = dispatchMethodCallCount,
            actual = 1,
            message = "[executor.effectExecutorScope.dispatch] need to call 1 time"
        )
    }

    @Test
    fun `reduce load movie sections unknown error`() = runTest {
        val unknownErrorResult: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.UnknownError()

        val appState = AppState.INITIAL
        val dataErrorMovies = DataState.Error<List<MovieDataModel>>(ApiException.UnknownError())
        val homeFeatureEffects = HomeFeatureEffects(testDispatcher)
        val homeFeatureSlice: HomeFeatureSlice = HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = { dataErrorMovies },
            moviesDataToFeatureStateMapper = { FeatureState.NetworkError() },
            homeFeatureEffects
        )
        val (homeFeatureState, effect) = homeFeatureSlice.reducer(
            appState,
            HomeAction.LoadMovieSections
        )

        assertTrue(homeFeatureState.nowPlayingMoviesState.isLoading)
        assertTrue(homeFeatureState.nowPopularMoviesState.isLoading)
        assertTrue(homeFeatureState.topRatedMoviesState.isLoading)
        assertTrue(homeFeatureState.upcomingMoviesState.isLoading)

        var nowPlayingMoviesMethodCallingCounter = 0
        var nowPopularMoviesMethodCallingCounter = 0
        var topRatedMoviesMethodCallingCounter = 0
        var upcomingMoviesMethodCallingCounter = 0

        val movieSource = object: MovieRemoteDataSource {
            override suspend fun movie(
                movieId: Int,
                language: String?,
                appendToResponse: String?
            ): ApiResponse<Movie, NetworkErrorModel> {
                TODO("Not yet implemented")
            }

            override suspend fun latestMovie(
                language: String?
            ): ApiResponse<Movie, NetworkErrorModel> {
                TODO("Not yet implemented")
            }

            override suspend fun nowPlayingMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                nowPlayingMoviesMethodCallingCounter++
                return unknownErrorResult
            }

            override suspend fun nowPopularMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                nowPopularMoviesMethodCallingCounter++
                return unknownErrorResult
            }

            override suspend fun topRatedMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                topRatedMoviesMethodCallingCounter++
                return unknownErrorResult
            }

            override suspend fun upcomingMovies(
                language: String?,
                page: Int?,
                region: String?
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                upcomingMoviesMethodCallingCounter++
                return unknownErrorResult
            }
        }

        var dispatchMethodCallCount: Int = 0
        val dispatchMethodCallCountCallback: (Int) -> Unit = { count ->
            dispatchMethodCallCount = count
        }

        val executor = createMockEffectExecutor(
            discoverSource,
            genreSource,
            movieSource,
            personSource,
            movieLocalDataSource,
            dispatchMethodCallCountCallback
        )
        effect?.invoke(executor)

        assertEquals(
            expected = nowPlayingMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::nowPlayingMovies need to call 1 time"
        )

        assertEquals(
            expected = nowPopularMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::nowPopularMovies need to call 1 time"
        )
        assertEquals(
            expected = topRatedMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::topRatedMovies need to call 1 time"
        )
        assertEquals(
            expected = upcomingMoviesMethodCallingCounter,
            actual = 1,
            message = "MovieRemoteDataSource::upcomingMovies need to call 1 time"
        )

        executor.effectExecutorScope.dispatch(
            HomeAction.MovieSectionsLoaded(
                nowPlayingMovies = dataErrorMovies,
                nowPopularMovies = dataErrorMovies,
                topRatedMovies = dataErrorMovies,
                upcomingMovies = dataErrorMovies,
            )
        )
        assertEquals(
            expected = dispatchMethodCallCount,
            actual = 1,
            message = "[executor.effectExecutorScope.dispatch] need to call 1 time"
        )
    }
}
