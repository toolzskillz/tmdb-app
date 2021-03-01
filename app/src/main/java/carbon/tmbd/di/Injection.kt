package carbon.tmbd.di

import android.content.Context
import carbon.tmbd.data.Repository
import carbon.tmbd.service.ApiService
import carbon.tmbd.service.RemoteDataSource
import carbon.tmbd.service.RemoteDataSource.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injection {

    fun provideTasksRepository(context: Context): Repository {

        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val apiService = retrofit.create(ApiService::class.java)
        return Repository.getInstance(RemoteDataSource.getInstance(apiService))
    }
}
