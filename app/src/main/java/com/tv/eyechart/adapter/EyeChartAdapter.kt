package com.tv.eyechart.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tv.eyechart.R
import com.tv.eyechart.model.EyeChartLineItem
import com.tv.eyechart.adapter.EyeChartAdapter.EyeChartViewHolder
import com.tv.eyechart.databinding.EyeChartLineBinding

class EyeChartAdapter : RecyclerView.Adapter<EyeChartViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<EyeChartLineItem>() {
        override fun areItemsTheSame(
            oldItem: EyeChartLineItem, newItem: EyeChartLineItem
        ): Boolean {
            return oldItem.textSize == newItem.textSize
        }

        override fun areContentsTheSame(
            oldItem: EyeChartLineItem, newItem: EyeChartLineItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EyeChartViewHolder {
        return EyeChartViewHolder(
            EyeChartLineBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: EyeChartViewHolder, position: Int) {
        val lineItem = differ.currentList[position]
        holder.bind(lineItem, position)
    }

    override fun getItemCount(): Int {
        val limit = 4
        return differ.currentList.size
    }

    inner class EyeChartViewHolder(private val binding: EyeChartLineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(eyeChartItem: EyeChartLineItem, position: Int) {
            val size = eyeChartItem.textSize.toFloat() * 20 / 20
            if (eyeChartItem.drawableList.isEmpty()) {
                Log.d("MYTAG", "drawable list is  empty")
                binding.eyeChartTextView.visibility = View.VISIBLE
                binding.imageLinearLay.visibility = View.GONE
                binding.eyeChartTextView.text = eyeChartItem.text
                binding.eyeChartTextView.textSize = size
                val paddingSize = (binding.eyeChartTextView.textSize/2).toInt();
                binding.eyeChartTextView.setPadding(paddingSize,paddingSize,paddingSize,paddingSize)
            } else {
                Log.d("MYTAG", "drawable list is not empty")
                binding.imageLinearLay.visibility = View.VISIBLE
                binding.eyeChartTextView.visibility = View.GONE
                binding.image1.setImageResource(eyeChartItem.drawableList[0])
                binding.image2.setImageResource(eyeChartItem.drawableList[1])
                binding.image3.setImageResource(eyeChartItem.drawableList[2])
                binding.image4.setImageResource(eyeChartItem.drawableList[3])
                binding.image5.setImageResource(eyeChartItem.drawableList[4])

                val textSize =
                    convertPixelsToXxhdpi(eyeChartItem.textSize.toFloat(), binding.root.context)

//                val paddingSize = (eyeChartItem.textSize/2)
//                binding.image1.setPadding(paddingSize,paddingSize,paddingSize,paddingSize)
//                binding.image2.setPadding(paddingSize,paddingSize,paddingSize,paddingSize)
//                binding.image3.setPadding(paddingSize,paddingSize,paddingSize,paddingSize)
//                binding.image4.setPadding(paddingSize,paddingSize,paddingSize,paddingSize)
//                binding.image5.setPadding(paddingSize,paddingSize,paddingSize,paddingSize)

                binding.image5.setImageResource(eyeChartItem.drawableList[4])
                binding.image1.layoutParams.height = textSize.toInt()
                binding.image1.layoutParams.width = textSize.toInt()
                binding.image1.requestLayout()

                binding.image2.layoutParams.height = textSize.toInt()
                binding.image2.layoutParams.width = textSize.toInt()
                binding.image2.requestLayout()

                binding.image3.layoutParams.height = textSize.toInt()
                binding.image3.layoutParams.width = textSize.toInt()
                binding.image3.requestLayout()

                binding.image4.layoutParams.height = textSize.toInt()
                binding.image4.layoutParams.width = textSize.toInt()
                binding.image4.requestLayout()

                binding.image5.layoutParams.height = textSize.toInt()
                binding.image5.layoutParams.width = textSize.toInt()
                binding.image5.requestLayout()
            }
            binding.eyeChartTextSize.text = size.toInt().toString()
            binding.eyeChartTextValue.text = (position + 1).toString()

        }

        private fun convertPixelsToXxhdpi(px: Float, context: Context): Float {
            val density = context.resources.displayMetrics.density
            val xxhdpiDensity = 3.0f
            return px * (xxhdpiDensity / density)
        }

    }


}