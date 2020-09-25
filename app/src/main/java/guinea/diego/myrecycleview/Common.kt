package guinea.diego.myrecycleview

interface BaseCallback<T> {
    fun onResult(result: T)
    fun onError(error: Error)
}