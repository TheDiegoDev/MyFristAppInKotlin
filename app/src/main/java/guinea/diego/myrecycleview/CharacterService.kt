import guinea.diego.myrecycleview.modelo.Characters
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET("character")
    //suspend fun getAllCharacters() : Response<Characters>
     fun list() : Call<Characters>

//    @GET("character/{id}")
//   suspend fun getCharacter(@Path("id") id: Int): Response<Characters>
}