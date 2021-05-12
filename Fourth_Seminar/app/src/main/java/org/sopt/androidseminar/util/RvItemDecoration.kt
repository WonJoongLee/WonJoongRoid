package org.sopt.androidseminar.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RvItemDecoration(private val padding: Int, private val rvType: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        with(outRect) {
            when (rvType) {
                // recyclerview가 repository인 경우
                REPO_RV_TYPE -> {
                    top = padding
                    bottom = padding
                }
                // recyclerview가 follower인 경우
                FOLLOWER_RV_TYPE -> {
                    top = padding
                    bottom = padding
                    left = padding
                    right = padding
                }
            }
        }
    }

    companion object {
        val REPO_RV_TYPE = 1
        val FOLLOWER_RV_TYPE = 2
    }
}