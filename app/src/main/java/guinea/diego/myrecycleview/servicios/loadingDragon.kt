package guinea.diego.myrecycleview.servicios

import android.app.Dialog
import android.graphics.Color
import androidx.core.graphics.drawable.toDrawable
import guinea.diego.myrecycleview.MainActivity
import guinea.diego.myrecycleview.R

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