package com.osmanboy.oppoproducts.ui.dashboard

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.osmanboy.oppoproducts.data.Oppo
import com.osmanboy.oppoproducts.data.OPPOREPOSITORY
import com.osmanboy.oppoproducts.databinding.FragmentDashboardBinding
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var oppo: Oppo
    private val opporepository by lazy {
        OPPOREPOSITORY(application = requireActivity().application)
    }

    private val getContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { _uri: Uri? ->

            _uri?.let {
                binding.imageViewSource.setImageURI(_uri)
                val uri: Uri = saveImageToInternalStorage(requireContext())
                setUpListener(uri)

            }


        }
    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewSource.setOnClickListener {
            getContent.launch("image/*")

        }

    }

    private fun setUpListener(uri: Uri) {

        binding.button.setOnClickListener {
            with(binding) {
                oppo = Oppo(
                    name = textProductsName.text.toString(),
                    price = textProductsPrice.text.toString().toInt(),
                    description = textProductsInfo.text.toString(),
                    imageUri = uri.toString()
                )

                lifecycleScope.launch {
                    opporepository.addShopItem(oppo)
                }

            }


        }
    }


    // Method to save an image to internal storage
    private fun saveImageToInternalStorage(context: Context): Uri {


        val bitmap: Bitmap = (binding.imageViewSource.drawable as BitmapDrawable).bitmap

        // Get the context wrapper instance
        val wrapper = ContextWrapper(context)

        // Initializing a new file
        // The bellow line return a directory in internal storage
        var file = wrapper.getDir("images", Context.MODE_PRIVATE)


        // Create a file to save the image
        file = File(file, "${UUID.randomUUID()}.jpg")

        try {
            // Get the file output stream
            val stream: OutputStream = FileOutputStream(file)

            // Compress bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)

            // Flush the stream
            stream.flush()

            // Close stream
            stream.close()
        } catch (e: IOException) { // Catch the exception
            e.printStackTrace()
        }

        // Return the saved image uri
        return Uri.parse(file.absolutePath)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}