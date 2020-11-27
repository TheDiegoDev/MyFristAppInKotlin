package guinea.diego.myrecycleview.ui


import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import guinea.diego.myrecycleview.R
import guinea.diego.myrecycleview.data.local.DB_Helper
import guinea.diego.myrecycleview.data.modelo.CharacterRM
import guinea.diego.myrecycleview.data.modelo.Characters
import guinea.diego.myrecycleview.ui.adapter.RecyclerAdapter
import guinea.diego.myrecycleview.utils.showLoadingDialog
import guinea.diego.myrecycleview.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.info_character.*
import kotlinx.android.synthetic.main.spinner_item.*
import kotlinx.android.synthetic.main.switch_item.*
import org.koin.android.ext.android.inject
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    private val viewModel by inject<MainViewModel>()
    private var listAdapter: RecyclerAdapter? = null
    private var mainCharacters: ArrayList<CharacterRM> = arrayListOf()
    lateinit var handler: DB_Helper
    private var dataBaseCharacters: ArrayList<CharacterRM> = ArrayList()
    private var loadingDialog: Dialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerConfiguration()
        handler = DB_Helper(this)
        showDialog()
        initAdapter()

    }

    private fun drawerConfiguration() {
        var status: String
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
//            spinner.onItemClickListener = object : AdapterView.OnItemSelectedListener{
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//                }
//
//                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//
//                }
//            }
            if (checkBox.isChecked) { status = "Alive" }
            else { status = "Dead" }
            when (it.itemId) {
                R.id.item2 -> filterData(status/*, spinner.selectedItem.toString()*/)
            }
            true
        }
    }

    private fun filterData(status: String/*, especie: String*/) {
        val filtro = handler.filterData(status/*, especie*/)
        (recyclerView.adapter as RecyclerAdapter).setData(filtro)
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

        viewModel.getAll()
        Observer()

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.getPageCharacters()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun Observer() {
        viewModel.viewMLD.observe(this, Observer {
            onResp(it)
        })
        viewModel.viewErrorMLD.observe(this, Observer {
            if (handler.readCharactersData() == null && it != null) {
                stopAnimacion()
                errorTxt.text = it.toString()
            } else {
                ShowDataBase()
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

    private fun ShowDataBase() {
        stopAnimacion()
        dataBaseCharacters = handler.readCharactersData()
        (recyclerView.adapter as RecyclerAdapter).setData(dataBaseCharacters)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val menuItem = menu!!.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun hideLoading() {
        loadingDialog?.let { if (it.isShowing) it.cancel() }
    }

    private fun showDialog() {
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
        dataBaseCharacters = handler.readCharactersData()
        (recyclerView.adapter as RecyclerAdapter).setData(mainCharacters)
    }
}