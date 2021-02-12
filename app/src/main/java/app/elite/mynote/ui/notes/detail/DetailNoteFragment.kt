package app.elite.mynote.ui.notes.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.elite.mynote.R

class DetailNoteFragment : Fragment() {

    companion object {
        fun newInstance() = DetailNoteFragment()
    }

    private lateinit var viewModel: DetailNoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_note_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailNoteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}