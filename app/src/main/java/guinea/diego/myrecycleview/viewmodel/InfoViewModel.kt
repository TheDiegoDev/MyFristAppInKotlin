package guinea.diego.myrecycleview.viewmodel


import Single
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.modelo.UrlOrigin
import guinea.diego.myrecycleview.servicios.BaseCallback


class InfoViewModel: ViewModel() {

    private val respositorio = Single.Repository()
    val viewMLD = MutableLiveData<CharacterRM>()

    fun getAllCharacters(personId: String){
        respositorio.getCharactersID(object : BaseCallback<CharacterRM> {
            override fun onResult(result: CharacterRM) {
                viewMLD.value = result
            }
            override fun onError(error: Error) {}
        },personId)

    }
}


