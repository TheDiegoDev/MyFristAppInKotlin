package guinea.diego.myrecycleview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import guinea.diego.myrecycleview.local.DB_Helper
import guinea.diego.myrecycleview.modelo.*
import guinea.diego.myrecycleview.servicios.getNumericValues
import guinea.diego.myrecycleview.viewmodel.OriginViewModel
import kotlinx.android.synthetic.main.origin_detall.*



class OriginDetail: AppCompatActivity() {

    private val viewModel = OriginViewModel()
    lateinit var handler: DB_Helper

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.origin_detall)
            handler = DB_Helper(this)

            configPage()
        }

    private fun configPage() {
        val urlRepo = intent.getStringExtra("url")
        val planet = intent.getStringExtra("name")
        val numLocation = urlRepo?.getNumericValues().toString()
        Observer(planet)
        viewModel.getOriginUrl(numLocation)
    }

    fun Observer(name: String?){
        viewModel.viewMLD.observe(this, Observer {
            handler.importDataUrl(it)
            importData(it)
        })
        viewModel.viewErrorMLD.observe(this, Observer {
            val valorURl = name?.let { handler.readUrlData(it)}
            if (it != null && valorURl == null){
                error_txt.text = it.toString()
            }else{
                onFaild(valorURl)
            }
        })
    }

    private fun onFaild(valorUrl: UrlOrigin?) {
            name_planet.text = valorUrl?.name
            type_planet.text = valorUrl?.type
            dimension.text = valorUrl?.dimension
    }

    private fun importData(result: UrlOrigin) {
            name_planet.text = result.name
            type_planet.text = result.type
            dimension.text = result.dimension
    }
}