package guinea.diego.myrecycleview
import RetrofitInitializer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.modelo.PrincipalRepo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Llamamos a la funcion setUpRecyclerView
        setUpRecyclerView()

    }

    //Esta funcion se encarga de conectar con la Api y decantrse entre dos funciones segun la respuesta
    private fun setUpRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        val call = RetrofitInitializer(PrincipalRepo).characterService().list()
        call.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                onResp(response) //Llamamos a la funcion en caso de respuesta
            }
            override fun onFailure(call: Call<Characters>, t: Throwable) {
                onFaild(t) // Llamamos a la funcion en caso de error
            }
        })
    }

    //Llamamos a la funcion que configura el recyclerView
    private fun onResp(response: Response<Characters>) {
        val list = response.body()
        list?.let {
            progressBar.visibility = View.INVISIBLE
            errorTxt.visibility = View.INVISIBLE
            confList(list)

    }
    }

    //Se muestra un mensaje de error
    private fun onFaild(t: Throwable) {
        progressBar.visibility = View.INVISIBLE
        errorTxt.text = t.message
    }

    //Configuracion del recyclerView con los datos recojidos por la Api
    private fun confList(characters: Characters){
        val recycler = recyclerView
        recycler.adapter = RecyclerAdapter(characters , this)
        val layoutRecycler = StaggeredGridLayoutManager(
            1, StaggeredGridLayoutManager.VERTICAL)
        recycler.layoutManager = layoutRecycler
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

    }
}