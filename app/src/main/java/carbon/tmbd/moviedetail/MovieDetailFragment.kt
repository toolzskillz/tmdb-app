package carbon.tmbd.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import carbon.tmbd.R
import carbon.tmbd.data.model.Movie

class MovieDetailFragment : Fragment() {

    private lateinit var viewDataBinding: MovieDetailFragBinding

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.viewmodel?.start(arguments?.getSerializable(ARGUMENT_MOVIE) as Movie)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.movie_detail_frag, container, false)
        viewDataBinding = MovieDetailFragBinding.bind(view).apply {
            viewmodel = (activity as MovieDetailActivity).obtainViewModel()
        }
        return view
    }

    companion object {

        const val ARGUMENT_MOVIE = "Movie"

        fun newInstance(movie: Movie) = MovieDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARGUMENT_MOVIE, movie)
            }
        }
    }
}