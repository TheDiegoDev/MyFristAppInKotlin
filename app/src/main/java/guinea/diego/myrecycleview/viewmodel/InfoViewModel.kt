package guinea.diego.myrecycleview.viewmodel


import Single
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.servicios.BaseCallback


class InfoViewModel {
     var dataOnScreen: CharacterRM? = null
    private val respositorio = Single.Repository()

    fun getAllCharacters(viewCallback: BaseCallback<CharacterRM>, personId: String){
        respositorio.getCharactersID(object : BaseCallback<CharacterRM> {
            override fun onResult(result: CharacterRM) {
                dataOnScreen = result
                viewCallback.onResult(result)
            }
            override fun onError(error: Error) {
                viewCallback.onError(error)
            }
        },personId)

    }
//    fun filterContent(persons: Int): CharacterRM? {
//        var response: CharacterRM? = null
//        for ((cont) in dataOnScreen.withIndex()){
//            if (dataOnScreen[cont].id == persons){
//                response = dataOnScreen[cont]
//            }
//        }
//        return response
//    }

}


