package guinea.diego.myrecycleview


import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.remote.CharacterRepository

class MainViewModel {

    private val characterRepository: CharacterRepository = CharacterRepository()
    var dataOnScreen: ArrayList<CharacterRM> = arrayListOf()

    fun getCharactersVM(viewCallback: BaseCallback<Characters>) {

        characterRepository.getCharacters(object : BaseCallback<Characters> {
            override fun onResult(result: Characters) {
                dataOnScreen.clear()
                dataOnScreen.addAll(result.results)
                //esto está duplicado
                viewCallback.onResult(result)
            }

            override fun onError(error: Error) {
                viewCallback.onError(error)
            }

        })
    }

    fun filterContent() {
        //TODO

    }
}