package com.yujun.trucksharing.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.yujun.trucksharing.R
import com.yujun.trucksharing.prefmanager.SharedPrefManager

class DetailsFragment : Fragment() {

    companion object {
        fun newInstance() = DetailsFragment()
    }

    var pref: SharedPrefManager? = null

    private var itemLocation: String? = ""
    private var pickUpTime: String? = ""
    private var receiverName: String? = ""
    private var weight: String? = ""
    private var strWidth: String? = ""
    private var height: String? = ""
    private var length: String? = ""
    private var goodType: String? = ""
    private var vehicleType: String? = ""

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_details, container, false)

        pref = SharedPrefManager(this.requireContext())

        itemLocation = arguments?.getString("itemLocation")
        pickUpTime = arguments?.getString("pickUpTime")
        receiverName = arguments?.getString("receiverName")
        weight = arguments?.getString("weight")
        strWidth = arguments?.getString("width")
        height = arguments?.getString("height")
        length = arguments?.getString("length")
        goodType = arguments?.getString("goodType")
        vehicleType = arguments?.getString("vehicleType")

        val detSender = rootView.findViewById<TextView>(R.id.detSender)
        val detTime = rootView.findViewById<TextView>(R.id.detTime)
        val detReceiver = rootView.findViewById<TextView>(R.id.detReceiver)
        val detDropLocation = rootView.findViewById<TextView>(R.id.detDropLocation)
        val detWeight = rootView.findViewById<TextView>(R.id.detWeight)
        val detIndustry = rootView.findViewById<TextView>(R.id.detIndustry)
        val detWidth = rootView.findViewById<TextView>(R.id.detWidth)
        val detHeight = rootView.findViewById<TextView>(R.id.detHeight)
        val detLength = rootView.findViewById<TextView>(R.id.detLength)
        val detCallDriverBtn = rootView.findViewById<AppCompatButton>(R.id.detCallDriver)


        detSender.text = pref!!.getFULL_NAME()
        detTime.text = pickUpTime
        detReceiver.text = receiverName
        detDropLocation.text = itemLocation
        detWeight.text = "$weight kg"
        detIndustry.text = goodType
        detWidth.text = "$strWidth m"
        detHeight.text = "$height m"
        detLength.text = "$length m"

        detCallDriverBtn.setOnClickListener {
            val mobileNumber = pref!!.getMOBILE()

            val intent = Intent()

            // def action and parse data with intent respectively
            intent.action = Intent.ACTION_DIAL
            intent.data = Uri.parse("tel: $mobileNumber")

            startActivity(intent)
        }

        return rootView
    }
}