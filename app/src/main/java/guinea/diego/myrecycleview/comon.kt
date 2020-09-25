package guinea.diego.myrecycleview

import retrofit2.Callback
import retrofit2.Response

    public fun <T> Callback<T>.onFailure(t: Throwable): Throwable {
    return t
    }

 public fun <T> Callback<T>.onResponse(response: Response<T>): T? {
     return response.body()
    }
