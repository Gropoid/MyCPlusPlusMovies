package fr.geraud.mycplusplusmovies

import android.util.Log
import timber.log.Timber

class MoviesControllerWrapper {

    private val handle: Long

    init {
        handle = createMovieController()
        Timber.d("handle = $handle")
    }

    fun getMovies() = getMovies(handle)
        .also { Timber.d("getMovies returned : $it") }

    fun getMovieDetail(name: String) = getMovieDetail(handle, name)
        .also { Timber.d("getMovieDetails returned : $it") }

    private external fun createMovieController(): Long
    private external fun disposeMovieController(handle: Long)
    private external fun getMovies(handle: Long): Array<Movie>
    private external fun getMovieDetail(handle: Long, name: String): MovieDetail

    fun dispose() {
        disposeMovieController(handle)
    }

    companion object {
        init {
            System.loadLibrary("movies-lib")
        }
    }
}


