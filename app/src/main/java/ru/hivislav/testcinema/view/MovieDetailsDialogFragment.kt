package ru.hivislav.testcinema.view

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import ru.hivislav.testcinema.R

const val MOVIE_DETAILS_DIALOG_FRAGMENT_EXTRA = "MOVIE_DETAILS_DIALOG_FRAGMENT_EXTRA"
private var titleMovie = ""

class MovieDetailsDialogFragment: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        arguments?.getString(MOVIE_DETAILS_DIALOG_FRAGMENT_EXTRA)?.let {
            titleMovie = it
        }

        return requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder
                .setTitle(getString(R.string.movie_details_dialog_fragment_title))
                .setMessage(resources.getString(R.string.movie_details_dialog_fragment_placeholder, titleMovie))
                .setIcon(R.drawable.ic_baseline_movie_24)
                .setPositiveButton(getString(R.string.movie_details_dialog_fragment_positive_button))
                    { dialog, _ ->  dialog.cancel()
                }
            builder.create()
            }
        }

    companion object {
        fun newInstance(bundle: Bundle): MovieDetailsDialogFragment {
            val fragment = MovieDetailsDialogFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}


