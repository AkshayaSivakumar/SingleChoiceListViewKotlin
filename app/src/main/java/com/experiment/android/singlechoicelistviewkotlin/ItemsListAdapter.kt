package com.experiment.android.singlechoicelistviewkotlin

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.TextView

class ItemsListAdapter(
    private val context: Activity,
    private val itemsList: MutableList<ItemsModel>
) :
    ArrayAdapter<ItemsModel>(context, R.layout.item_lv, itemsList) {

    var selectedPosition: Int = 0
        get() = field
        set(value) {
            field = value
        }

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val rowView: View

        if (null == view) {
            rowView = LayoutInflater.from(context).inflate(R.layout.item_lv, parent, false)
            viewHolder = ViewHolder(rowView)
            rowView.tag = viewHolder
        } else {
            rowView = view
            viewHolder = rowView.tag as ViewHolder
        }

        viewHolder.rbSelection.tag = position
        viewHolder.tvItemName.text = getItem(position)?.name
        viewHolder.rbSelection.isChecked = position == selectedPosition
        viewHolder.rbSelection.setOnClickListener(
            onStateChangeListener(
                viewHolder.rbSelection,
                position
            )
        )

        return rowView
    }

    private fun onStateChangeListener(
        radioButton: RadioButton,
        position: Int
    ): View.OnClickListener {
        return View.OnClickListener {
            selectedPosition = if (radioButton.isChecked) {
                position
            } else {
                0
            }
            notifyDataSetChanged()
        }
    }

    private class ViewHolder(view: View) {
        var tvItemName: TextView = view.findViewById<TextView>(R.id.tv_label)
        var rbSelection: RadioButton = view.findViewById<RadioButton>(R.id.rb_selection)
    }
}