package guinea.diego.myrecycleview.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.text.ParsePosition


abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView){
    abstract fun bind(item: T,position: Int)
}