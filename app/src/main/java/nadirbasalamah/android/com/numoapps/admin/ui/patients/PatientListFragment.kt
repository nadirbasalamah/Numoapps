package nadirbasalamah.android.com.numoapps.admin.ui.patients


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_patient_list.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.adapter.PatientsAdapter
import nadirbasalamah.android.com.numoapps.model.entity.Patient
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel

/**
 * A simple [Fragment] subclass.
 */
class PatientListFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var adapter: PatientsAdapter
    private lateinit var adminViewModel: AdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_patientlist.setHasFixedSize(true)

        sv_patientlist.setOnQueryTextListener(this)

        fab_add_patient.setOnClickListener{
            val intent = Intent(context,AddPatientActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        showPatientList()
    }

    private fun showPatientList() {
        rv_patientlist.layoutManager = LinearLayoutManager(context)
        adapter = PatientsAdapter()
        adapter.notifyDataSetChanged()
        rv_patientlist.adapter = adapter

        adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
        adminViewModel.setContext(context)

        showLoading(true)

        adminViewModel.getAllPatients()?.observe(viewLifecycleOwner, Observer {patientItems ->
            if(patientItems != null) {
                adapter.setData(patientItems.data)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : PatientsAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Patient) {
                val detailPatientIntent = Intent(context, PatientDetailActivity::class.java)
                detailPatientIntent.putExtra(PatientDetailActivity.EXTRA_PATIENT,data)
                startActivity(detailPatientIntent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            pb_patientlist.visibility = View.VISIBLE
        } else {
            pb_patientlist.visibility = View.GONE
        }
    }

    override fun onQueryTextSubmit(keyword: String): Boolean {
        return false
    }

    override fun onQueryTextChange(keyword: String): Boolean {
        getPatientList(keyword)
        return false
    }

    private fun getPatientList(keyword: String) {
        rv_patientlist.layoutManager = LinearLayoutManager(context)
        adapter = PatientsAdapter()
        adapter.notifyDataSetChanged()
        rv_patientlist.adapter = adapter

        adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
        showLoading(true)
        adminViewModel.getPatientByName(keyword)?.observe(this, Observer {patientItems ->
            if(patientItems != null) {
                adapter.setData(patientItems.data)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : PatientsAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Patient) {
                val detailPatientIntent = Intent(context, PatientDetailActivity::class.java)
                detailPatientIntent.putExtra(PatientDetailActivity.EXTRA_PATIENT,data)
                startActivity(detailPatientIntent)
            }
        })

    }
}