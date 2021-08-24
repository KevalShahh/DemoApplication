package com.example.demoapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
        Log.d("TAG", "onCreateView: "+"created view")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.btn_next).setOnClickListener {
            view.findNavController().navigate(
                FirstFragmentDirections.actionFirstFragmentToSecondFragment4(
                    view.findViewById<EditText>(R.id.et_name).text.toString(), navDemo(
                        Integer.parseInt(
                            view.findViewById<EditText>(R.id.et_age).text.toString()
                        ), view.findViewById<EditText>(R.id.et_surname).text.toString()
                    )
                )
            )
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("TAG", "onAttach: "+"attached")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("TAG", "onActivityCreated: "+"attached created")
    }

    override fun onStart() {
        super.onStart()
        Log.d("TAG", "onStart: "+"started")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("TAG", "onDetach: "+"detached")
    }
    override fun onStop() {
        super.onStop()
        Log.d("TAG", "onStop: "+"stopped")
    }
    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause: "+"paused")
    }
    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: "+"resumed")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy: "+"destroyed")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("TAG", "onDestroyView: "+"destroyed view")
    }
}