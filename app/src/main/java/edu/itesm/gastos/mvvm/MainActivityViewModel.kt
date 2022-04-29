package edu.itesm.gastos.mvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.itesm.gastos.dao.GastoDao
import edu.itesm.gastos.entities.Gasto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random


class MainActivityViewModel : ViewModel(){
     var liveData: MutableLiveData<List<Gasto>>
    init {
        liveData = MutableLiveData()
    }

    fun getLiveDataObserver(): MutableLiveData<List<Gasto>>{
        return liveData
    }

    fun getGastos(gastoDao: GastoDao){
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0..5){
                gastoDao.insertGasto(Gasto(0, "Gasto ${i}", Random.nextDouble() *100 ))
            }
            liveData.postValue(gastoDao.getAllGastos())
        }
    }
    fun insertaGastos(gastoDao: GastoDao, gasto: Gasto){
        CoroutineScope(Dispatchers.IO).launch{
            gastoDao.insertGasto(Gasto(0, gasto.description,  gasto.monto))
            liveData.postValue(gastoDao.getAllGastos())
        }
    }
    fun sumaGastos(gastoDao: GastoDao,gasto: Gasto){
        CoroutineScope(Dispatchers.IO).launch{
            gastoDao.sumarGastos(Gasto(0, gasto.description,gasto.monto))

        }

    }
}






