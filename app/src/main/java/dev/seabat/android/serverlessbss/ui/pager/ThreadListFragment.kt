package dev.seabat.android.serverlessbss.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.seabat.android.serverlessbbs.databinding.FragmentThreadListBinding

class ThreadListFragment : Fragment() {
    companion object {
        const val ARG_OBJECT = "object"
    }

    private var _binding: FragmentThreadListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ThreadListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(ThreadListViewModel::class.java)

        _binding = FragmentThreadListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textThreadList.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {
            viewModel.updateText(getInt(ARG_OBJECT).toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // see. https://developer.android.com/topic/libraries/view-binding?hl=ja#fragments
    }
}