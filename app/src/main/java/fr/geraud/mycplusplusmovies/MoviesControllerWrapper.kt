package fr.geraud.mycplusplusmovies

import android.util.Log

class MoviesControllerWrapper {

    private val handle: Long

    init {
        handle = createMovieController()
        Log.d(TAG, "handle = $handle")
    }

    fun getMovies() = getMovies(handle).also {
        Log.d(TAG, "getMovies returned : $it")
    }

    private external fun createMovieController(): Long
    private external fun disposeMovieController(handle: Long)
    private external fun getMovies(handle: Long): Movie
    external fun getMovieDetail(handle: Long, name: String)

    companion object {

        init {
            System.loadLibrary("movies-lib")
        }

        const val TAG = "MoviesControllerWrapper"

    }

    fun dispose() {
        disposeMovieController(handle)
    }

}

data class Actor(val name: String, val age: Int, val imageUrl: String)
data class MovieDetail(val name: String, val score: Double, val imageUrl: String)
