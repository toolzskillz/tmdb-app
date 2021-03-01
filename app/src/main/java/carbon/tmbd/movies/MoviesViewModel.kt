package carbon.tmbd.movies

import android.app.Application
import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.databinding.ObservableBoolean
import android.databinding.ObservableList
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import carbon.tmbd.SingleLiveEvent
import carbon.tmbd.data.DataSource
import carbon.tmbd.data.Repository
import carbon.tmbd.data.model.Movie
import carbon.tmbd.data.model.Movies

class MoviesViewModel(
        context: Application,
        private val repository: Repository
) : AndroidViewModel(context) {

    companion object {
        @JvmStatic
        @BindingAdapter("bind:items")
        fun entries(recyclerView: RecyclerView, movies: List<Movie>) =
                (recyclerView.adapter as MoviesAdapter).addToList(movies)

        @JvmStatic
        @BindingAdapter(value = ["android:src", "placeHolder"], requireAll = false)
        fun setImageUrl(view: ImageView, url: String,
                        placeHolder: Int) {
            val requestCreator = Picasso.with(view.context).load(url)
            if (placeHolder != 0) {
                requestCreator.placeholder(placeHolder)
            }
            requestCreator.into(view)
        }
    }

    val dataLoading = ObservableBoolean(false)
    val items: ObservableList<Movie> = ObservableArrayList()
    internal val openMovieDetailsEvent = SingleLiveEvent<Movie>()

    fun loadMovies(index: Int, withReleaseDate: Boolean = false) {
        dataLoading.set(true)
        repository.getMovies(index, withReleaseDate, object : DataSource.GetMoviesCallback {
            override fun onOperationComplete(movies: Movies) {
                with(items) {
                    addAll(movies.movies)
                }
                dataLoading.set(false)
            }

            override fun onOperationFailed(t: Throwable?) {
                dataLoading.set(false)
            }
        })
    }
}