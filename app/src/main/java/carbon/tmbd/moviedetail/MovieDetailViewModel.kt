package carbon.tmbd.moviedetail

import android.app.Application
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.widget.ImageView
import androidx.lifecycle.AndroidViewModel
import com.squareup.picasso.Picasso
import carbon.tmbd.data.Repository
import carbon.tmbd.data.model.Movie

/**
 * View model for MovieDetailActivity.
 */
class MovieDetailViewModel(
        context: Application,
        private val repository: Repository
) : AndroidViewModel(context) {

    val movie = ObservableField<Movie>()

    fun start(movieItem: Movie?) {
        movieItem?.run {
            movie.set(movieItem)
        }
    }

    companion object {
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
}