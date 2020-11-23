package guinea.diego.myrecycleview.utils

interface BaseCallback<T> {
    fun onResult(result: T)
    fun onError(error: Error)
}


