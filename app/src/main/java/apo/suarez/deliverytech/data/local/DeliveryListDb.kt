package apo.suarez.deliverytech.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DeliveryItem::class], version = 1, exportSchema = true)
abstract class DeliveryListDb : RoomDatabase(){

    abstract fun deliveryListDao() : DeliveryListDao

    companion object {

        @Volatile
        private var INSTANCE: DeliveryListDb? = null

        fun getDb(context: Context): DeliveryListDb {
            val tempInstance =
                INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DeliveryListDb::class.java,
                    "delivery_list_db"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}