package com.mncgroup.mnccore.ui.notification

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mncgroup.core.ui.BaseFragment
import com.mncgroup.core.util.ext.observeData
import com.mncgroup.mnccore.R
import com.mncgroup.mnccore.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : BaseFragment() {

    private val viewModel: NotificationsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        viewModel.text.observeData(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_logout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_logout){
            viewModel.logoutUser()
            val intent = Intent(mContext, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}