package com.example.dashedxmldesign

import android.os.Bundle
import android.view.ViewGroup.LayoutParams
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.example.dashedxmldesign.databinding.ActivityMainBinding
import com.example.dashedxmldesign.databinding.TooltipLayoutBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding
    private var tooltipPopup: PopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.m10Button.post {
            showTooltip()
        }
    }

    private fun showTooltip() {
        val tooltipBinding = TooltipLayoutBinding.inflate(layoutInflater)
        tooltipBinding.root

        tooltipBinding.closeTooltip.setOnClickListener {
            hideTooltipAndBorder()

        }

        val marginHorizontalDp = 20
        val density = resources.displayMetrics.density
        val marginHorizontalPx = (marginHorizontalDp * density).toInt()

        val screenWidth = resources.displayMetrics.widthPixels
        val popupWidth = screenWidth - 2 * marginHorizontalPx

        tooltipPopup = PopupWindow(
            tooltipBinding.root,
            popupWidth,
            LayoutParams.WRAP_CONTENT,
            true
        ).apply {
            showAsDropDown(mainBinding.m10Button, 0, 60)
        }


    }

    private fun hideTooltipAndBorder() {
        tooltipPopup?.dismiss()
        tooltipPopup = null
        mainBinding.buttonContainer.background = null
    }


    override fun onDestroy() {
        super.onDestroy()
        tooltipPopup?.dismiss()
    }
}
