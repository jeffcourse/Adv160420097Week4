package com.example.adv160420097week4.view

import android.icu.util.TimeUnit
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adv160420097week4.R
import com.example.adv160420097week4.databinding.FragmentStudentDetailBinding
import com.example.adv160420097week4.model.Student
import com.example.adv160420097week4.util.loadImage
import com.example.adv160420097week4.viewmodel.DetailViewModel
import com.example.adv160420097week4.viewmodel.ListViewModel
import com.google.android.material.textfield.TextInputEditText
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment(), ButtonNotifClickListener {
    private lateinit var detailModel: DetailViewModel
    private lateinit var dataBinding: FragmentStudentDetailBinding
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
        /*val txtID: TextInputEditText = requireView().findViewById(R.id.txtID)
        val txtName: TextInputEditText = requireView().findViewById(R.id.txtName)
        val txtBod: TextInputEditText = requireView().findViewById(R.id.txtBod)
        val txtPhone: TextInputEditText = requireView().findViewById(R.id.txtPhone)
        val imageView: ImageView = requireView().findViewById(R.id.imageView2)
        val progresBar: ProgressBar = requireView().findViewById(R.id.progressBar2)
        val btnNotif: Button = requireView().findViewById(R.id.btnNotif)*/

        detailModel.studentLD.observe(viewLifecycleOwner, Observer{
            dataBinding.student = it
            dataBinding.listener = this
            /*txtID.setText(it.id)
            txtName.setText(it.name)
            txtBod.setText(it.dob)
            txtPhone.setText(it.phone)
            imageView.loadImage(it.photoUrl, progresBar)

            var student = it
            btnNotif.setOnClickListener {
                Observable.timer(0, java.util.concurrent.TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        Log.d("Messages", "five seconds")
                        MainActivity.showNotif(student.name.toString(),
                            "A new notification created",
                            R.drawable.circle)
                    }
            }*/
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate<FragmentStudentDetailBinding>(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onButtonNotifClick(v: View, obj:Student) {
        Observable.timer(0, java.util.concurrent.TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
                MainActivity.showNotif(
                    obj.name.toString(),
                    "A new notification created",
                    R.drawable.circle
                )
            }
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