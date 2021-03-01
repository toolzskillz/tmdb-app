
package carbon.tmbd.data.model

import com.google.gson.annotations.SerializedName
import carbon.tmbd.service.RemoteDataSource
import java.io.Serializable

data class Movie constructor(
        @SerializedName("id")
        var id: Int,
        @SerializedName("title")
        var title: String,
        @SerializedName("poster_path")
        var halfThumbUrl: String,
        @SerializedName("release_date")
        var releaseDate: String,
        @SerializedName("vote_average")
        var star: Double,
        @SerializedName("overview")
        var overview: String

) : Serializable {
    val smallThumbUrl
        get() = RemoteDataSource.SMALL_SIZE_URL + halfThumbUrl

    val bigThumbUrl
        get() = RemoteDataSource.MID_SIZE_URL + halfThumbUrl

    val movieStar
        get() = star.toString()
}
