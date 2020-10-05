package guinea.diego.myrecycleview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import guinea.diego.myrecycleview.modelo.*
import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.viewmodel.MainViewModel
import guinea.diego.myrecycleview.viewmodel.OriginViewModel
import kotlinx.android.synthetic.main.origin_detall.*
import java.net.URL


class OriginDetail: AppCompatActivity() {

    private val viewModel = OriginViewModel()
    private var infoOrigin: UrlOrigin? = null


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.origin_detall)

            configPage()
        }

    private fun configPage() {
        val urlRepo = intent.getStringExtra("url")
        val numLocation = viewModel.getNumericValues(urlRepo.toString())
         viewModel.getOriginUrl(object : BaseCallback<UrlOrigin> {
            override fun onResult(result: UrlOrigin) {
                importData(result)
            }

            override fun onError(error: Error) {
                onFaild(error)
            }
        }, numLocation)
    }

    private fun onFaild(error: Error) {
        error_txt.text = "$error"
    }

    private fun importData(result: UrlOrigin) {
        name_planet.text = result.name
        type_planet.text = result.type
        dimension.text = result.dimension
    }
}