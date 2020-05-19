package edu.towson.cosc435.labsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_view.view.*

class UserAdapter(val controller: IController) : RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_view, parent, false)
        val holder = UserViewHolder(view)



        view.setOnLongClickListener {

            val position = holder.adapterPosition
            controller.deleteAt(position)
            this@UserAdapter.notifyItemRemoved(position)

            true
        }



        return holder
    }

    override fun getItemCount(): Int {
        return controller.users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = controller.users.get(position)

        holder.itemView.user_name_tv.text = user.name
        holder.itemView.user_age_tv.text = user.age.toString()

    }
}

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view)
