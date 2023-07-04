package com.exam.dispositivosmoviles.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.data.entities.MarvelChars
import com.exam.dispositivosmoviles.data.entities.marvel.Marvel

import com.exam.dispositivosmoviles.databinding.FragmentFirstBinding
import com.exam.dispositivosmoviles.logic.jikanLogic.JikanAnimeLogic
import com.exam.dispositivosmoviles.logic.lists.ListItems
import com.exam.dispositivosmoviles.logic.marvelLogic.MarvelLogic
import com.exam.dispositivosmoviles.ui.activities.DetailsMarvelActivity
import com.exam.dispositivosmoviles.ui.activities.MainActivity
import com.exam.dispositivosmoviles.ui.activities.PedidosActivity
import com.exam.dispositivosmoviles.ui.adapters.MarvelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : Fragment() {

    private  lateinit var binding: FragmentFirstBinding
    private lateinit var lmanager: LinearLayoutManager
    private  var rvAdapter: MarvelAdapter = MarvelAdapter { sendMarvelItem(it) }

    private lateinit var marvelCharItems: MutableList<MarvelChars>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)

        lmanager = LinearLayoutManager(
            requireActivity(),LinearLayoutManager.VERTICAL,false


        )
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val list = arrayListOf<String>("Dale Zelda Dale",
            "Pasame la ",
            "Trifuerza que tengo hambre",
            "pasame una botella de Sora grande")

        val adapter = ArrayAdapter<String>(requireActivity(),
            R.layout.simple_layout, list)

        binding.spinner.adapter = adapter
//        binding.listview.adapter= adapter

        /*val rvAdapter = MarvelAdapter(
            ListItems<Any>().returnMarvelChars()
        ) { sendMarvelItem(it) }
        val rvMarvel = binding.rvMarvelChars

        with(rvMarvel){
            this.adapter=rvAdapter
            this.layoutManager=LinearLayoutManager(
                requireActivity(),LinearLayoutManager.VERTICAL,false
            )
        }*/
        binding.spinner.adapter=adapter

        chargeDataRV("cap")

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV("cap")
            binding.rvSwipe.isRefreshing=false
        }
        /*rvMarvel.adapter = rvAdapter
        rvMarvel.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL,false)*/
        binding.rvMarvelChars.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int){

                    if(dy > 0){
                        val v = lmanager.childCount
                        val p = lmanager.findFirstVisibleItemPosition()
                        val t = lmanager.itemCount
                        if((v+p) >= t){
//                        chargeDataRV("spider")
                            lifecycleScope.launch(Dispatchers.IO)
                            {
//                                val newItems = MarvelLogic().getCharactersStartsWith(
//                                    "spider",20)
                                val newItems = JikanAnimeLogic().getAllAnimes(
                                    )

                                withContext(Dispatchers.Main){
                                    rvAdapter.updateListItems(newItems)
                                }
                            }
                        }

                    }
                }




        })

        binding.txtFilter.addTextChangedListener{txtFilter -> val result = marvelCharItems.filter { items -> items.nombre.contains(txtFilter.toString()) }
        }

        rvAdapter.replaceListItems(marvelCharItems)
    }

    fun sendMarvelItem(item: MarvelChars){
        val i=Intent(requireActivity(),DetailsMarvelActivity::class.java)
        i.putExtra("name",item)
        startActivity(i)
    }

    fun coroutine(){
        lifecycleScope.launch(Dispatchers.Main){
            var name = "Uno"
              name = withContext(Dispatchers.IO){
                name = "Dos"
                  return@withContext name
            }

            binding.cardView2.radius

        }
    }
    fun chargeDataRV(search: String){




        lifecycleScope.launch(Dispatchers.IO){
//            val rvAdapter = MarvelAdapter(
//            ListItems<Any>().returnMarvelChars()
                //JikanAnimeLogic().getAllAnimes()
//            MarvelLogic().getCharactersStartsWith(search,20)
//            ) { sendMarvelItem(it) }
//                JikanAnimeLogic().getAllAnimes()
//            ) { sendMarvelItem(it) }

//            rvAdapter.items =
//                JikanAnimeLogic().getAllAnimes()

//            var jikanItems = rvAdapter.items =
//                JikanAnimeLogic().getAllAnimes()
//
//
//            withContext(Dispatchers.Main){
//                val rvMarvel = binding.rvMarvelChars
//
//                with(rvMarvel){
//                    this.adapter=rvAdapter
//                    this.layoutManager= lmanager
//                }
//            }
//
//
//
//        }

    }





}