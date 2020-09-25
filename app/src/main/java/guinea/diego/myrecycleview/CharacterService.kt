import guinea.diego.myrecycleview.modelo.Characters
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET("character")
     fun list() : Call<Characters>
}