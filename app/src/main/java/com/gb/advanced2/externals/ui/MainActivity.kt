package com.gb.advanced1337.externals.ui



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gb.advanced1337.app.Contract
import com.gb.advanced1337.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = Adapter()
    private val viewModel by inject<Contract.ViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchFab.setOnClickListener {
            SearchDialogFragment().apply {
                onSearchClickListener = { text -> viewModel.search(text) }
                show(supportFragmentManager, DIALOG_TAG)
            }
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.recyclerview.adapter = adapter

        viewModel.getState().observe(this) { renderState(it) }
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

    private fun renderState(state: Contract.AppState) {
        when (state) {
            is Contract.AppState.Loading -> {
                setProgressVisible(true)
            }
            is Contract.AppState.Empty -> {
                setProgressVisible(false)
                adapter.submitList(null)
            }
            is Contract.AppState.DataLoaded -> {
                setProgressVisible(false)
                adapter.submitList(state.data)
            }
            is Contract.AppState.Error -> {
                setProgressVisible(false)
                adapter.submitList(null)
                Toast.makeText(applicationContext, state.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object {
        const val DIALOG_TAG = "e52b6ab0-ca3e-4351-bbe1-3ad2c49840b7"
    }
}