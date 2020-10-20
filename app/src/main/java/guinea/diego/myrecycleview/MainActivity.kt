package guinea.diego.myrecycleview

import android.app.Dialog
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.servicios.RecyclerAdapter
import guinea.diego.myrecycleview.servicios.loadingDragon
import guinea.diego.myrecycleview.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private val viewModel = MainViewModel()
    private var listAdapter: RecyclerAdapter? = null
    private var loadingDialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showDialog()
        initAdapter()
    }
    private fun hideLoading(){
        loadingDialog?.let {if(it.isShowing)it.cancel()}
    }
    private fun showDialog(){
        hideLoading()
        loadingDialog = loadingDragon.showLoadingDialog(this)
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
        errorTxt.text = t.message
    }

    private fun stopAnimacion() {
        Handler().postDelayed({
            hideLoading()
        }, 0)
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

    private fun updateData(characters: Characters) {
        (recyclerView.adapter as RecyclerAdapter).setData(characters)
    }

}
