package carbon.tmbd.service

import carbon.tmbd.data.model.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {

    @GET("discover/movie")
    fun getMovies(@QueryMap queryMap: Map<String, String>): Call<Movies>

}