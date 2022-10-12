package com.colosoft.recomiendame.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.colosoft.recomiendame.R
import com.colosoft.recomiendame.databinding.FragmentDetailsBinding
import com.colosoft.recomiendame.server.model.Opinion
import com.squareup.picasso.Picasso


class DetailsFragment : Fragment() {

    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var detailsBinding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        detailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        detailsViewModel = ViewModelProvider(this)[DetailsViewModel::class.java]

        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mensaje = args.mensaje


        with(detailsBinding){
            nameTextView.text = mensaje.user_name
            lastNameTextView.text = mensaje.user_last_name
            codeMessage.text = mensaje.mensaje_cifrado
            messageTitleTextView.visibility = TextView.INVISIBLE
            message.visibility = TextView.INVISIBLE


            decodeButton.setOnClickListener {
                if (keyInputEditText.text.toString() != null && keyInputEditText.text.toString() != ""){
                    val alphabetListLowerCase = listOf("a", "b", "c","d","e","f","g","h","i","j","k","l","m","n","ñ","o","p","q","r","s","t","u"
                    ,"v","w","x","y","z")
                    val alphabetListCapitalLetter = listOf("A", "B", "C","D","E","F","G","H","I","J","K","L","M","N","Ñ","O","P","Q","R","S","T","U"
                        ,"V","W","X","Y","Z")
                    println("Tamaño alfabeto: " + alphabetListLowerCase.size)

                    var finalMessage = ""
                    /*
                    //PARA CIFRAR
                    for (c in mensaje.mensaje_cifrado.toString()){
                        var isCapitalLetter = false
                        var indexAlphabet = alphabetListLowerCase.indexOf(c.toString())
                        if (indexAlphabet == null){
                            indexAlphabet = alphabetListCapitalLetter.indexOf(c.toString())
                            isCapitalLetter = true
                        }
                        val newCharIndex = indexAlphabet + (keyInputEditText.text.toString().toInt()%27)
                        if (isCapitalLetter){
                            finalMessage += alphabetListCapitalLetter[newCharIndex]
                        }else{
                            finalMessage += alphabetListLowerCase[newCharIndex]
                        }
                    }*/
                    //PARA DESCIFRAR
                    for (c in mensaje.mensaje_cifrado.toString()){
                        var isCapitalLetter = false
                        var indexAlphabet = alphabetListLowerCase.indexOf(c.toString())
                        if (indexAlphabet == null || indexAlphabet == -1){
                            indexAlphabet = alphabetListCapitalLetter.indexOf(c.toString())
                            isCapitalLetter = true
                        }
                        println("Caracter: $c")
                        var newCharIndex = indexAlphabet - (keyInputEditText.text.toString().toInt()%27)
                        if (newCharIndex < 0){
                            newCharIndex += 27
                        }
                        println("Índice caracter: $indexAlphabet")
                        println("Índice del nuevo caracter: $newCharIndex")
                        if (isCapitalLetter){
                            finalMessage += alphabetListCapitalLetter[newCharIndex]
                        }else{
                            finalMessage += alphabetListLowerCase[newCharIndex]
                        }
                        println("Mensaje: $finalMessage")
                    }
                    messageTitleTextView.visibility = TextView.VISIBLE
                    message.visibility = TextView.VISIBLE
                    message.text = finalMessage
                }
                else{
                    messageTitleTextView.visibility = TextView.INVISIBLE
                    message.visibility = TextView.INVISIBLE
                    Toast.makeText(requireActivity(),R.string.key_error, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}