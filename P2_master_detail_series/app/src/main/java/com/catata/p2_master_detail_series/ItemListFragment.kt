package com.catata.p2_master_detail_series

import android.content.ClipData
import android.content.ClipDescription
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.catata.p2_master_detail_series.databinding.FragmentItemListBinding
import com.catata.p2_master_detail_series.databinding.ItemListContentBinding
import com.catata.p2_master_detail_series.databinding.ItemListContentEndedBinding
import com.catata.p2_master_detail_series.model.Serie
import setFromString

/**
 * A Fragment representing a list of Pings. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link ItemDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */

class ItemListFragment : Fragment() {

    /**
     * Method to intercept global key events in the
     * item list fragment to trigger keyboard shortcuts
     * Currently provides a toast when Ctrl + Z and Ctrl + F
     * are triggered
     */
    private val unhandledKeyEventListenerCompat =
        ViewCompat.OnUnhandledKeyEventListenerCompat { v, event ->
            if (event.keyCode == KeyEvent.KEYCODE_Z && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Undo (Ctrl + Z) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            } else if (event.keyCode == KeyEvent.KEYCODE_F && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Find (Ctrl + F) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            }
            false
        }

    private var _binding: FragmentItemListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat)

        val recyclerView: RecyclerView = binding.itemList

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        setupRecyclerView(recyclerView, itemDetailFragmentContainer)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        itemDetailFragmentContainer: View?
    ) {

        //Init series
        Serie.loadSeries(requireContext())

        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            Serie.serieList, itemDetailFragmentContainer
        )
    }

    class SimpleItemRecyclerViewAdapter(
        private val seriesList: List<Serie>,
        private val itemDetailFragmentContainer: View?
    ) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if(viewType==0) {
                val binding = ItemListContentEndedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder2(binding)
            } else {
                val binding = ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder1(binding)
            }


        }


        override fun getItemViewType(position: Int): Int {
            return if(seriesList[position].status=="Ended") 0 else 1

        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

            val serie = seriesList[position]
            val mHolder = when(holder){
                is ViewHolder2 -> {
                    holder.tvSeriesName.text = serie.name
                    holder.tvLanguage.text = serie.language

                    holder.rbRating.rating = serie.rating*5/10

                    holder.ivPoster.setFromString(serie.image)
                    holder

                }
                is ViewHolder1 -> {
                    holder.tvSeriesName.text = serie.name
                    holder.tvLanguage.text = serie.language

                    holder.rbRating.rating = serie.rating*5/10

                    holder.ivPoster.setFromString(serie.image)
                    holder
                }
                else -> holder
            }


            with(mHolder.itemView) {
                tag = serie
                setOnClickListener { itemView ->
                    val item = itemView.tag as Serie
                    val bundle = Bundle()
                    bundle.putInt(
                        ItemDetailFragment.ARG_ITEM_ID,
                        item.id
                    )
                    if (itemDetailFragmentContainer != null) {
                        itemDetailFragmentContainer.findNavController()
                            .navigate(R.id.fragment_item_detail, bundle)
                    } else {
                        itemView.findNavController().navigate(R.id.show_item_detail, bundle)
                    }
                }


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    /**
                     * Context click listener to handle Right click events
                     * from mice and trackpad input to provide a more native
                     * experience on larger screen devices
                     */
                    setOnContextClickListener { v ->

                        Toast.makeText(
                            v.context,
                            "Context click of item " + serie.id,
                            Toast.LENGTH_LONG
                        ).show()
                        true
                    }
                }

                setOnLongClickListener { v ->
                    // Setting the item id as the clip data so that the drop target is able to
                    // identify the id of the content
                    val clipItem = ClipData.Item(serie.id.toString())
                    val dragData = ClipData(
                        v.tag as? CharSequence,
                        arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                        clipItem
                    )

                    if (Build.VERSION.SDK_INT >= 24) {
                        v.startDragAndDrop(
                            dragData,
                            View.DragShadowBuilder(v),
                            null,
                            0
                        )
                    } else {
                        v.startDrag(
                            dragData,
                            View.DragShadowBuilder(v),
                            null,
                            0
                        )
                    }
                }
            }
        }

        override fun getItemCount() = seriesList.size

        inner class ViewHolder1(binding: ItemListContentBinding) :
            ViewHolder(binding.root) {
            val tvSeriesName: TextView = binding.tvSerieName
            val tvLanguage: TextView = binding.tvLanguage
            val ivPoster: ImageView = binding.ivPoster
            val rbRating: RatingBar = binding.rbRating
        }

        inner class ViewHolder2(binding: ItemListContentEndedBinding) :
            ViewHolder(binding.root) {
            val tvSeriesName: TextView = binding.tvSerieName
            val tvLanguage: TextView = binding.tvLanguage
            val ivPoster: ImageView = binding.ivPoster
            val rbRating: RatingBar = binding.rbRating
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}