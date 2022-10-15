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

        with(writeOpinionBinding){

            buttonWriteOpinion.setOnClickListener {
                if (keyInputEditText.text.toString().isNotEmpty()) {
                    if (keyInputEditText.text.toString().toInt() > 0){
                        if (opinionInputEditText.text.toString().isNotEmpty()){
                            writeOpinionViewModel.createMessage(opinionInputEditText.text.toString(),keyInputEditText.text.toString().toInt())
                            Toast.makeText(getActivity(),R.string.successOpinion,Toast.LENGTH_SHORT).show();
                            findNavController().navigate(WriteOpinionFragmentDirections.actionNavigationWriteOpinionToNavigationHome())
                        }
                        else{
                            Toast.makeText(getActivity(),R.string.emptyOpinion,Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getActivity(),R.string.positiveKey,Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getActivity(),R.string.emptyStars,Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

}