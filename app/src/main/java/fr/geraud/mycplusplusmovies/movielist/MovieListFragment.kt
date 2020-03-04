package fr.geraud.mycplusplusmovies.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import fr.geraud.mycplusplusmovies.MoviesControllerWrapper
import fr.geraud.mycplusplusmovies.R
import kotlinx.android.synthetic.main.fragment_movie_list.*
import timber.log.Timber

class MovieListFragment : Fragment() {

    private val viewModel = MovieListViewModel(MoviesControllerWrapper())
    private var adapter = MovieAdapter(::onMovieClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_list, container, false)

    override fun onStart() {
        super.onStart()
        viewModel.loadMovies()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieListRecycler.layoutManager = LinearLayoutManager(context)
        movieListRecycler.adapter = adapter
        viewModel.state.observe(viewLifecycleOwner, Observer { render(it) })
    }

    private fun render(viewModelState: MovieListViewModel.State) {
        Timber.d("render($viewModelState)")
        adapter.setData(viewModelState.movies)
    }

    private fun onMovieClicked(movieName: String) {
        MovieListFragmentDirections.actionGoDetail(movieName = movieName)
            .let { findNavController().navigate(it) }
    }
}