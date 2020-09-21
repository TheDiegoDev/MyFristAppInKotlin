package guinea.diego.myrecycleview

import RetrofitInitializer
import android.util.Log
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.modelo.PrincipalRepo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository {

    fun getCharacters(){
        val call = RetrofitInitializer(PrincipalRepo).characterService().list()
        CallCharacter(call)

    }

    fun CallCharacter(call: Call<Characters>) {
        call.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                saveCharacter(response)
            }
            override fun onFailure(call: Call<Characters>, t: Throwable) {
                onFaild(t) // Llamamos a la funcion en caso de error
            }
        })
    }

    fun onFaild(t: Throwable): Throwable {
        Log.i("lista", t.toString())
        return t
    }

    fun saveCharacter(response: Response<Characters>): Characters? {
        val list = response.body()
        return list
    }
}