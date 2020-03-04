package fr.geraud.mycplusplusmovies.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import fr.geraud.mycplusplusmovies.MoviesControllerWrapper
import fr.geraud.mycplusplusmovies.R
import fr.geraud.mycplusplusmovies.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.*

class MovieDetailFragment : Fragment() {

    private val args: MovieDetailFragmentArgs by navArgs()
    private val viewModel = MovieDetailViewModel(MoviesControllerWrapper())
    private val actorAdapter = ActorAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewDataBinding: FragmentDetailBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.viewmodel = viewModel
        return viewDataBinding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadMovieDetail(args.movieName)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailActorsRecycler.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        movieDetailActorsRecycler.adapter = actorAdapter
        viewModel.actors.observe(viewLifecycleOwner, Observer { actorAdapter.setData(it) })
    }

}