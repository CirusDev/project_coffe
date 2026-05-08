package fr.code.project_coffe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fr.code.project_coffe.domain.BannerModel
import fr.code.project_coffe.domain.CategoryModel
import fr.code.project_coffe.domain.ItemsModel
import fr.code.project_coffe.repository.MainRepository

class MainViewModel : ViewModel() {
    private val repository = MainRepository()

    fun loaderBanner(): LiveData<MutableList<BannerModel>> {
        return repository.loadBanner()
    }

    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }

    fun loadPopular(): LiveData<MutableList<ItemsModel>>{
        return repository.loadPopular()
    }
}