package jawoheer.example.shoestore.shoeList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import jawoheer.example.shoestore.R
import jawoheer.example.shoestore.databinding.FragmentShoeDetailBinding
import jawoheer.example.shoestore.models.Shoe


class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoeListViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.viewModel = viewModel

        binding.shoeData = Shoe("", 0.0,"","","")

        viewModel.navigateToShoeList.observe(viewLifecycleOwner, Observer {
            if (it == true && areAllFieldsChecked()) {
                this.findNavController()
                    .navigate(R.id.action_shoeDetailFragment_to_shoeListFragment)
                viewModel.onCompleteNavToShoeList()
            }
        })

        binding.cancelButton.setOnClickListener {
            this.findNavController().navigateUp()
        }

        return binding.root
    }

    private fun areAllFieldsChecked(): Boolean {
        if (binding.edtShoeName.length() == 0) {
            binding.edtShoeName.setError("Required field")
            return false
        }
        if (binding.edtCompany.length() == 0) {
            binding.edtCompany.setError("Required field")
            return false
        }
        if (binding.edtShoeSize.length() == 0) {
            binding.edtShoeSize.setError("Required field")
            return false
        }
        if (binding.edtDescription.length() == 0) {
            binding.edtDescription.setError("Required field")
            return false
        }

        return true
    }

}