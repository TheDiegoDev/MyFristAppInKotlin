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
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()

    }

    private fun setUpRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)

        val call = RetrofitInitializer().characterService().list()
        call.enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                onResp(response)
            }
            override fun onFailure(call: Call<Characters>, t: Throwable) {
                onFaild(t)
            }
        })
    }


    private fun onResp(response: Response<Characters>) {
        val list = response.body()
        list?.let {
            progressBar.visibility = View.INVISIBLE
            confList(list)

        }
    }

    private fun onFaild(t: Throwable) {
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
    private fun gneerardats() : ArrayList<CharacterRM>{
        val lista = ArrayList<CharacterRM>()
        Log.i("lista", "$lista")
        return lista
    }

}