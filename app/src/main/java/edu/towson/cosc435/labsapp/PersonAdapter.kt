package edu.towson.cosc435.contentproviderdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.towson.cosc435.labsapp.IController
import edu.towson.cosc435.labsapp.Person
import edu.towson.cosc435.labsapp.R
import kotlinx.android.synthetic.main.person_row.view.*

class PersonAdapter(val controller: IController) : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_row, parent, false)
        val holder = PersonViewHolder(view)
        return holder
    }

    override fun getItemCount(): Int {
        return controller.people.size
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person: Person = controller.people[position]
        holder.itemView.person_name_tv.text = person.name
        holder.itemView.person_age_tv.text = person.age.toString()
    }

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}