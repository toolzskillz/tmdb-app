package carbon.tmbd.data

import carbon.tmbd.data.model.Movies

interface DataSource {

    interface GetMoviesCallback {

        fun onOperationComplete(movies: Movies)

        fun onOperationFailed(t: Throwable? = Throwable())
    }

    fun getMovies(page: Int = 1, withReleaseDate: Boolean, callback: GetMoviesCallback)
}