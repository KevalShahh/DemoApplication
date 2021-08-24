package com.example.demoapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavArgs
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import org.w3c.dom.Text


class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args by navArgs<SecondFragmentArgs>()
        view.findViewById<TextView>(R.id.t_name).setText(args.name)
        view.findViewById<TextView>(R.id.t_age).setText(args.modelName.age.toString())
        view.findViewById<TextView>(R.id.t_surname).setText(args.modelName.surname)
        view.findViewById<Button>(R.id.btn_previous).setOnClickListener {
            view.findNavController().navigateUp()
        }
    }
}