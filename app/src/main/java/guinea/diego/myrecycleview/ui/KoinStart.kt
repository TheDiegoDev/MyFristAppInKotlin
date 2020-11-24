package guinea.diego.myrecycleview.ui

import android.app.Application
import guinea.diego.myrecycleview.ui.viewmodel.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(viewmodelModule)
        }
    }
}