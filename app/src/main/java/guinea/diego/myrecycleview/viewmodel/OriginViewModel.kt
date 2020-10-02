package guinea.diego.myrecycleview.viewmodel

import guinea.diego.myrecycleview.modelo.UrlOrigin
import guinea.diego.myrecycleview.remote.CharacterRepository
import guinea.diego.myrecycleview.servicios.BaseCallback

class OriginViewModel {

    private val characterRepository: CharacterRepository = CharacterRepository()

    fun getOriginUrl(viewCallback: BaseCallback<UrlOrigin>) {
        characterRepository.getUrlOrigin(object : BaseCallback<UrlOrigin> {
            override fun onResult(result: UrlOrigin) {
                viewCallback.onResult(result)
            }
            override fun onError(error: Error) {
                viewCallback.onError(error)
            }
        })
    }
}