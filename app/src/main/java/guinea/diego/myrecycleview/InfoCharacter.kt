package guinea.diego.myrecycleview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import guinea.diego.myrecycleview.local.DB_Helper
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.viewmodel.InfoViewModel
import kotlinx.android.synthetic.main.info_character.*

class InfoCharacter : AppCompatActivity() {

    private val viewModel = InfoViewModel()
    private var result: CharacterRM? = null
    lateinit var handler: DB_Helper
    private var dataBaseCharacters: ArrayList<CharacterRM> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_character)
        handler = DB_Helper(this)
        configPage()
    }

    private fun configPage(){
        val personsID = intent.getIntExtra("persons", 0)

        viewModel.getAllCharacters(object : BaseCallback<CharacterRM> {
            override fun onResult(result: CharacterRM) {
                importData(result)
            }
            override fun onError(error: Error) {
                dataBaseCharacters = handler.readCharactersData()
                showError(error, dataBaseCharacters[personsID])
            }
        },personsID.toString())
    }
    private fun showError(error: Error, characters: CharacterRM) {
        if (characters != null){
            name_character.text = characters.name
            raza_character.text = characters.species
            status_character.text = characters.status
            side_character.text = characters.type
            sex_character.text = characters.gender
            btn_location.text = characters.location?.name
            btn_origin.text = characters.origin?.name
            Glide.with(img_character.context)
                .load(characters.image)
                .into(img_character)

            btn_origin.setOnClickListener {
                val intent: Intent = Intent(this, OriginDetail::class.java)
                intent.putExtra("url", characters.origin?.url)
                intent.putExtra("name", characters.origin?.name)
                startActivity(intent)
            }
        }else{
            error_txt.text = "$error"
        }
    }

    private fun importData(result: CharacterRM) {

            name_character.text = result.name
            raza_character.text = result.species
            status_character.text = result.status
            side_character.text = result.type
            sex_character.text = result.gender
            btn_location.text = result.location?.name
            btn_origin.text = result.origin?.name
            Glide.with(img_character.context)
                .load(result.image)
                .into(img_character)

        btn_origin.setOnClickListener {
            val intent: Intent = Intent(this, OriginDetail::class.java)
            intent.putExtra("url", result.origin?.url)
            startActivity(intent)
        }
    }

}