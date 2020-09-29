<<<<<<< Updated upstream:app/src/main/java/guinea/diego/myrecycleview/RetrofitInitializer.kt
=======
import com.ihsanbal.logging.LoggingInterceptor
import guinea.diego.myrecycleview.servicios.CharacterService
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer(repo: String) {
    private fun getLoggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor.Builder()
            //TODO esto debería cogerse de un config.debug
            .log(Platform.INFO)
            .request("Request")
            .response("Response")
            .build()
    }
>>>>>>> Stashed changes:app/src/main/java/guinea/diego/myrecycleview/servicios/RetrofitInitializer.kt


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
        private val retrofit = Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    fun characterService() = retrofit.create(CharacterService::class.java)!!
}