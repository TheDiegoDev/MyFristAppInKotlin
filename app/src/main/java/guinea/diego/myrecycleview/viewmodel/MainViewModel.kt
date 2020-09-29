package guinea.diego.myrecycleview.viewmodel


import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.remote.CharacterRepository

class MainViewModel() {

    private val characterRepository: CharacterRepository = CharacterRepository()

    fun getCharactersVM(viewCallback: BaseCallback<Characters>) {

        characterRepository.getCharacters(object :
            BaseCallback<Characters> {
            override fun onResult(result: Characters) {
                viewCallback.onResult(result)
            }
            override fun onError(error: Error) {
                viewCallback.onError(error)
            }
        })
    }
}

