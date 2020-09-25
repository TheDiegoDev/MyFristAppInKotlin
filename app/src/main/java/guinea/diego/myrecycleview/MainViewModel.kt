package guinea.diego.myrecycleview

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.remote.CharacterRepository
//import guinea.diego.myrecycleview.util.Resource
import retrofit2.Call
import retrofit2.Response
//class CharactersViewModel @ViewModelInject constructor(
//    private val repository: CharacterRepository
//) : ViewModel() {
//
//   // val characters: LiveData<Resource<List<Characters>>> = repository.getCharacters()
//}
class MainViewModel {

   private  var characterRepository: CharacterRepository =CharacterRepository()

    fun getCharactersVM(viewCallback: retrofit2.Callback<Characters>) {
        
         characterRepository.getCharacters(object : retrofit2.Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                viewCallback.onResponse(response)
            }
            override fun onFailure(call: Call<Characters>, t: Throwable){
                viewCallback.onFailure(t)
            }
        })

    }
}