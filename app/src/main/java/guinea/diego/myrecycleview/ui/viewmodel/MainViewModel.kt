package guinea.diego.myrecycleview.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import guinea.diego.myrecycleview.utils.BaseCallback
import guinea.diego.myrecycleview.data.modelo.Characters

class MainViewModel: ViewModel() {
    private val respositorio = Single.characterRepository
    private var cont = 2
    val viewMLD = MutableLiveData<Characters>()
    val viewErrorMLD = MutableLiveData<Error>()

    fun getAll(){
        respositorio.getCharacters(object : BaseCallback<Characters> {
            override fun onResult(result: Characters) {
                viewMLD.value = result
            }
            override fun onError(error: Error) {
                viewErrorMLD.value = error
            }
        })
    }

    fun getPageCharacters() {
        val num = cont.toString()
        respositorio.getDataScroll(object : BaseCallback<Characters> {
            override fun onResult(result: Characters) {
                viewMLD.value = result
                cont = cont + 1
            }
            override fun onError(error: Error) {
                viewErrorMLD.value = error
            }
        }, num)
    }
}


