package org.wikiedufoundation.wikiedudashboard.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_image_viewer.photoView
import kotlinx.android.synthetic.main.fragment_image_viewer.toolbar
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsActivity


/**
 * A simple [Fragment] subclass.
 * Use the [ImageViewerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageViewerFragment : Fragment() {

    private var image_url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            image_url = arguments!!.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater.inflate(R.layout.fragment_image_viewer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        context?.let { Glide.with(it).load(image_url).into(photoView) }
        toolbar.setNavigationOnClickListener { (context as MediaDetailsActivity).onBackPressed() }
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param image_url Parameter 1.
         * @return A new instance of fragment ImageViewerFragment.
         */
        fun newInstance(image_url: String): ImageViewerFragment {
            val fragment = ImageViewerFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, image_url)
            fragment.arguments = args
            return fragment
        }
    }
}
