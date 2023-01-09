package dev.seabat.android.serverlessbbs.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.seabat.android.serverlessbbs.databinding.FragmentBbsThreadListBinding

class BbsThreadListFragment : Fragment() {
    companion object {
        const val ARG_TYPE = "type"
    }

    private var _binding: FragmentBbsThreadListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: BbsThreadListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(BbsThreadListViewModel::class.java)

        _binding = FragmentBbsThreadListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textBbsThreadList.text = it
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            if (it.containsKey(ARG_TYPE)) {
                viewModel.updateText((it.getSerializable(ARG_TYPE) as BbsThreadListType).value)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // see. https://developer.android.com/topic/libraries/view-binding?hl=ja#fragments
    }
}

enum class BbsThreadListType(val value: String) {
    ALL("ALL"), UNREAD("UNREAD"), FOLLOW("FOLLOW");
    companion object {
        fun convertToListType(no: Int): BbsThreadListType {
            return when (no) {
                0 -> ALL
                1 -> UNREAD
                2 -> FOLLOW
                else -> ALL
            }
        }
        val size = BbsThreadListType.values().size
    }
}