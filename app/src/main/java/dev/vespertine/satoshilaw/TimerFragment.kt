package dev.vespertine.satoshilaw

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import kotlinx.android.synthetic.main.fragment_timer.*


class TimerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    val list = mutableListOf<Project>()
    lateinit var adapter : TimerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val p1 = Project(1, "Project Name", "Client Name",
            0.00, 0, 0, 0)
        val p2 = Project(2, "Summer Hackathon", "Lambda School",
            30.00, 24, 9, 21 )

        adapter = TimerAdapter((activity as MainActivity).getProjects())

        timer_recyclerView.adapter = adapter
        floatingActionButton.setOnClickListener{v->
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, EditProjectFragment.newInstance())
                ?.commit()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    companion object {
        @JvmStatic
        fun newInstance() = TimerFragment()

    }
}
