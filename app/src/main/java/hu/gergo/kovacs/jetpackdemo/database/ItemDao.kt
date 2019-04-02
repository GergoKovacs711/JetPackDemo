package hu.gergo.kovacs.jetpackdemo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import hu.gergo.kovacs.jetpackdemo.model.Item

/**
 * @author Gergo Kovacs
 *
 * @version 1.0
 *
 * @date 2019.03.30.
 */

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("DELETE FROM item_table")
    fun deleteAll()

    @Query("SELECT * FROM item_table ORDER BY text ASC")
    fun getAllEntries(): LiveData<List<Item>>

    @Query("SELECT * FROM item_table ORDER BY text ASC")
    fun getAllEntriedSnapshot() : List<Item>

    @Query("SELECT * FROM item_table WHERE uid = :uid")
    fun getItem(uid: Long) : Item

}

