package jawoheer.example.shoestore.ShoeList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import jawoheer.example.shoestore.R
import jawoheer.example.shoestore.databinding.FragmentShoeDetailBinding
import jawoheer.example.shoestore.models.Shoe


class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)

        val shoeName= binding.edtShoeName.text
        val shoeSize = binding.edtShoeSize.text

        binding.saveButton.setOnClickListener { view ->
//          Toast.makeText(context, "${shoeName}, ${shoeSize}", Toast.LENGTH_LONG).show()
            viewModel.addShoeToList(shoeName.toString(), shoeSize.toString().toDouble())
            findNavController().navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

}