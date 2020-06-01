package com.example.googlesummerofcode

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*

class UserSecretAdapter(context: Context, resource: Int) :
    ArrayAdapter<Any?>(context, resource) {

    var list: ArrayList<Any?> = ArrayList<Any?>()
    var res = 0
    override fun add(`object`: Any?) {
        super.add(`object`)
        list.add(`object`)
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any? {
        return list[position]
    }

    internal class LayoutHandler {
        var Application_name: TextView? = null
        var USerNAme: TextView? = null
        var Pass: TextView? = null
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var row = convertView
        val layoutHandler: LayoutHandler
        if (row == null) {
            val layoutInflater =
                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            row = layoutInflater.inflate(R.layout.adapterview, parent, false)
            layoutHandler = LayoutHandler()
            layoutHandler.Application_name =
                row.findViewById<View>(R.id.application_name_text) as TextView
            layoutHandler.USerNAme =
                row.findViewById<View>(R.id.user_name_text) as TextView
            layoutHandler.Pass = row.findViewById<View>(R.id.pass_text) as TextView
            row.tag = layoutHandler
        } else {
            layoutHandler = row.tag as LayoutHandler
        }
        val us = getItem(position) as UserSecrets?
        if (us != null) {
            layoutHandler.Application_name?.setText(us.applicationame)
        }
        if (us != null) {
            layoutHandler.USerNAme?.setText(us.username)
        }
        if (us != null) {
            layoutHandler.Pass?.setText(us.password)
        }
        return row!!
    }
}