package edu.itesm.gastos.dao

import androidx.room.*
import edu.itesm.gastos.entities.Gasto

@Dao
interface GastoDao{
    @Query("SELECT * from Gasto")
    suspend fun getAllGastos(): List<Gasto>

    @Insert
    suspend fun insertGasto(gasto: Gasto)

    @Query("select sum(monto) from Gasto")
    suspend fun sumarGastos(monto: Gasto):List<Gasto>
}
