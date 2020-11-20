package guinea.diego.myrecycleview.viewmodel



import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.modelo.InfoRM


class MainViewModel() {
    private var dataOnScreen: ArrayList<InfoRM> = arrayListOf()
    private val respositorio = Single.characterRepository
    private var cont = 2


    fun getCharactersVM(viewCallback: BaseCallback<Characters>) {
        respositorio.getCharacters(object : BaseCallback<Characters> {
            override fun onResult(result: Characters) {
//                dataOnScreen.clear()
//                dataOnScreen.addAll(result.info)
                viewCallback.onResult(result)
            }
            override fun onError(error: Error) {
                viewCallback.onError(error)
            }
        })
    }
    fun getPageCharacters(viewCallback: BaseCallback<Characters>) {
        val num = cont.toString()
        respositorio.getDataScroll(object : BaseCallback<Characters> {
            override fun onResult(result: Characters) {
                viewCallback.onResult(result)
                cont = cont + 1
            }

            override fun onError(error: Error) {
                viewCallback.onError(error)
            }
        }, num)
    }
}


