package ru.hivislav.testcinema.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.hivislav.testcinema.R
import ru.hivislav.testcinema.databinding.FragmentMainBinding
import ru.hivislav.testcinema.viewmodel.AppState
import ru.hivislav.testcinema.viewmodel.MainFragmentViewModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(this)[MainFragmentViewModel::class.java]
    }

    private val adapter = MainFragmentAdapter(object: OnItemViewClickListener{
        override fun onItemViewClick(title: String) {
            openFilmMovieDetailsFragment(title)
        }
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainFragmentRecycler.also {
            it.adapter = adapter
            binding.mainFragmentRecycler.layoutManager = LinearLayoutManager(requireContext(),
                                                            LinearLayoutManager.VERTICAL, false)
            binding.mainFragmentRecycler.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.getMoviesFromServer()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.loadingLayout.visibility = View.GONE
                Snackbar.make(binding.root, appState.error.message.toString(), Snackbar.LENGTH_INDEFINITE).setAction(
                    getString(R.string.snackbar_error_try_again)) {viewModel.getMoviesFromServer()}.show()
            }

            is AppState.Loading -> {
                binding.loadingLayout.visibility = View.VISIBLE
            }

            is AppState.Success -> {
                binding.loadingLayout.visibility = View.GONE
                appState.moviesListResponse.let { adapter.setMoviesData(it.items) }
            }
        }
    }

    interface OnItemViewClickListener{
        fun onItemViewClick(title: String)
    }

    private fun openFilmMovieDetailsFragment(title: String) {
        val manager = requireActivity().supportFragmentManager
        val bundle = Bundle()
        bundle.putString(MOVIE_DETAILS_DIALOG_FRAGMENT_EXTRA, title)
        MovieDetailsDialogFragment.newInstance(bundle).show(manager, "")
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}