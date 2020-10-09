import guinea.diego.myrecycleview.modelo.Characters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    @GET("character")
    fun list() : Call<Characters>
<<<<<<< Updated upstream:app/src/main/java/guinea/diego/myrecycleview/CharacterService.kt
}
=======
}
interface OriginService{
    @GET("location/{id}")
    fun location(@Path("id")id:String) : Call<UrlOrigin>
}
>>>>>>> Stashed changes:app/src/main/java/guinea/diego/myrecycleview/servicios/CharacterService.kt
