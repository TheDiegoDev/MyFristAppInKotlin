package guinea.diego.myrecycleview

import android.app.Activity
import android.app.Dialog
import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.getSystemService
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import guinea.diego.myrecycleview.local.DB_Helper
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.adapter.RecyclerAdapter
import guinea.diego.myrecycleview.servicios.Connectivity
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
    private var loadingDialog: Dialog? = null



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

        viewModel.getAll()
        Observer()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getPageCharacters()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun Observer(){
        if (Connectivity().isConnected(this)){
            viewModel.viewMLD.observe(this, Observer {
                onResp(it)
            })
        }else{
            onFaild()
        }

    }

    private fun onResp(response: Characters) {
            response?.let {
                stopAnimacion()
                errorTxt.visibility = View.INVISIBLE
                updateData(response)
            }
    }

    private fun onFaild() {
        stopAnimacion()
        if(handler.readCharactersData() != null){
            dataBaseCharacters = handler.readCharactersData()
            (recyclerView.adapter as RecyclerAdapter).setData(dataBaseCharacters)
        }else{
            errorTxt.text = "Error"
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

    private fun updateData(data: Characters) {
        mainCharacters.addAll(data.results)
        handler.importDataCharacters(data)
        dataBaseCharacters =  handler.readCharactersData()
        (recyclerView.adapter as RecyclerAdapter).setData(mainCharacters)
    }
}
