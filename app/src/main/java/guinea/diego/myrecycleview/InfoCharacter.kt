package guinea.diego.myrecycleview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import guinea.diego.myrecycleview.local.DB_Helper
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.servicios.BaseCallback
import guinea.diego.myrecycleview.servicios.Connectivity
import guinea.diego.myrecycleview.viewmodel.InfoViewModel
import kotlinx.android.synthetic.main.info_character.*

class InfoCharacter : AppCompatActivity() {

    private val viewModel = InfoViewModel()
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
        viewModel.getAllCharacters(personsID.toString())
        Observable(personsID)
    }

    fun Observable(id: Int){
        if (Connectivity().isConnected(this)){
            viewModel.viewMLD.observe(this, Observer {
                importData(it)
            })
        }else{
            dataBaseCharacters = handler.readCharactersData()
            showError(dataBaseCharacters[id])
        }

    }
    private fun showError( characters: CharacterRM) {
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
            error_txt.text = "Error"
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