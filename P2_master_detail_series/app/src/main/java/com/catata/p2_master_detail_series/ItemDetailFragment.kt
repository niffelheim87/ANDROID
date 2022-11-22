package com.catata.p2_master_detail_series

import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.DragEvent
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.catata.p2_master_detail_series.databinding.FragmentItemDetailBinding
import com.catata.p2_master_detail_series.model.Serie
import com.google.android.material.snackbar.Snackbar
import setFromString
import toDrawableIdentifier

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The placeholder content this fragment is presenting.
     */
    private var item: Serie? = null

    private var moreInfo = false

    lateinit var itemDetailTextView: TextView
    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val dragListener = View.OnDragListener { v, event ->
        if (event.action == DragEvent.ACTION_DROP) {
            val clipDataItem: ClipData.Item = event.clipData.getItemAt(0)
            val dragData = clipDataItem.text
            //item = Serie.getSerieById(dragData.)
            //updateContent()
            Toast.makeText(requireContext(), "Dragged Listener", Toast.LENGTH_SHORT).show()
        }
        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                item = Serie.getSerieById(it.getInt(ARG_ITEM_ID))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        toolbarLayout = binding.toolbarLayout
        itemDetailTextView = binding.tvSerieName

        updateContent()
        rootView.setOnDragListener(dragListener)

        return rootView
    }

    private fun updateContent() {

        binding.detailToolbar?.title = item?.name
        //toolbarLayout?.title = item?.name
        toolbarLayout?.setBackgroundResource(item?.image!!.toDrawableIdentifier(requireContext()))

        // Show the placeholder content as text in a TextView.
        item?.let {
            itemDetailTextView.text = it.summary

            binding.ivPortada?.setFromString(it.image)

            binding.tvUrlSite?.text = getString(R.string.url) + it.officialSite
            binding.tvLanguage?.text = getString(R.string.language) + it.language
            binding.tvPremiered?.text = getString(R.string.premiered) + it.premiered
            binding.tvGenres?.text = getString(R.string.genres) + it.genres?.joinToString(", ")

            binding.tvUrlSite?.setOnClickListener { view->
                val webpage: Uri = Uri.parse(it.officialSite)
                val intent = Intent(Intent.ACTION_VIEW, webpage)
                startActivity(intent)

            }

            binding.fab?.setOnClickListener {
                val text = if(!moreInfo) getString(R.string.show_more_info) else getString(R.string.hide_more_info)

                Snackbar.make(it,text,Snackbar.LENGTH_SHORT).apply {
                    setAction("Accept"){
                        binding.llMoreInfo?.visibility = if(!moreInfo) View.VISIBLE else View.GONE
                        moreInfo=!moreInfo
                    }
                }.show()

            }
        }




    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}