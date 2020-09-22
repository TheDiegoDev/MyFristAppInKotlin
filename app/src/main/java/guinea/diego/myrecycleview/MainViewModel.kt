package guinea.diego.myrecycleview

import guinea.diego.myrecycleview.modelo.Characters
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainViewModel {

   lateinit var characterRepository:CharacterRepository

    fun getCharactersVM() {
        characterRepository = CharacterRepository()

        val list = characterRepository.getCharacters(object : retrofit2.Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {


            }
            override fun onFailure(call: Call<Characters>, t: Throwable){

            }
        })
    }
}