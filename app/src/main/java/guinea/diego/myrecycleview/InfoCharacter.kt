package guinea.diego.myrecycleview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.viewmodel.InfoViewModel
import kotlinx.android.synthetic.main.info_character.*
import kotlinx.android.synthetic.main.info_character.view.*


class InfoCharacter : AppCompatActivity() {

    private val viewModel = InfoViewModel()
    private var infoCharacter: CharacterRM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_character)
        configPage()
    }

    private fun configPage(){
        viewModel.getAllCharacters(object :
            BaseCallback<Characters> {
            override fun onResult(result: Characters) {
                importData()
            }
            override fun onError(error: Error) {
                showError(error)
            }
        })
    }

    private fun showError(error: Error) {
        error_txt.text = "$error"
    }

    private fun importData() {
        val personsID = intent.getIntExtra("persons", 0)
        infoCharacter = viewModel.filterContent(personsID)

        if (infoCharacter != null) {
            name_character.text = infoCharacter!!.name
            raza_character.text = infoCharacter!!.species
            status_character.text = infoCharacter!!.status
            side_character.text = infoCharacter!!.type
            sex_character.text = infoCharacter!!.gender
            btn_location.text = infoCharacter!!.location.name
            btn_origin.text = infoCharacter!!.origin.name
            Glide.with(img_character.context)
                .load(infoCharacter!!.image)
                .into(img_character)
        }
    }
}