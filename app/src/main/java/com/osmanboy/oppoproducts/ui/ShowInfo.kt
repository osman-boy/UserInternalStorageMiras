package com.osmanboy.oppoproducts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.osmanboy.oppoproducts.R
import com.osmanboy.oppoproducts.databinding.FragmentShowInfoBinding
import java.lang.RuntimeException

private const val NAME = "param1"
private const val PRICE = "param2"
private const val DESCRIPTION = "param3"
private const val IMAGE_URL = "param4"

/**
 * A simple [Fragment] subclass.
 * Use the [ShowInfo.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowInfo : Fragment() {
    private var name: String? = null
    private var description: String? = null
    private var price: Int? = null
    private var imageUrl: String? = null
    private var _binding: FragmentShowInfoBinding? = null;
    private val binding: FragmentShowInfoBinding
        get() = _binding?:throw RuntimeException("binding id null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME)
            price = it.getInt(PRICE)
            description = it.getString(DESCRIPTION)
            imageUrl = it.getString(IMAGE_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentShowInfoBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            textProductsName.text = name
            textProductsInfo.text = "Описание:\n$description"
            textProductsPrice.text = "Цена: $price T"
            imageViewSource.setImageURI(imageUrl?.toUri())
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShowInfo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(name: String, price: Int, description: String, imageUrl: String) =
            ShowInfo().apply {
                arguments = Bundle().apply {
                    putString(NAME, name)
                    putInt(PRICE, price)
                    putString(DESCRIPTION, description)
                    putString(IMAGE_URL, imageUrl)
                }
            }
    }
}