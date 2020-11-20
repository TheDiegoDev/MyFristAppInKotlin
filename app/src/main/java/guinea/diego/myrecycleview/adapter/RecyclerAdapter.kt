package guinea.diego.myrecycleview.adapter

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
import kotlinx.android.synthetic.main.characters.view.*
import kotlin.collections.ArrayList


class RecyclerAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerAdapter.BaseViewHolder>(), Filterable {
    private var characters: ArrayList<CharacterRM> = arrayListOf()
    private var filterCharacter: ArrayList<CharacterRM> = arrayListOf()

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if(constraint == null || constraint.length < 0){
                    filterResults.count = filterCharacter.size
                    filterResults.values = filterCharacter
                }else{
                    var searchString = constraint.toString().toLowerCase()
                    val itemModal = ArrayList<CharacterRM>()
                    for(item in filterCharacter){
                        if(item.name!!.toLowerCase().contains(searchString) || item.species!!.toLowerCase().contains(searchString)){
                            itemModal.add(item)
                        }
                    }
                    filterResults.count = itemModal.size
                    filterResults.values = itemModal
                }
                return filterResults
            }
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                characters = results!!.values as ArrayList<CharacterRM>
                notifyDataSetChanged()
            }
        }
    }

    fun setData(character: ArrayList<CharacterRM>) {
        characters.clear()
        characters.addAll(character)
        filterCharacter = characters
        notifyDataSetChanged()
    }
    fun addData(character: ArrayList<CharacterRM>){
        characters.clear()
        characters.addAll(character)
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

