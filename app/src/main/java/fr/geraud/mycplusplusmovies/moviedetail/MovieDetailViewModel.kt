package fr.geraud.mycplusplusmovies.moviedetail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class MovieDetailViewModel : ViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Default

}