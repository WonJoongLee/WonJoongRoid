package org.sopt.androidseminar.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RvItemDecoration(private val padding: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            top = padding
            bottom = padding
        }
    }
}