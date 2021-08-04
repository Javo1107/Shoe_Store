package jawoheer.example.shoestore.shoeList

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import jawoheer.example.shoestore.R
import jawoheer.example.shoestore.databinding.FragmentShoeListBinding
import jawoheer.example.shoestore.models.Shoe

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        viewModel.shoeList.observe(viewLifecycleOwner, object : Observer<MutableList<Shoe>> {

            override fun onChanged(shoeList: MutableList<Shoe>?) {
                shoeList ?: return
                val container = binding.shoeListLl
                val inflater = LayoutInflater.from(context)

                val children = shoeList.map { shoe ->
                    val shoeItemView =
                        inflater.inflate(R.layout.list_item, container, false) as ConstraintLayout
                    (shoeItemView.findViewById(R.id.txt_shoeName) as TextView).text = shoe.name
                    (shoeItemView.findViewById(R.id.txt_company) as TextView).text = shoe.company
                    (shoeItemView.findViewById(R.id.txt_shoeSize) as TextView).text =
                        shoe.size.toString()
                    (shoeItemView.findViewById(R.id.txt_desc) as TextView).text = shoe.description
                    if (shoe.image != null) {
                        val shoeImage = (shoeItemView.findViewById<ImageView>(R.id.img_shoe))
                        val imgUri = (shoe.image).toUri().buildUpon().scheme("https").build()
                        Glide.with(container.context).load(imgUri)
                            .placeholder(R.drawable.no_image_icon).into(shoeImage)
                    }

                    shoeItemView
                }

                for (shoe in children) {
                    binding.shoeListLl.addView(shoe)
                }

            }
        })

        setHasOptionsMenu(true)

        binding.fab.setOnClickListener { view ->
            view.findNavController()
                .navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
        }
        //set invisible of up button
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        return binding.root
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