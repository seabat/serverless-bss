package dev.seabat.android.serverlessbbs.ui.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dev.seabat.android.serverlessbbs.R
import dev.seabat.android.serverlessbbs.data.BbsThread
import dev.seabat.android.serverlessbbs.databinding.FragmentBbsThreadListBinding
import dev.seabat.android.serverlessbbs.ui.screen.bbs_thread.BbsThreadDetailFragment.Companion.ARG_BBS_THREAD_ID
import dev.seabat.android.serverlessbbs.ui.list.BbsThreadListAdapter


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

        // BBSスレッドリストの初期化
        binding.listThread.also {
            it.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            it.adapter = BbsThreadListAdapter {
                    bbsThread -> onAdapterClick(bbsThread)
            }
        }

        // LiveData の observe をセット
        viewModel.also {
            it.text.observe(viewLifecycleOwner) { text ->
                binding.textBbsThreadList.text = text
            }
            it.bbsThreadList.observe(viewLifecycleOwner) { list ->
                (binding.listThread.adapter as BbsThreadListAdapter).submitList(list as MutableList<BbsThread>)
            }
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

    private fun onAdapterClick(bbsThread: BbsThread) {
        val bundle = Bundle().also {
            it.putString(ARG_BBS_THREAD_ID, bbsThread.text_title)
        }
        this.findNavController().navigate(R.id.action_navHome_to_bbsThreadDetail, bundle)
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