package fr.geraud.mycplusplusmovies.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.geraud.mycplusplusmovies.Actor
import fr.geraud.mycplusplusmovies.MoviesControllerWrapper
import fr.geraud.mycplusplusmovies.core.NonNullMutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MovieDetailViewModel(private val controller: MoviesControllerWrapper) : ViewModel(),
    CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default

    private val _name = NonNullMutableLiveData<String>("")
    val name: LiveData<String>
        get() = _name

    private val _score = NonNullMutableLiveData<String>("")
    val score: LiveData<String>
        get() = _score

    private val _description = NonNullMutableLiveData<String>("")
    val description: LiveData<String>
        get() = _description

    private val _actors = NonNullMutableLiveData<List<Actor>>(emptyList())
    val actors: LiveData<List<Actor>>
        get() = _actors

    fun loadMovieDetail(movieName: String) {
        launch {
            controller.getMovieDetail(movieName)
                .let {
                    launch(Dispatchers.Main) {
                        _score.value = "%.2f".format(it.score)
                        _description.value = it.description
                        _name.value = it.name
                        _actors.value = it.actors.asList()
                    }
                }
        }
    }
}