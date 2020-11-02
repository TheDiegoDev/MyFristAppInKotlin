package guinea.diego.myrecycleview.servicios

import guinea.diego.myrecycleview.modelo.Characters

interface BaseCallback<T> {
    fun onResult(result: T)
    fun onError(error: Error)
}


