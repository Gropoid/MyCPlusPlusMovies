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
    private var adapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieListDebugNavButton.setOnClickListener {
            findNavController().navigate(R.id.action_go_detail)
        }
        movieListRecycler.layoutManager = LinearLayoutManager(context)
        movieListRecycler.adapter = adapter
        viewModel.state.observe(viewLifecycleOwner, Observer { render(it) })
    }

    private fun render(viewModelState: MovieListViewModel.State) {
        Timber.d("render($viewModelState)")
        adapter.setData(viewModelState.movies)
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadMovies()
    }
}