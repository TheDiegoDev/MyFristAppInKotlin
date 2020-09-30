package guinea.diego.myrecycleview

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val viewModel = MainViewModel()

    private var listAdapter: RecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAdapter()
        //Llamamos a la funcion setUpRecyclerView


    }

    private fun initAdapter() {
        listAdapter = RecyclerAdapter(this)
        recyclerView.adapter = listAdapter
        val layoutRecycler = StaggeredGridLayoutManager(
            1, StaggeredGridLayoutManager.VERTICAL
        )
        recyclerView.layoutManager = layoutRecycler
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.getCharactersVM(object : BaseCallback<Characters> {
            override fun onResult(result: Characters) {
                onResp(result)
            }

            override fun onError(error: Error) {
                onFaild(error)
            }
        })
    }

    //TODO añadir filtrado
    //Llamamos a la funcion que configura el recyclerView
    private fun onResp(response: Characters) {
        response?.let {
            progressBar.visibility = View.INVISIBLE
            errorTxt.visibility = View.INVISIBLE
            updateData(response)

        }
    }

    //Se muestra un mensaje de error
    private fun onFaild(t: Throwable) {
        progressBar.visibility = View.INVISIBLE
        errorTxt.text = t.message
    }

    //Configuracion del recyclerView con los datos recojidos por la Api
    private fun updateData(characters: Characters) {
        (recyclerView.adapter as RecyclerAdapter).setData(characters)
    }

}