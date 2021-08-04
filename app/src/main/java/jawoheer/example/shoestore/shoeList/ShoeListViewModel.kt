package jawoheer.example.shoestore.shoeList


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jawoheer.example.shoestore.models.Shoe
import kotlin.reflect.typeOf

class ShoeListViewModel : ViewModel() {

    //List for a shoe object
    private var _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList


    private val _navigateToShoeList = MutableLiveData<Boolean>()
    val navigateToShoeList: LiveData<Boolean>
        get() = _navigateToShoeList


    init {
        populateShoeList()
        _navigateToShoeList.value = false
    }

    private fun populateShoeList() {
        val shoe1 = Shoe(
            "Dixon",
            8.0,
            "Hush Puppies",
            "Classic meets contemporary! This slip on sneaker is perfect for those on the go adventures. We've got you covered with a breathable leather lining that provides climate and odour control.- Bounce™ for shock absorption and energy return.- WorryFree upper",
            "https://cdn11.bigcommerce.com/s-z9x4z1a17g/images/stencil/680x680/products/5202/146658/SWH-01__81693.1621291546.jpg?c=2"
        )
        val shoe2 = Shoe(
            "Lqdcell Method",
            9.5,
            "Puma",
            "Puma LQDCell Method is the ultimate menâ??s shoe for crossfit, gym and agility training.",
            "https://cdn11.bigcommerce.com/s-z9x4z1a17g/images/stencil/680x680/products/5143/145486/SWH-01__00812.1619569386.jpg?c=2"
        )
        val shoe3 = Shoe(
            "Fluidflow 2.0 Men",
            7.5,
            "Adidas Performance",
            "It doesn't really matter whether or not a run is in the cards for the day. An ultra-breathable feel and cushy midsole that energises give these adidas running shoes an edge.",
            "https://cdn11.bigcommerce.com/s-z9x4z1a17g/images/stencil/680x680/products/5007/140809/SWH-01__87810.1617074230.jpg?c=2"
        )
        val shoe4 = Shoe(
            "Tri Sky",
            10.0,
            "Clarks",
            "With classic sports appeal, these casual shoes offer maximum comfort for all day wear. Try them on and feel the difference. Our Trigenic system gives ultralight, gait-reactive flex and engineered deconstruction, a foot-shaped fit and responsive.",
            "https://cdn11.bigcommerce.com/s-z9x4z1a17g/images/stencil/680x680/products/5042/143061/SWH-01__31692.1617326253.jpg?c=2"
        )
        _shoeList.value = mutableListOf(shoe1, shoe2, shoe3, shoe4)
    }

    fun navigateToShoeList(shoe: Shoe) {
        _navigateToShoeList.value = true
        _shoeList.value?.add(0, shoe)
    }

    fun onCompleteNavToShoeList() {
        _navigateToShoeList.value = false
    }
}