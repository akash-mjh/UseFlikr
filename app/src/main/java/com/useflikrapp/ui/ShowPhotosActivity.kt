package com.useflikrapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.useflikrapp.R
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class ShowPhotosActivity : AppCompatActivity() {

    private val photosViewModel: ShowPhotosViewModel by viewModels()
    private val showPhotoAdapter = ShowPhotosAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.adapter = showPhotoAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        photosViewModel.photosLiveData.observe(this,
            Observer { list ->
                with(showPhotoAdapter) {
                    photosList.clear()
                    photosList.addAll(list)
                    notifyDataSetChanged()
                }
            })


        searchET.addTextChangedListener { editable ->
            photosViewModel.fetchImages(editable.toString())
        }

    }
}