package com.exam.dispositivosmoviles.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.ArrayAdapter
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.stringPreferencesKey

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exam.dispositivosmoviles.R

import com.exam.dispositivosmoviles.logic.data.MarvelChars

import com.exam.dispositivosmoviles.databinding.FragmentFirstBinding
import com.exam.dispositivosmoviles.logic.data.getMarvelCharsDB
import com.exam.dispositivosmoviles.logic.marvelLogic.MarvelLogic
import com.exam.dispositivosmoviles.ui.activities.DetailsMarvelActivity
import com.exam.dispositivosmoviles.ui.activities.dataStore
import com.exam.dispositivosmoviles.ui.adapters.MarvelAdapter
import com.exam.dispositivosmoviles.ui.data.UserDataStore
import com.exam.dispositivosmoviles.ui.utilities.DispositivosMoviles
import com.exam.dispositivosmoviles.ui.utilities.Metodos
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private lateinit var lManager: LinearLayoutManager
    private lateinit var gManager: GridLayoutManager
    private val limit: Int = 99
    private var offset: Int = 0

    private var rvAdapter: MarvelAdapter = MarvelAdapter() {
        sendMarvelItem(it)
    }


    private var marvelCharItems: MutableList<MarvelChars> = mutableListOf<MarvelChars>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(
            layoutInflater,
            container,
            false
        )

        lManager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL, false
        )
        gManager = GridLayoutManager(
            requireActivity(), 2
        )
        return binding.root
    }

    override fun onStart() {
        super.onStart()

        lifecycleScope.launch(Dispatchers.IO) {
            getDataStore()
                .collect {user ->
                    Log.d("UCE",user.name )
                    Log.d("UCE",user.email )
                    Log.d("UCE",user.session )
                    //binding.txtFilter.text = user.name.toString()
                }
        }

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
            chargeDataRVDB(limit, offset)
            binding.rvSwipe.isRefreshing = false
        }

        binding.rvMarvelChars.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {

                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)


                    if (dy > 0) {

                        val elementos = lManager.childCount
                        val posicion = lManager.findFirstVisibleItemPosition()
                        val tamanio = lManager.itemCount

                        if ((elementos + posicion) >= tamanio) {
                            //chargeDataRV(search= "spider")
                            lifecycleScope.launch(Dispatchers.IO) {
                                val items = MarvelLogic().getAllMarvelChars(0, 99)
                                withContext(Dispatchers.Main) {
                                    rvAdapter.updateListItems(items)
                                    this@FirstFragment.offset += offset
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

    private fun saveMarvelItem(item: MarvelChars): Boolean {
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                DispositivosMoviles
                    .getDbInstance()
                    .mairvelDAO()
                    .insertMarvelChar(listOf(item.getMarvelCharsDB()))
            }
        }
        return true
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

    private fun chargeDataRV(limit: Int, offset: Int) {
        lifecycleScope.launch(Dispatchers.Main) {
            //rvAdapter.items=MarvelLogic().getAllMarvelChars(0,30)

            marvelCharItems = withContext(Dispatchers.IO) {
                return@withContext (
                        MarvelLogic().getAllMarvelChars(
                            offset,
                            limit
                        ))
            } as MutableList<MarvelChars>

            rvAdapter.items =
                MarvelLogic().getAllMarvelChars(0, 99)

            binding.rvMarvelChars.apply {
                this.adapter = rvAdapter
                this.layoutManager = gManager
            }
            this@FirstFragment.offset = offset + limit
        }

    }

    //    creo que se cambia a suspend
    private fun chargeDataRVDB(limit: Int, offset: Int) {
        if (Metodos().isOnline(requireActivity())) {
            lifecycleScope.launch(Dispatchers.Main) {

                marvelCharItems = withContext(Dispatchers.IO) {

                    return@withContext MarvelLogic().getInitChars(limit, offset)
                }
                rvAdapter.items = marvelCharItems
                this@FirstFragment.offset += limit
                binding.rvSwipe.isRefreshing


            }
        } else {

            Snackbar.make(binding.cardView2, "No hay internet", Snackbar.LENGTH_LONG).show()

        }


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

    private fun getDataStore()=
        requireActivity().dataStore.data.map { prefs ->
            UserDataStore(
            name = prefs[(stringPreferencesKey("usuario"))].orEmpty(),
            email = prefs[(stringPreferencesKey("email"))].orEmpty(),
            session = prefs[(stringPreferencesKey("session"))].orEmpty()
            )
        }

}