
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.modelo.UrlOrigin
import retrofit2.Call
import retrofit2.http.*


interface CharacterService {
    @GET("character")        ///?page={num}
    fun list() : Call<Characters>  //@Path("num")page:String
}

interface PersonService {
    @GET("character/{id}")        ///?page={num}
    fun getPerson(@Path("id")id:String) : Call<CharacterRM>  //@Path("num")page:String
}

interface PageService {
    @GET("character/")        ///?page={num}
    fun getPage(@Query("page")page:String) : Call<Characters>  //@Path("num")page:String
}

interface OriginService{
    @GET("location/{id}")
    fun location(@Path("id")id:String) : Call<UrlOrigin>
}
