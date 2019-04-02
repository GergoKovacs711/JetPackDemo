package hu.gergo.kovacs.jetpackdemo

import android.app.Application
import androidx.lifecycle.LiveData
import hu.gergo.kovacs.jetpackdemo.database.ItemDao
import hu.gergo.kovacs.jetpackdemo.database.ItemDataBase
import hu.gergo.kovacs.jetpackdemo.model.Item

/**
 * @author Gergo Kovacs
 * @version 1.0
 * @date 2019.03.30.
 */


class Repository private constructor(application: Application) {
    private val itemDao: ItemDao
    val items: LiveData<List<Item>>

    val itemSnapshot: List<Item>
        get() = itemDao.getAllEntriedSnapshot()

    init {
        val dataBase = ItemDataBase.getDataBase(application)
        itemDao = dataBase.itemDao
        items = itemDao.getAllEntries()
    }

    fun addItem(item: Item) {
        itemDao.insert(item)
    }

    fun deleteItem(item: Item) {
        itemDao.delete(item)
    }

    fun deleteAll() {
        itemDao.deleteAll()
    }

    companion object {
        private val INSTANCE: Repository? = null

        fun getInstance(application: Application): Repository {
            return INSTANCE ?: Repository(application)
        }
    }
}
