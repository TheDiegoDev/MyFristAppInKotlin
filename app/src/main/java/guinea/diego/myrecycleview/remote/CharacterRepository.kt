package guinea.diego.myrecycleview.remote


import RetrofitInitializer
import guinea.diego.myrecycleview.modelo.*
import guinea.diego.myrecycleview.servicios.BaseCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Path
import retrofit2.http.Query

class CharacterRepository {

    private val characterService = RetrofitInitializer(PrincipalRepo).characterService()
    private val urlOriginService = RetrofitInitializer(PrincipalRepo).OriginService()
    private val pageService = RetrofitInitializer(PrincipalRepo).PageCharacters()

    fun getCharacters(callback: BaseCallback<Characters>) {

        characterService.list().enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if (response.body() == null) {
                    callback.onError(Error("esta vacio"))
                } else {
                    callback.onResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<Characters>, t: Throwable) {
                callback.onError(Error(t))
                
            }
        })
    }

    fun getDataScroll(callback: BaseCallback<Characters>,page:String){
        pageService.getPage(page).enqueue((object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                if (response.body() == null) {
                    callback.onError(Error("esta vacio"))
                } else {
                    callback.onResult(response.body()!!)
                }
            }
            override fun onFailure(call: Call<Characters>, t: Throwable) {
                callback.onError(Error(t))

            }
        }))

    }

    fun getUrlOrigin(callback: BaseCallback<UrlOrigin>, numLocation: String){
        urlOriginService.location(numLocation).enqueue(object : Callback<UrlOrigin>{
            override fun onFailure(call: Call<UrlOrigin>, t: Throwable) {
                callback.onError(Error(t))
            }

            override fun onResponse(call: Call<UrlOrigin>, response: Response<UrlOrigin>) {
                if (response.body() == null) {
                    callback.onError(Error("esta vacio"))
                } else {
                    callback.onResult(response.body()!!)
                }
            }
        })
    }



}






