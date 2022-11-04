package com.alexsykes.trialmonsterk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.ParsePosition

class TrialListAdapter : ListAdapter<Trial, TrialViewHolder>(TrialsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrialViewHolder {
        return TrialViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TrialViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }
}

    class TrialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trialItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            trialItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup) : TrialViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.trial_item, parent, false)
                return TrialViewHolder(view)
            }
        }

    }

    class TrialsComparator : DiffUtil.ItemCallback<Trial>() {
        override fun areItemsTheSame(oldItem: Trial, newItem: Trial): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Trial, newItem: Trial): Boolean {
            return oldItem.name == newItem.name
        }

    }

