package hu.gergo.kovacs.jetpackdemo.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import hu.gergo.kovacs.jetpackdemo.R
import hu.gergo.kovacs.jetpackdemo.Repository
import hu.gergo.kovacs.jetpackdemo.adapter.ItemAdapter
import hu.gergo.kovacs.jetpackdemo.databinding.ActivityMainBinding
import hu.gergo.kovacs.jetpackdemo.model.Item
import hu.gergo.kovacs.jetpackdemo.util.ApplicationHelper
import hu.gergo.kovacs.jetpackdemo.viewmodel.ItemViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity(), LifecycleOwner {
    lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val itemAdapter = ItemAdapter(this)
        val recyclerView = layoutBinding.mainRecyclerView
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        itemViewModel.items.observe(this, Observer<List<Item>>(itemAdapter::setItems))
    }

    @BindingAdapter("imageResource")
    fun setImageResource(imageView: ImageView, imageId: Int) {
        imageView.setImageResource(imageId)
    }

    fun refreshActivity(view: View){
        recreate()
    }

    fun deleteItemFromDB(view: View){
        itemViewModel.deleteOneItem()
    }

    fun addItemToDB(view: View){
        itemViewModel.addOneRandomItem()
    }

    fun resetDB(view: View){
        itemViewModel.resetDataset()
    }
}

