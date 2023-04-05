package com.example.adv160420097week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adv160420097week4.R
import com.example.adv160420097week4.model.Student
import com.example.adv160420097week4.util.loadImage
import com.example.adv160420097week4.viewmodel.DetailViewModel
import com.example.adv160420097week4.viewmodel.ListViewModel
import com.google.android.material.textfield.TextInputEditText

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment() {
    private lateinit var detailModel: DetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val id = StudentDetailFragmentArgs.fromBundle(requireArguments()).id
            detailModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            detailModel.fetch(id)

            observeDetailModel()
        }
    }

    fun observeDetailModel(){
        val txtID: TextInputEditText = requireView().findViewById(R.id.txtID)
        val txtName: TextInputEditText = requireView().findViewById(R.id.txtName)
        val txtBod: TextInputEditText = requireView().findViewById(R.id.txtBod)
        val txtPhone: TextInputEditText = requireView().findViewById(R.id.txtPhone)
        val imageView: ImageView = requireView().findViewById(R.id.imageView2)
        val progresBar: ProgressBar = requireView().findViewById(R.id.progressBar2)

        detailModel.studentLD.observe(viewLifecycleOwner, Observer{
            txtID.setText(it.id)
            txtName.setText(it.name)
            txtBod.setText(it.dob)
            txtPhone.setText(it.phone)
            imageView.loadImage(it.photoUrl, progresBar)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentDetailFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentDetailFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}