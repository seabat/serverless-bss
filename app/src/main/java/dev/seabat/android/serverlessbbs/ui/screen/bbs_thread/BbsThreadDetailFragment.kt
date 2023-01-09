package dev.seabat.android.serverlessbbs.ui.screen.bbs_thread

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dev.seabat.android.serverlessbbs.databinding.FragmentBbsThreadDetailBinding

class BbsThreadDetailFragment : Fragment() {

    companion object {
        const val ARG_BBS_THREAD_ID ="arg_bbsThreadId"
    }

    private var _binding: FragmentBbsThreadDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bbsThreadDetailViewModel =
            ViewModelProvider(this).get(BbsThreadDetailViewModel::class.java)

        _binding = FragmentBbsThreadDetailBinding.inflate(inflater, container, false)
        binding.let {
            it.textBbsThreadDetail.text = arguments?.getString(ARG_BBS_THREAD_ID, "1")?: "1"
        }
        val root: View = binding.root

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
