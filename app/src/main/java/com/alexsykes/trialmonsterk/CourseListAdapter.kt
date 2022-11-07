package com.alexsykes.trialmonsterk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseListAdapter(private val courseList: List<String>) : RecyclerView.Adapter<CourseListAdapter.CourseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_result_section, parent, false)
        return CourseViewHolder(view)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courseList.get(position)
        holder.courseLabel.text = course
    }

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
         val courseLabel: TextView = itemView.findViewById(R.id.courseLabel)

//        fun bind(course: String) {
//            courseLabel.text = course
//        }
//
//        companion object {
//            fun create(parent: ViewGroup) : CourseViewHolder {
//                val view: View = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.trial_item, parent, false)
//                return CourseViewHolder(view)
//            }
//        }
    }
}
