package guinea.diego.myrecycleview.remote

import RetrofitInitializer
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.modelo.PrincipalRepo
import guinea.diego.myrecycleview.onFailure
import guinea.diego.myrecycleview.onResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

//class CharacterRepository @Inject constructor(
//    private val remoteDataSource: CharacterRemoteDataSource
//) {
//
//    fun getCharacter(id: Int) = performGetOperation(
//        networkCall = { remoteDataSource.getCharacter(id) }
//    )
//
//    fun getCharacters() = performGetOperation(
//        networkCall = { remoteDataSource.getCharacters() }
//    )
//}
class CharacterRepository {

    //    fun saveCharacter(response: Response<Characters>) : Characters? {
//        val list = response.body()
//        return list
//    }

    fun getCharacters(callback: Callback<Characters>) {

        val call = RetrofitInitializer(PrincipalRepo).characterService().list()

         call.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                callback.onResponse(response)
            }
            override fun onFailure(call: Call<Characters>, t: Throwable){
                callback.onFailure(t)
            }
        })


    }

}




