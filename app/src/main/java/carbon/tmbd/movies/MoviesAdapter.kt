package carbon.tmbd.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import carbon.tmbd.data.model.Movie

class MoviesAdapter(
    private var movies: MutableList<Movie>,
    private val moviesViewModel: MoviesViewModel
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val itemBinding = MovieItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item = movies[position]
        holder?.run {
            bind(item)
            binding.root.setOnClickListener({ moviesViewModel.openMovieDetailsEvent.value = item })
        }
    }

    fun addToList(movies: List<Movie>) {
        val prevCount = itemCount
        this.movies.clear()
        this.movies.addAll(movies)
        if (prevCount > movies.size) {
            notifyDataSetChanged()
        } else {
            notifyItemRangeInserted(prevCount, movies.size)
        }
    }

    inner class ViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            with(binding) {
                movie = item
                executePendingBindings()
            }
        }
    }
}
