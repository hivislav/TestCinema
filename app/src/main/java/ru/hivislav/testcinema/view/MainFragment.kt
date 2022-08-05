package ru.hivislav.testcinema.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import ru.hivislav.testcinema.databinding.FragmentMainBinding
import ru.hivislav.testcinema.model.entities.ActorDTO
import ru.hivislav.testcinema.model.entities.MovieDTO

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val actorsData = listOf<ActorDTO>(
        ActorDTO("Иванов Иван"),
        ActorDTO("Петров Петр"),
        ActorDTO("Семёнов Семен"),
        ActorDTO("Владимиров Владимир"),
        ActorDTO("Алексеев Алексей"),
        ActorDTO("Тимуров Тимур"),
        ActorDTO("Александров Александр"),
    )

    private val movieData = listOf<MovieDTO>(
        MovieDTO("Форсаж", "Режиссеров Режиссер", 1992, actorsData),
        MovieDTO("Матрица", "Режиссеров Режиссер", 1993, actorsData),
        MovieDTO("Властелин Колец", "Режиссеров Режиссер", 1994, actorsData),
        MovieDTO("Хоббит", "Режиссеров Режиссер", 2002, actorsData),
        MovieDTO("Другой Мир", "Режиссеров Режиссер", 2012, actorsData),
        MovieDTO("Парк Юрского Периода", "Режиссеров Режиссер", 1992, actorsData),
        MovieDTO("Обитель Зла", "Режиссеров Режиссер", 2022, actorsData),
        MovieDTO("Топ Ган", "Режиссеров Режиссер", 1995, actorsData),
        MovieDTO("Мстители", "Режиссеров Режиссер", 1992, actorsData),
    )

    private val adapter = MainFragmentAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainFragmentRecycler.adapter = adapter.apply{
            setMoviesData(movieData)
        }

        binding.mainFragmentRecycler.layoutManager = LinearLayoutManager(requireContext(),
                                                                        LinearLayoutManager.VERTICAL, false)
        binding.mainFragmentRecycler.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}