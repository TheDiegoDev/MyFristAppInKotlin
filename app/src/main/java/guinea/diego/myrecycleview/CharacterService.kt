import guinea.diego.myrecycleview.modelo.Characters
import retrofit2.Call
import retrofit2.http.GET

interface CharacterService {
    @GET("character")
    fun list() : Call<Characters>
}