package guinea.diego.myrecycleview.servicios

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable
import guinea.diego.myrecycleview.MainActivity
import guinea.diego.myrecycleview.R
import guinea.diego.myrecycleview.local.DB_Helper
import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.modelo.Characters

object loadingDragon {
    fun showLoadingDialog(context: MainActivity): Dialog{
        val progressDialog = Dialog(context)
        progressDialog.let {
            it.show()
            it.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
            it.setContentView(R.layout.progress_dialog)
            it.setCancelable(false)
            it.setCanceledOnTouchOutside(false)

            return it
        }
    }

}

//object getDataBase{
//    lateinit var handler: DB_Helper
//
//    fun getData():ArrayList<CharacterRM> {
//        handler = DB_Helper(Application().applicationContext)
//        return handler.readData()
//    }
//    fun saveData(data: Characters){
//        handler = DB_Helper(Application().applicationContext)
//        return handler.importData(data)
//    }
//}