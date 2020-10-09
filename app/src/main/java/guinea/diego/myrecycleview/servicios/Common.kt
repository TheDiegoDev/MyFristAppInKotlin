package guinea.diego.myrecycleview.servicios

interface BaseCallback<T> {
    fun onResult(result: T)
    fun onError(error: Error)
}


