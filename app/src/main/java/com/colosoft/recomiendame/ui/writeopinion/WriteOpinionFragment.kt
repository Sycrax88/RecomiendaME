package com.colosoft.recomiendame.ui.writeopinion

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.colosoft.recomiendame.R
import com.colosoft.recomiendame.databinding.FragmentDetailsBinding
import com.colosoft.recomiendame.databinding.FragmentWriteOpinionBinding
import com.colosoft.recomiendame.server.model.Opinion
import com.colosoft.recomiendame.ui.details.DetailsFragmentArgs
import com.colosoft.recomiendame.ui.details.DetailsFragmentDirections
import com.colosoft.recomiendame.ui.details.DetailsViewModel

class WriteOpinionFragment : Fragment() {

    private lateinit var  writeOpinionViewModel: WriteOpinionViewModel
    private lateinit var writeOpinionBinding: FragmentWriteOpinionBinding
    private val args: WriteOpinionFragmentArgs by navArgs()
    private lateinit var opinion: Opinion

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        writeOpinionBinding = FragmentWriteOpinionBinding.inflate(inflater, container, false)
        writeOpinionViewModel = ViewModelProvider(this)[WriteOpinionViewModel::class.java]

        return writeOpinionBinding.root
    }

    private fun showErrorMessage(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurant = args.restaurant

        var restaurantRateByUser = 0.0

        with(writeOpinionBinding){
            star1ImageViewOpinion.setOnClickListener {
                star1ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star2ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_empty))
                star3ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_empty))
                star4ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_empty))
                star5ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_empty))
                restaurantRateByUser = 1.0
            }
            star2ImageViewOpinion.setOnClickListener {
                star1ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star2ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star3ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_empty))
                star4ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_empty))
                star5ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_empty))
                restaurantRateByUser = 2.0

            }
            star3ImageViewOpinion.setOnClickListener {
                star1ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star2ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star3ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star4ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_empty))
                star5ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_empty))
                restaurantRateByUser = 3.0
            }
            star4ImageViewOpinion.setOnClickListener {
                star1ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star2ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star3ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star4ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star5ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate_empty))
                restaurantRateByUser = 4.0

            }
            star5ImageViewOpinion.setOnClickListener {
                star1ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star2ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star3ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star4ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                star5ImageViewOpinion.setImageDrawable(resources.getDrawable(R.drawable.ic_star_rate))
                restaurantRateByUser = 5.0
            }




            buttonWriteOpinion.setOnClickListener {
                if (restaurantRateByUser > 0.0){
                    if (opinionInputEditText.text.toString().isNotEmpty()){
                        writeOpinionViewModel.createOpinion(restaurant,restaurantRateByUser,opinionInputEditText.text.toString())
                        Toast.makeText(getActivity(),R.string.successOpinion,Toast.LENGTH_SHORT).show();
                        findNavController().navigate(WriteOpinionFragmentDirections.actionNavigationWriteOpinionToNavigationRestaurants())

                    }
                    else{
                        Toast.makeText(getActivity(),R.string.emptyOpinion,Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(getActivity(),R.string.emptyStars,Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

}