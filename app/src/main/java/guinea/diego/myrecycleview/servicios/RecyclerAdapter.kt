package guinea.diego.myrecycleview.servicios

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import guinea.diego.myrecycleview.InfoCharacter
import guinea.diego.myrecycleview.R
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters
import kotlinx.android.synthetic.main.characters.view.*
import kotlin.collections.ArrayList


class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>(), Filterable{

    private var characters: ArrayList<CharacterRM> = arrayListOf()
    private var filterCharacter: ArrayList<CharacterRM> = arrayListOf()

    init {
        filterCharacter = characters
    }
    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val busqueda = constraint.toString()
                if(constraint.toString().isEmpty()){
                    filterCharacter = characters
                }else{
                    val resultCharacter: ArrayList<CharacterRM> = arrayListOf()
                    characters.filter { it.name.toLowerCase().contains(busqueda.toLowerCase()) }
                        .forEach { resultCharacter.add(it) }
                    filterCharacter = resultCharacter
                }
                return FilterResults().apply { values = filterCharacter }
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterCharacter = results!!.values as ArrayList<CharacterRM>
                notifyDataSetChanged()
            }
        }
    }

    fun setData(character: Characters) {
        characters.clear()
        characters.addAll(character.results)
        notifyDataSetChanged()
    }

    //Funcion encargada de la creacion del ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.characters, parent, false)
        return BaseViewHolder(view)
    }

    //Funcion encargada de contar cuantos items son
    override fun getItemCount(): Int {
        return characters.size
    }


    //Funcion encargada de montar cada elemento del recycler
    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder?.let {
            it.bindView(characters[position])
        }
    }

    inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //Funcion encargada de emparejar los elementos del layaout con los datos de la Api
        fun bindView(character: CharacterRM) {
            val title = itemView.profile_name
            val desc = itemView.profile_desc
            val image = itemView.imageView

            title.text = character.name
            desc.text = character.species
            Glide.with(image.context)
                .load(character.image)
                .into(image)


        }

        init {
            //TODO esto debería llamarse desde el activity, mediante un handler o algo similar.
            //Evento de click en un item del recyclerView
            itemView.setOnClickListener {
                val pos = characters[adapterPosition]
                val intent: Intent = Intent(itemView.context, InfoCharacter::class.java)
                intent.putExtra("persons", pos.id)
                context.startActivity(intent)
            }
        }
    }
}

