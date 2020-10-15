package guinea.diego.myrecycleview

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.ActionProvider
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
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
    var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    }

    private fun onResp(response: Characters) {
        response?.let {
            progressBar.visibility = View.INVISIBLE
            errorTxt.visibility = View.INVISIBLE
            updateData(response)

        }
    }

    private fun onFaild(t: Throwable) {
        progressBar.visibility = View.INVISIBLE
        errorTxt.text = t.message
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu?.findItem(R.id.action_search)?.actionView as SearchView
        searchView?.apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            maxWidth = Int.MAX_VALUE
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    listAdapter!!.filter.filter(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    listAdapter!!.filter.filter(newText)
                    return true
                }
            })
        }
        return true
    }

    private fun updateData(characters: Characters) {
        (recyclerView.adapter as RecyclerAdapter).setData(characters)
    }

}
