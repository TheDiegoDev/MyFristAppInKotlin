package guinea.diego.myrecycleview.viewmodel


import Single
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.servicios.BaseCallback


class InfoViewModel {
     var dataOnScreen: ArrayList<CharacterRM> = arrayListOf()
    private val respositorio = Single.Repository()

    fun getAllCharacters(viewCallback: BaseCallback<Characters>){
        respositorio.getCharacters(object : BaseCallback<Characters> {
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
        for ((cont) in dataOnScreen.withIndex()){
            if (dataOnScreen[cont].id == persons){
                response = dataOnScreen[cont]
            }
        }
        return response
    }

}


