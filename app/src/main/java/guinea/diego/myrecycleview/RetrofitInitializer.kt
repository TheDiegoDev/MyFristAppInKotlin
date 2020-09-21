

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer (repo: String){
        private val retrofit = Retrofit.Builder()
                .baseUrl(repo)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    fun characterService() = retrofit.create(CharacterService::class.java)!!
}