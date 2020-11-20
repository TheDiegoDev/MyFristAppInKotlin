package guinea.diego.myrecycleview

import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.*
import guinea.diego.myrecycleview.local.DB_Helper
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.adapter.RecyclerAdapter
import guinea.diego.myrecycleview.servicios.showLoadingDialog
import guinea.diego.myrecycleview.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()
    private var listAdapter: RecyclerAdapter? = null
    private var mainCharacters: ArrayList<CharacterRM> = arrayListOf()
    lateinit var handler: DB_Helper
    private var dataBaseCharacters: ArrayList<CharacterRM> = ArrayList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = DB_Helper(this)
        showDialog()
        initAdapter()
    }

    private fun initAdapter() {
        listAdapter = RecyclerAdapter(this)
        recyclerView.adapter = listAdapter
         val layoutRecycler = StaggeredGridLayoutManager(
            1, StaggeredGridLayoutManager.VERTICAL)
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
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getPageCharacters(object : BaseCallback<Characters> {
                        override fun onResult(result: Characters) {
                            addData(result)
                        }
                        override fun onError(error: Error) {
                            onFaild(error)
                        }
                    })
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

    }

    private fun onResp(response: Characters) {
        response?.let {
            stopAnimacion()
            errorTxt.visibility = View.INVISIBLE
            updateData(response)

        }
    }

    private fun onFaild(t: Throwable) {
        stopAnimacion()
        if(handler.readCharactersData() != null){
            dataBaseCharacters = handler.readCharactersData()
            (recyclerView.adapter as RecyclerAdapter).setData(dataBaseCharacters)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        val menuItem = menu!!.findItem(R.id.action_search)

        val searchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    listAdapter!!.filter.filter(query)
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    listAdapter!!.filter.filter(newText)
                    return true
                }
        })
        return true
    }
    private var loadingDialog: Dialog? = null

    private fun hideLoading(){
        loadingDialog?.let {if(it.isShowing)it.cancel()}
    }
    private fun showDialog(){
        hideLoading()
        loadingDialog = this.showLoadingDialog()
    }

    private fun stopAnimacion() {
        Handler().postDelayed({
            hideLoading()
        }, 1)
    }


//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        val id = item.itemId
//        if (id == R.id.filter_button){
//            sortDialog()
//        }
//        return super.onOptionsItemSelected(item)
//    }
//    private fun sortDialog(){
//        val options = arrayOf("opcion1", "opcion2")
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle("Filtrar contenido")
//        builder.setIcon(R.drawable.ic_launcher_filtter)
//        builder.setItems(options){dialog, which ->
//            if(which == 0){
//
//            }
//            if(which == 1){
//
//            }
//            builder.create().show()
//        }
//    }

    private fun updateData(data: Characters) {
        mainCharacters.addAll(data.results)
        handler.importDataCharacters(data)
        dataBaseCharacters =  handler.readCharactersData()
        (recyclerView.adapter as RecyclerAdapter).setData(mainCharacters)
    }


    private fun addData(result: Characters) {
        mainCharacters.addAll(result.results)
        handler.importDataCharacters(result)
        dataBaseCharacters = handler.readCharactersData()
        (recyclerView.adapter as RecyclerAdapter).addData(mainCharacters)
        stopAnimacion()
    }

}
