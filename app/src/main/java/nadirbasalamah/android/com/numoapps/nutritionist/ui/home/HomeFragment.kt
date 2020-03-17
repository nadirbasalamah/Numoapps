package nadirbasalamah.android.com.numoapps.nutritionist.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_nut_home.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.adapter.PatientsAdapter
import nadirbasalamah.android.com.numoapps.model.entity.Patient
import nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records.PatientDetailActivity
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel

class HomeFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var adapter: PatientsAdapter
    private lateinit var adminViewModel: AdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_nut_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_nut_patientlist.setHasFixedSize(true)
        showPatientList()
        sv_nut_patientlist.setOnQueryTextListener(this)
    }

    private fun showPatientList() {
        rv_nut_patientlist.layoutManager = LinearLayoutManager(context)
        adapter = PatientsAdapter()
        adapter.notifyDataSetChanged()
        rv_nut_patientlist.adapter = adapter

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
                detailPatientIntent.putExtra(PatientDetailActivity.EXTRA_NUT_PATIENT,data)
                startActivity(detailPatientIntent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            pb_nut_patientlist.visibility = View.VISIBLE
        } else {
            pb_nut_patientlist.visibility = View.GONE
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
        rv_nut_patientlist.layoutManager = LinearLayoutManager(context)
        adapter = PatientsAdapter()
        adapter.notifyDataSetChanged()
        rv_nut_patientlist.adapter = adapter

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
                detailPatientIntent.putExtra(PatientDetailActivity.EXTRA_NUT_PATIENT,data)
                startActivity(detailPatientIntent)
            }
        })
    }
}