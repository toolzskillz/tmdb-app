package carbon.tmbd.data.model

import carbon.tmbd.data.model.Movie
import com.google.gson.annotations.SerializedName

data class Movies constructor(
        @SerializedName("page")
        var page: Int,
        @SerializedName("results")
        var movies: List<Movie>
) {

}