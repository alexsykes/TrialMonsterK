package com.alexsykes.trialmonsterk

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class TrialListAdapter : ListAdapter<Trial, TrialViewHolder>(TrialsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrialViewHolder {
        return TrialViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TrialViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
}

    class TrialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trialNameView: TextView = itemView.findViewById(R.id.nameView)
        private val trialDateView: TextView = itemView.findViewById(R.id.dateView)
        private val trialClubView: TextView = itemView.findViewById(R.id.clubView)

        fun bind(trial: Trial?) {
            trialNameView.text = trial?.name
            trialDateView.text = trial?.formatted_date
            trialClubView.text = trial?.club
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

