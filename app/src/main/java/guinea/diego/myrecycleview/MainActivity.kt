package guinea.diego.myrecycleview
import RetrofitInitializer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.servicios.RecyclerAdapter
import guinea.diego.myrecycleview.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()

    }

    private fun setUpRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)

<<<<<<< Updated upstream
        val call = RetrofitInitializer().characterService().list()
        call.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                onResp(response)
            }
            override fun onFailure(call: Call<Characters>, t: Throwable) {
                onFaild(t)
=======
        viewModel.getCharactersVM(object :
            BaseCallback<Characters> {
            override fun onResult(result: Characters) {
                onResp(result)
            }

            override fun onError(error: Error) {
                onFailed(error)
>>>>>>> Stashed changes
            }
        })
    }

<<<<<<< Updated upstream

    private fun onResp(response: Response<Characters>) {
        val list = response.body()
        list?.let {
=======
    //TODO añadir filtrado
    //Llamamos a la funcion que configura el recyclerView
    private fun onResp(response: Characters) {
        response.let {
>>>>>>> Stashed changes
            progressBar.visibility = View.INVISIBLE
            confList(list)

        }
    }

<<<<<<< Updated upstream
    private fun onFaild(t: Throwable) {
=======
    //Se muestra un mensaje de error
    private fun onFailed(t: Throwable) {
>>>>>>> Stashed changes
        progressBar.visibility = View.INVISIBLE
        errorTxt.text = t.message
    }

    private fun confList(characters: Characters){
        val recycler = recyclerView
        recycler.adapter = RecyclerAdapter(characters , this)
        val layoutRecycler = StaggeredGridLayoutManager(
            1, StaggeredGridLayoutManager.VERTICAL)
        recycler.layoutManager = layoutRecycler
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }
}