package ru.hivislav.testcinema.view

import androidx.recyclerview.widget.DiffUtil
import ru.hivislav.testcinema.model.entities.MovieDTO

class MainFragmentDiffCallback(
    private val oldList: List<MovieDTO>,
    private val newList: List<MovieDTO>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].title == newList[newItemPosition].title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}