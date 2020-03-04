package fr.geraud.mycplusplusmovies.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import fr.geraud.mycplusplusmovies.MoviesControllerWrapper
import fr.geraud.mycplusplusmovies.R
import fr.geraud.mycplusplusmovies.databinding.FragmentDetailBinding

class MovieDetailFragment : Fragment() {

    val args: MovieDetailFragmentArgs by navArgs()
    val viewModel = MovieDetailViewModel(MoviesControllerWrapper())

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
}