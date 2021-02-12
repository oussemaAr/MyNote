package app.elite.mynote.ui.notes.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import app.elite.mynote.data.local.entity.Note
import app.elite.mynote.databinding.CreateNoteFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateNoteFragment : Fragment() {

    private val viewModel: CreateNoteViewModel by viewModels()
    private lateinit var binding: CreateNoteFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateNoteFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.saveAction.setOnClickListener {
            val note =
                Note(
                    id = 0,
                    description = binding.description.text.toString(),
                    title = binding.title.text.toString()
                )
            viewModel.addNote(note = note)
            it.findNavController().popBackStack()
        }
    }
}