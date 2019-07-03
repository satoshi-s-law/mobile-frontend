package dev.vespertine.satoshilaw

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_edit_project.*
import android.text.InputFilter
import android.text.TextWatcher







class EditProjectFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var project: Project? = null

//    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_project, container, false)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar!!.show()
     //   fragmentManager?.popBackStack()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hourly_edit_text.addTextChangedListener(NumberTextWatcher(hourly_edit_text, "#,###"))
        //start_edittext.filters = arrayOf<InputFilter>(InputFilterMinMax("1", "12"))
//        start_edittext.addTextChangedListener(object : TextWatcher {
//
//            override fun afterTextChanged(p0: Editable?) {
//                start_edittext.removeTextChangedListener(this)
//
//                if (p0 != null && p0.toString().isNotEmpty()) {
//                    try{
//                        val startString = Integer.parseInt(start_edittext.text.toString())
//                        val startSet = if (startString <= 12) "$startString:00 AM"
//                        else (startString - 12).toString() + ":00 PM"
//                        start_edittext.setText(startSet)
//                    } catch (e: NumberFormatException) {
//                        e.printStackTrace()
//                    }
//                }
//
//
//                start_edittext.addTextChangedListener(this)
//
//
//            }
//
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//            }
//        })
        //X Button
        edit_project_toolbar.setNavigationOnClickListener {
            dismiss()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, TimerFragment.newInstance())
                ?.addToBackStack(null)?.commit()}
        edit_project_toolbar.inflateMenu(R.menu.dialog)
        //Save Button
        edit_project_toolbar.setOnMenuItemClickListener {
            dismiss()
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.fragment_container, TimerFragment.newInstance())
                ?.addToBackStack(null)?.commit()
            true
        }


    }


    // TODO: Rename method, update argument and hook method into UI event
/*    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }*/

/*    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }*/

/*    override fun onDetach() {
        super.onDetach()
        listener = null
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditProjectFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = EditProjectFragment()
        fun 

    }
}
