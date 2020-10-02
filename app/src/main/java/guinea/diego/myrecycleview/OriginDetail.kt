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
            UrlRepo = intent.getStringExtra("url").toString()
            configPage()
        }

    private fun configPage() {
        name_planet.text = UrlRepo
         viewModel.getOriginUrl(object : BaseCallback<UrlOrigin> {
            override fun onResult(result: UrlOrigin) {
                importData(result)
            }

            override fun onError(error: Error) {
                onFaild(error)
            }
        })
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