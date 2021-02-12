package app.elite.mynote.ui.notes.get

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.findNavController
import app.elite.mynote.databinding.ListNotesFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ListNotesFragment : Fragment() {

    private val viewModel: ListNotesViewModel by viewModels()
    private lateinit var binding: ListNotesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListNotesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.addNoteAction.setOnClickListener {
            val direction =
                ListNotesFragmentDirections.actionListNotesFragmentToCreateNoteFragment()
            it.findNavController().navigate(direction)
        }

        val adapter = NoteAdapter()
        binding.recyclerView.apply {
            this.adapter = adapter
        }

        viewModel.data.asLiveData().observe(viewLifecycleOwner) {
            Timber.e("Size = $it")
            if (it.isSuccess) {
                adapter.submitList(it.getOrNull())
                binding.refreshAction.isRefreshing = false
            } else {
                Snackbar.make(binding.root, "No Data", Snackbar.LENGTH_LONG).show()
            }
        }


        binding.refreshAction.setOnRefreshListener {
            viewModel.syncData()
        }
    }
}