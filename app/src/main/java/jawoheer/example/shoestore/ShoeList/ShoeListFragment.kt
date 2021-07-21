package jawoheer.example.shoestore.ShoeList

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import jawoheer.example.shoestore.R
import jawoheer.example.shoestore.databinding.FragmentShoeListBinding
import jawoheer.example.shoestore.models.Shoe

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        Log.i("ShoeListFragment", "VMProvider is called")
        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            Log.i("ShoeListFragment", "Observer is called")

            for (shoe in shoeList) {
                addShoeToLinearLay(shoe)
            }
        })
        setHasOptionsMenu(true)
        binding.fab.setOnClickListener { view ->
            view.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        return binding.root
    }

    private fun addShoeToLinearLay(shoe: Shoe) {
        val name_textview = TextView(context)
        Log.i("ShoeListFragment", shoe.name)
        name_textview.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        name_textview.text = shoe.name
        binding.shoeListLl.addView(name_textview)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}