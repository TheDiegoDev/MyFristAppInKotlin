package guinea.diego.myrecycleview.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import guinea.diego.myrecycleview.data.modelo.UrlOrigin
import guinea.diego.myrecycleview.utils.BaseCallback

class OriginViewModel: ViewModel() {
    private val respositorio = Single.characterRepository
    val viewMLD = MutableLiveData<UrlOrigin>()
    val viewErrorMLD = MutableLiveData<Error>()

    fun getOriginUrl(numLocation: String) {
        respositorio.getUrlOrigin(object : BaseCallback<UrlOrigin> {
            override fun onResult(result: UrlOrigin) {
                viewMLD.value = result
            }
            override fun onError(error: Error) {
                viewErrorMLD.value = error
            }
        },numLocation)
    }
}