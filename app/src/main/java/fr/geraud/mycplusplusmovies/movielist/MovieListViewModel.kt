package fr.geraud.mycplusplusmovies.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.geraud.mycplusplusmovies.Movie
import fr.geraud.mycplusplusmovies.MoviesControllerWrapper
import fr.geraud.mycplusplusmovies.core.NonNullMutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class MovieListViewModel(private val controller: MoviesControllerWrapper) : ViewModel(),
    CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default

    private val _state = NonNullMutableLiveData(State(emptyList()))

    data class State(val movies: List<Movie>)

    val state: LiveData<State>
        get() = _state

    fun loadMovies() {
        Timber.d("loadMovies()")
        launch {
            controller.getMovies().let {
                launch(Dispatchers.Main) {
                    _state.value = _state.value.copy(movies = it.asList())
                }
            }
        }
    }


}