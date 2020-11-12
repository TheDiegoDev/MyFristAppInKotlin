package guinea.diego.myrecycleview.modelo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

    @Parcelize
    data class Data(
        var name: String,
        val url: String
    ) : Parcelable
