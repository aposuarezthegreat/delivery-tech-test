package apo.suarez.deliverytech.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DeliveryListDao {

    @Query("SELECT * from delivery_list ORDER BY id ASC LIMIT 20")
    fun getDeliveryList(): LiveData<List<DeliveryItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewDelivery(delivery: DeliveryItem)

    @Query("DELETE FROM delivery_list where id NOT IN (SELECT id from delivery_list ORDER BY id ASC LIMIT 20)")
    suspend fun deleteOldDeliveries()

    @Query("DELETE FROM delivery_list")
    fun nukeTable()

}