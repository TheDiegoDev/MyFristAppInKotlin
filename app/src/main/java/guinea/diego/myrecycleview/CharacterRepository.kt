package guinea.diego.myrecycleview

import RetrofitInitializer
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.modelo.PrincipalRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository {

    fun getCharacters(callback: Callback<Characters>) {

        val call = RetrofitInitializer(PrincipalRepo).characterService().list()

        call.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                callback.onResponse(response)
            }
            override fun onFailure(call: Call<Characters>, t: Throwable){
                callback.onFailure(t)
                // return t
            }
        })


    }
    fun saveCharacter(response: Response<Characters>) : Characters? {
        val list = response.body()
       return list
   }
}

    private fun <T> Callback<T>.onFailure(t: Throwable) {

    }

    private fun <T> Callback<T>.onResponse(response: Response<T>) {

    }



