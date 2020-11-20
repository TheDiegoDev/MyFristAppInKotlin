package guinea.diego.myrecycleview

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import guinea.diego.myrecycleview.local.DB_Helper
import guinea.diego.myrecycleview.modelo.*
import guinea.diego.myrecycleview.servicios.BaseCallback
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
         viewModel.getOriginUrl(object : BaseCallback<UrlOrigin> {
            override fun onResult(result: UrlOrigin) {
                handler.importDataUrl(result)
                importData(result)
            }

            override fun onError(error: Error) {
                planet?.let { onFaild(error, it) }
            }
        }, numLocation)
    }

    private fun onFaild(error: Error, planet: String) {
        val response = handler.readUrlData(planet)
        if(response != null){
            name_planet.text = response.name
            type_planet.text = response.name
            dimension.text = response.dimension
        }else{
            error_txt.text = "$error"
        }

    }

    private fun importData(result: UrlOrigin) {
            name_planet.text = result.name
            type_planet.text = result.type
            dimension.text = result.dimension
    }
}