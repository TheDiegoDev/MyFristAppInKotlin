package guinea.diego.myrecycleview.ui.viewmodel

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodelModule = module {
    viewModel { MainViewModel() }
    viewModel { InfoViewModel() }
    viewModel { OriginViewModel() }
}