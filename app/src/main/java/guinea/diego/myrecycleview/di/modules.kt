package guinea.diego.myrecycleview.di

import guinea.diego.myrecycleview.ui.viewmodel.InfoViewModel
import guinea.diego.myrecycleview.ui.viewmodel.MainViewModel
import guinea.diego.myrecycleview.ui.viewmodel.OriginViewModel
//import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { MainViewModel() }
    viewModel { InfoViewModel() }
    viewModel { OriginViewModel() }
}