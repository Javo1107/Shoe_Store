package jawoheer.example.shoestore.ShoeList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jawoheer.example.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {
    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        Log.i("ShoeListFragment", "init is called")
        populateShoeList()
    }

    private fun populateShoeList() {
        val shoe1 = Shoe("Jordan", 8.0)
        val shoe2 = Shoe("Nike", 9.5)
        val shoe3 = Shoe("Adidas", 7.5)
        val shoe4 = Shoe("Puma", 10.0)
        _shoeList.value = mutableListOf(shoe1, shoe2, shoe3, shoe4)

    }
    fun addShoeToList(shoeName: String, shoeSize: Double){
        _shoeList.value?.add(Shoe(shoeName, shoeSize))
//        Log.i("ShoeListViewModel", "${shoeList.value?.get(3)}")
    }
}