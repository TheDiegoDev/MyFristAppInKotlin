package guinea.diego.myrecycleview.viewmodel


import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.servicios.BaseCallback


class InfoViewModel {

    private var dataOnScreen: ArrayList<CharacterRM> = arrayListOf()
    private val viewModel = MainViewModel()

    fun getAllCharacters(viewCallback: BaseCallback<Characters>){
        viewModel.getCharactersVM(object : BaseCallback<Characters> {
            override fun onResult(result: Characters) {
                dataOnScreen.clear()
                dataOnScreen.addAll(result.results)
                viewCallback.onResult(result)
            }
            override fun onError(error: Error) {
                viewCallback.onError(error)
            }
        })

    }

    fun filterContent(persons: Int): CharacterRM? {

        var response: CharacterRM? = null
        var cont: Int = 0
        for (n in dataOnScreen){
            if (dataOnScreen[cont].id == persons){
                response = dataOnScreen[cont]
            }
            cont++
        }
        return response
    }

}


