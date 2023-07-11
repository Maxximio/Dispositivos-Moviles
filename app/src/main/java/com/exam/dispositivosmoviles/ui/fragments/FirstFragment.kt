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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.dispositivosmoviles.R
import com.exam.dispositivosmoviles.logic.data.MarvelChars

import com.exam.dispositivosmoviles.databinding.FragmentFirstBinding
import com.exam.dispositivosmoviles.logic.marvelLogic.MarvelLogic
import com.exam.dispositivosmoviles.ui.activities.DetailsMarvelActivity
import com.exam.dispositivosmoviles.ui.adapters.MarvelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var lManager: LinearLayoutManager
    private lateinit var gManager: GridLayoutManager

    private  var rvAdapter: MarvelAdapter =MarvelAdapter{
        sendMarvelItem(it) }///////////////////

    private  var marvelCharItems: MutableList<MarvelChars> = mutableListOf<MarvelChars>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(
            layoutInflater,
            container,
            false
        )

        lManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL
            , false
        )
        gManager= GridLayoutManager(
            requireActivity(),2
        )
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        val list = arrayListOf<String>(
            "Dale Zelda Dale",
            "Pasame la ",
            "Trifuerza que tengo hambre",
            "pasame una botella de Sora grande"
        )

        val adapter = ArrayAdapter<String>(
            requireActivity(),
            R.layout.simple_layout, list
        )

        binding.spinner.adapter = adapter

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRVDB()
            binding.rvSwipe.isRefreshing = false
        }

        binding.rvMarvelChars.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    /*
                    val elementos = lManager.childCount
                    val posicion = lManager.findFirstVisibleItemPosition()
                    val tamanio = lManager.itemCount*/

                if (dy > 0) {

                    val elementos = lManager.childCount
                    val posicion = lManager.findFirstVisibleItemPosition()
                    val tamanio = lManager.itemCount

                    if ((elementos + posicion) >= tamanio) {
                        //chargeDataRV(search= "spider")
                        lifecycleScope.launch(Dispatchers.IO){
                            val items=MarvelLogic().getAllMarvelChars(0,99)
                             withContext(Dispatchers.Main) {
                                    rvAdapter.updateListItems(items)
                                }
                        }
                    }

                }
            }


        })

        /*binding.txtFilter.addTextChangedListener { txtFilter ->
            val result =
                marvelCharItems.filter { items ->
                    items.nombre.lowercase().contains(txtFilter.toString().lowercase())
                }
            rvAdapter.replaceListItems(result)
        }*/


    }

    private fun sendMarvelItem(item: MarvelChars) {
        val i = Intent(requireActivity(), DetailsMarvelActivity::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }

    /*fun coroutine() {
        lifecycleScope.launch(Dispatchers.Main) {
            var name = "Uno"
            name = withContext(Dispatchers.IO) {
                name = "Dos"
                return@withContext name
            }

            binding.cardView2.radius

        }
    }*/

    private fun chargeDataRV( search:String) {
        lifecycleScope.launch(Dispatchers.Main) {
            //rvAdapter.items=MarvelLogic().getAllMarvelChars(0,30)

            marvelCharItems = withContext(Dispatchers.IO){
                return@withContext (
                        MarvelLogic().getAllMarvelChars(
                    0,
                    20
                ))
            } as MutableList<MarvelChars>

            rvAdapter.items=
                MarvelLogic().getAllMarvelChars(0,99)

            binding.rvMarvelChars.apply {
                this.adapter = rvAdapter
                this.layoutManager = gManager
            }
        }

    }
    private fun chargeDataRVDB() {
        lifecycleScope.launch(Dispatchers.Main) {

            marvelCharItems = withContext(Dispatchers.IO){
                var marvelCharItems = MarvelLogic()
                    .getAll().toMutableList()

                if(marvelCharItems.isEmpty()){
                    marvelCharItems = (MarvelLogic().getCharactersStartsWith(
                        name="spider",
                        10
                    ).toMutableList())
                    MarvelLogic().insertMarvelCharstoDB(marvelCharItems)
                }
                return@withContext marvelCharItems
            }

            rvAdapter.items=marvelCharItems



            /*if(marvelCharsItems.isEmpty()){
                marvelCharItems = withContext(Dispatchers.IO){
                    return@withContext (MarvelLogic().getAll().toMutableList()
                            ))
                }
            }*/

            /*withContext(Dispatchers.IO){
                MarvelLogic().insertMarvelCharstoDB(marvelCharsItems)
            }

            rvAdapter.items= marvelCharItems*/

            binding.rvMarvelChars.apply {
                this.adapter = rvAdapter
                this.layoutManager = lManager


//            this.layoutManager = gManager
//            gManager.scrollToPositionWithOffset(pos, 10)
            }
        }

    }


}



