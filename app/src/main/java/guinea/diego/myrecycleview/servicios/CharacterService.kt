
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.modelo.UrlOrigin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface CharacterService {
    @GET("character")
    fun list() : Call<Characters>
}
interface OriginService{
    @GET("location/{id}")
    fun location(@Path("id")id:String) : Call<UrlOrigin>
}
