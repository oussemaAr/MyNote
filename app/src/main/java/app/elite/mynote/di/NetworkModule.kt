package app.elite.mynote.di

import app.elite.mynote.data.network.RemoteDataSource
import app.elite.mynote.data.network.ServiceAPI
import app.elite.mynote.utils.cookies
import app.elite.mynote.utils.token
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient() = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val origin = chain.request()
            val request = origin.newBuilder()
            if (token != "") {
                request.header("Authorization", "Bearer $token")
            }
            chain.proceed(request.build())
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()


    @Singleton
    @Provides
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://192.168.73.1:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

    @Provides
    fun provideService(retrofit: Retrofit): ServiceAPI =
        retrofit.create(ServiceAPI::class.java)


    @Provides
    fun provideDataSource(serviceAPI: ServiceAPI): RemoteDataSource = RemoteDataSource(serviceAPI)

}