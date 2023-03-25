package com.example.adv160420097week4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adv160420097week4.R
import com.example.adv160420097week4.viewmodel.ListViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [StudentListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentListFragment : Fragment() {
    private lateinit var viewModel:ListViewModel
    private val studentListAdapter = StudentListAdapter(arrayListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        val recView = view.findViewById<RecyclerView>(R.id.recView)
        recView.layoutManager = LinearLayoutManager(context)
        recView.adapter = studentListAdapter

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.studentsLD.observe(viewLifecycleOwner, Observer{
            studentListAdapter.updateStudentList(it)
        })
        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer{
            val txtError = view?.findViewById<TextView>(R.id.txtError)
            if(it == true){
                txtError?.visibility = View.VISIBLE
            }else{
                txtError?.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            val recView = view?.findViewById<RecyclerView>(R.id.recView)
            val progressLoad = view?.findViewById<ProgressBar>(R.id.progressLoad)
            if(it == true){
                recView?.visibility = View.GONE
                progressLoad?.visibility = View.VISIBLE
            }else{
                recView?.visibility = View.VISIBLE
                progressLoad?.visibility = View.GONE
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_list, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentListFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StudentListFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}