package guinea.diego.myrecycleview.viewmodel

import guinea.diego.myrecycleview.modelo.UrlOrigin
import guinea.diego.myrecycleview.remote.CharacterRepository
import guinea.diego.myrecycleview.servicios.BaseCallback
import java.lang.Double.parseDouble

class OriginViewModel {
    private val respositorio = Single.characterRepository

    fun getOriginUrl(viewCallback: BaseCallback<UrlOrigin>, numLocation: String) {
        respositorio.getUrlOrigin(object : BaseCallback<UrlOrigin> {
            override fun onResult(result: UrlOrigin) {
                viewCallback.onResult(result)
            }
            override fun onError(error: Error) {
                viewCallback.onError(error)
            }
        },numLocation)
    }

    fun getNumericValues(cadena: String): String {
        val sb = StringBuilder()
        for (i in cadena.indices) {
            var numeric = true
            try {
                val num = parseDouble(cadena[i].toString())
            } catch (e: NumberFormatException) {
                numeric = false
            }
            if (numeric) {
                sb.append(cadena[i].toString())
            }
        }
        return sb.toString();
    }
}