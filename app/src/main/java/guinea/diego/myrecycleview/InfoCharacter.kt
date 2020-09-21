package guinea.diego.myrecycleview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import guinea.diego.myrecycleview.modelo.CharacterRM
import kotlinx.android.synthetic.main.info_character.*


class InfoCharacter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_character)
        configPage()
    }

    //Funcion encargada de cargar los datos del intent
    private fun configPage(){
        val persons = intent.getSerializableExtra("persons") as CharacterRM
        name_character.text = persons.name
        raza_character.text = persons.species
        status_character.text = persons.status
        side_character.text = persons.type
        sex_character.text = persons.gender
        //btn_location.text = persons.name
        //btn_origin.text = persons.origin.name
        Glide.with(img_character.context)
            .load(persons.image)
            .into(img_character)
    }
}