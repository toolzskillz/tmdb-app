package carbon.tmbd.movies

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import carbon.tmbd.R
import carbon.tmbd.data.model.Movie
import carbon.tmbd.moviedetail.MovieDetailActivity
import carbon.tmbd.util.obtainViewModel
import carbon.tmbd.util.replaceFragmentInActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewFragment()
        viewModel = obtainViewModel().apply {
            openMovieDetailsEvent.observe(this@MainActivity, Observer<Movie> { movie ->
                openDetailActivity(movie)
            })
        }
    }

    private fun openDetailActivity(movie: Movie?) {
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.MOVIE, movie)
        }
        startActivity(intent)
    }

    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.contentFrame)
                ?: MoviesFragment.newInstance().let {
                    replaceFragmentInActivity(it, R.id.contentFrame)
                }
    }

    fun obtainViewModel(): MoviesViewModel = obtainViewModel(MoviesViewModel::class.java)
}
