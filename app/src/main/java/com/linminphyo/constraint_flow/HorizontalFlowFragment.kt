package com.linminphyo.constraint_flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.constraintlayout.helper.widget.Flow
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import com.google.android.material.button.MaterialButtonToggleGroup

/**
 * Created by lin min phyo on 2019-09-24.
 */

class HorizontalFlowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.layout_horizontal_flow, container, false)
    }

    val flow : Flow
        get() = requireView().findViewById(R.id.flow)

    val parent : ViewGroup
        get() = requireView() as ViewGroup

    val button_group_wrap_modes : MaterialButtonToggleGroup
        get() = requireView().findViewById(R.id.button_group_wrap_modes)

    val button_group_chain_style : MaterialButtonToggleGroup
        get() = requireView().findViewById(R.id.button_group_chain_style)

    val seekbar_horizontal_bias : AppCompatSeekBar
        get() = requireView().findViewById(R.id.seekbar_horizontal_bias)

    val button_group_vertical_alignments : MaterialButtonToggleGroup
        get() = requireView().findViewById(R.id.button_group_vertical_alignments)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        flow.setOrientation(Flow.HORIZONTAL)

        button_group_wrap_modes.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.button_wrap_mode_chain -> Flow.WRAP_CHAIN
                    R.id.button_wrap_mode_align -> Flow.WRAP_ALIGNED
                    else -> Flow.WRAP_NONE
                }.let {
                    TransitionManager.beginDelayedTransition(parent)
                    flow.setWrapMode(it)
                }
            }
        }

        button_group_chain_style.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                TransitionManager.beginDelayedTransition(parent)
                when (checkedId) {
                    R.id.button_chain_style_spread -> Flow.CHAIN_SPREAD
                    R.id.button_chain_style_spread_inside -> Flow.CHAIN_SPREAD_INSIDE
                    else -> Flow.CHAIN_PACKED
                }.let {
                    TransitionManager.beginDelayedTransition(parent)
                    flow.setHorizontalStyle(it)
                }

            }
        }

        seekbar_horizontal_bias.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                flow.setHorizontalBias(p1 / 100f)
            }

        })

        button_group_vertical_alignments.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked) {
                TransitionManager.beginDelayedTransition(parent)
                when (checkedId) {
                    R.id.button_vertical_alignment_top -> Flow.VERTICAL_ALIGN_TOP
                    R.id.button_vertical_alignment_bottom -> Flow.VERTICAL_ALIGN_BOTTOM
                    R.id.button_vertical_alignment_baseline -> Flow.VERTICAL_ALIGN_BASELINE
                    else -> Flow.VERTICAL_ALIGN_CENTER
                }.let {
                    flow.setVerticalAlign(it)
                }
            }
        }
    }
}