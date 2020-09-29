package guinea.diego.myrecycleview.servicios

import guinea.diego.myrecycleview.modelo.Characters
import retrofit2.Call
import retrofit2.http.GET
<<<<<<< Updated upstream:app/src/main/java/guinea/diego/myrecycleview/CharacterService.kt
=======

>>>>>>> Stashed changes:app/src/main/java/guinea/diego/myrecycleview/servicios/CharacterService.kt

interface CharacterService {
    @GET("character")
    fun list() : Call<Characters>
}