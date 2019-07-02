package dev.vespertine.satoshilaw

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.timer_list_element_layout.view.*

class TimerAdapter(
    projects : MutableList<Project> = mutableListOf()
) : BaseRecyclerAdapter<Project>(projects) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.timer_list_element_layout, parent, false))

    class ViewHolder(view : View) : BaseViewHolder<Project>(view) {
        override fun onBind(data: Project){
            view.timer_project_name.text = data.projectName
            view.timer_client_name.text = data.clientName

            view.timer_hours_textview.text = (data.duration.toString() + ":00 hrs")



/*            view.setOnClickListener{
                val intent = Intent(view.context, QueenDetails::class.java)
                intent.putExtra("queen", data.id)
                ContextCompat.startActivity(view.context, intent, null)

            }*/

        }
    }



}