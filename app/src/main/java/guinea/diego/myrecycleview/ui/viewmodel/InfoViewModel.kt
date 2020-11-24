package guinea.diego.myrecycleview.ui.viewmodel


import Single
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import guinea.diego.myrecycleview.data.modelo.CharacterRM
import guinea.diego.myrecycleview.utils.BaseCallback


class InfoViewModel: ViewModel() {

    private val respositorio = Single.Repository()
    val viewMLD = MutableLiveData<CharacterRM>()
    val viewErrorMLD = MutableLiveData<Error>()

    fun getAllCharacters(personId: String){
        respositorio.getCharactersID(object : BaseCallback<CharacterRM> {
            override fun onResult(result: CharacterRM) {
                viewMLD.value = result
            }
            override fun onError(error: Error) {
                viewErrorMLD.value = error
            }
        },personId)

    }
}


