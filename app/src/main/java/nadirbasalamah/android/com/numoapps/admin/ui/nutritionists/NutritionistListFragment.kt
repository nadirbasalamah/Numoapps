package nadirbasalamah.android.com.numoapps.admin.ui.nutritionists


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_nutritionist_list.*

import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.adapter.NutritionistsAdapter
import nadirbasalamah.android.com.numoapps.model.entity.Nutritionist
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel

/**
 * A simple [Fragment] subclass.
 */
class NutritionistListFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var adapter: NutritionistsAdapter
    private lateinit var adminViewModel: AdminViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nutritionist_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_nutritionistlist.setHasFixedSize(true)
        showNutritionistList()
        sv_nutritionistlist.setOnQueryTextListener(this)

        fab_add_nutritionist.setOnClickListener {
            val intent = Intent(context,AddNutritionistActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showNutritionistList() {
        rv_nutritionistlist.layoutManager = LinearLayoutManager(context)
        adapter = NutritionistsAdapter()
        adapter.notifyDataSetChanged()
        rv_nutritionistlist.adapter = adapter

        adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
        adminViewModel.setContext(context)

        showLoading(true)

        adminViewModel.getAllNutritionists()?.observe(this, Observer {nutritionistItems ->
            if(nutritionistItems != null) {
                adapter.setData(nutritionistItems.data)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : NutritionistsAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Nutritionist) {
                val detailNutritionistIntent = Intent(context, NutritionistDetailActivity::class.java)
                detailNutritionistIntent.putExtra(NutritionistDetailActivity.EXTRA_NUTRITIONIST,data)
                startActivity(detailNutritionistIntent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if(state) {
            pb_nutritionistlist.visibility = View.VISIBLE
        } else {
            pb_nutritionistlist.visibility = View.GONE
        }
    }

    override fun onQueryTextSubmit(keyword: String): Boolean {
        return false
    }

    override fun onQueryTextChange(keyword: String): Boolean {
        getNutritionistList(keyword)
        return false
    }

    private fun getNutritionistList(keyword: String) {
        rv_nutritionistlist.layoutManager = LinearLayoutManager(context)
        adapter = NutritionistsAdapter()
        adapter.notifyDataSetChanged()
        rv_nutritionistlist.adapter = adapter

        adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
        showLoading(true)
        adminViewModel.getNutritionistByName(keyword)?.observe(this, Observer {nutritionistItems ->
            if(nutritionistItems != null) {
                adapter.setData(nutritionistItems.data)
                showLoading(false)
            }
        })

        adapter.setOnItemClickCallback(object : NutritionistsAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Nutritionist) {
                Toast.makeText(context,data.toString(),Toast.LENGTH_SHORT).show()
                val detailNutritionistIntent = Intent(context, NutritionistDetailActivity::class.java)
                detailNutritionistIntent.putExtra(NutritionistDetailActivity.EXTRA_NUTRITIONIST,data)
                startActivity(detailNutritionistIntent)
            }
        })
    }
}