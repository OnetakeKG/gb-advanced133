package com.gb.advanced2.externals.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.advanced2.app.App
import com.gb.advanced2.app.Contract
import com.gb.advanced2.databinding.ActivityMainBinding
import com.gb.advanced2.entities.Articles

class MainActivity : AppCompatActivity(), Contract.View {
    private lateinit var binding: ActivityMainBinding
    private val presenter = App.instance!!.presenter
    private val adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchFab.setOnClickListener {
            SearchDialogFragment().apply {
                onSearchClickListener = { text -> presenter.search(text) }
                show(supportFragmentManager, DIALOG_TAG)
            }
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerview.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        presenter.onAttachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.onDetachView()
    }

    override fun displayArticles(articles: Articles) {
        adapter.setData(articles)
        setProgressVisible(false)
    }

    override fun showLoadingScreen() {
        adapter.clearData()
        setProgressVisible(true)
    }

    override fun showError(error: String) {
        adapter.clearData()
        setProgressVisible(false)
        Toast.makeText(applicationContext, error, Toast.LENGTH_LONG).show()
    }

    private fun setProgressVisible(visible: Boolean) {
        if (visible) {
            binding.progress.visibility = View.VISIBLE
            binding.recyclerview.visibility = View.GONE
        } else {
            binding.progress.visibility = View.GONE
            binding.recyclerview.visibility = View.VISIBLE
        }
    }

    companion object {
        const val DIALOG_TAG = "e52b6ab0-ca3e-4351-bbe1-3ad2c49840b7"
    }
}