package guinea.diego.myrecycleview.servicios

import android.app.Service
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


class Connectivity (){

    var connectivity: ConnectivityManager? = null
    var infoConnectivity: NetworkInfo? = null

    fun isConnected(context: Context): Boolean{
        connectivity = context.getSystemService(Service.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null){
            infoConnectivity = connectivity?.activeNetworkInfo
            if (infoConnectivity !=null){
                if (infoConnectivity?.state == NetworkInfo.State.CONNECTED){
                    return true
                }
            }
        }
        return false
    }
}
