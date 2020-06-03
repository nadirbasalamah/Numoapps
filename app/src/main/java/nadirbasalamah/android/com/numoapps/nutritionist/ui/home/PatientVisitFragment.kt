package nadirbasalamah.android.com.numoapps.nutritionist.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_patient_visit2.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.adapter.PatientsAdapter
import nadirbasalamah.android.com.numoapps.model.entity.Patient
import nadirbasalamah.android.com.numoapps.nutritionist.ui.nutrition_records.PatientDetailActivity
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class PatientVisitFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var adapter: PatientsAdapter
    private lateinit var adminViewModel: AdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_patient_visit2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_nut_patientvisitlist.setHasFixedSize(true)
        sv_nut_patientvisitlist.setOnQueryTextListener(this)
    }

    override fun onResume() {
        super.onResume()
        showPatientList()
    }

    private fun showPatientList() {
        rv_nut_patientvisitlist.layoutManager = LinearLayoutManager(context)
        adapter = PatientsAdapter()
        adapter.notifyDataSetChanged()
        rv_nut_patientvisitlist.adapter = adapter

        adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
        adminViewModel.setContext(context)

        showLoading(true)

        val calendar: Calendar = Calendar.getInstance()
        val visitdate = calendar.get(Calendar.DAY_OF_MONTH).toString() + "-" + calendar.get(Calendar.MONTH).toString() + "-" + calendar.get(
            Calendar.YEAR)

        adminViewModel.getPatientByVisitDate(visitdate)?.observe(viewLifecycleOwner, Observer {patientItems ->
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
            pb_nut_patientvisitlist.visibility = View.VISIBLE
        } else {
            pb_nut_patientvisitlist.visibility = View.GONE
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
        rv_nut_patientvisitlist.layoutManager = LinearLayoutManager(context)
        adapter = PatientsAdapter()
        adapter.notifyDataSetChanged()
        rv_nut_patientvisitlist.adapter = adapter

        adminViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(AdminViewModel::class.java)
        showLoading(true)
        adminViewModel.getPatientByName(keyword)?.observe(this, Observer { patientItems ->
            if (patientItems != null) {
                adapter.setData(patientItems.data)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : PatientsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Patient) {
                val detailPatientIntent = Intent(context, PatientDetailActivity::class.java)
                detailPatientIntent.putExtra(PatientDetailActivity.EXTRA_NUT_PATIENT, data)
                startActivity(detailPatientIntent)
            }
        })
    }
}
