
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.modelo.UrlOrigin
import retrofit2.Call
import retrofit2.http.GET


interface CharacterService {
    @GET("character")
    fun list() : Call<Characters>
}
interface urlOriginService{
    @GET("")
    fun locatcion() : Call<UrlOrigin>
}
