package carbon.tmbd.moviedetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import carbon.tmbd.R

import carbon.tmbd.data.model.Movie
import carbon.tmbd.util.obtainViewModel
import carbon.tmbd.util.replaceFragmentInActivity

/**
 * Activity class for movie detail.
 */
class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val MOVIE = "Movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewFragment()
    }

    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.contentFrame)
                ?: MovieDetailFragment.newInstance(intent.getSerializableExtra(MOVIE) as Movie).let {
                    replaceFragmentInActivity(it, R.id.contentFrame)
                }
    }

    fun obtainViewModel(): MovieDetailViewModel = obtainViewModel(MovieDetailViewModel::class.java)
}