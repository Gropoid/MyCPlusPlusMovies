package fr.geraud.mycplusplusmovies

import android.util.Log

class MoviesControllerWrapper {

    private val handle: Long

    init {
        handle = createMovieController()
        Log.d(TAG, "handle = $handle")
    }

    fun getMovies() = getMovies(handle)
        .also { Log.d(TAG, "getMovies returned : $it") }

    fun getMovieDetail(name: String) = getMovieDetail(handle, name)
        .also { Log.d(TAG, "getMovieDetails returned : $it") }

    private external fun createMovieController(): Long
    private external fun disposeMovieController(handle: Long)
    private external fun getMovies(handle: Long): Array<Movie>
    private external fun getMovieDetail(handle: Long, name: String) : MovieDetail

    fun dispose() {
        disposeMovieController(handle)
    }

    companion object {
        const val TAG = "MoviesControllerWrapper"
        init {
            System.loadLibrary("movies-lib")
        }
    }
}


