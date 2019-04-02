package hu.gergo.kovacs.jetpackdemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import hu.gergo.kovacs.jetpackdemo.Repository
import hu.gergo.kovacs.jetpackdemo.model.Item
import hu.gergo.kovacs.jetpackdemo.util.ItemData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * @author Gergo Kovacs
 *
 * @version 1.0
 *
 * @date 2019.03.30.
 */

class ItemViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "ItemViewModel"
    private val repository = Repository.getInstance(application)
    val items: LiveData<List<Item>> = repository.items

    fun deleteOneItem() {
        GlobalScope.launch {
            val items = repository.itemSnapshot

            if (items.isNotEmpty()) {
                repository.deleteItem(items[0])
            }
        }
    }

    fun addOneRandomItem() {
        GlobalScope.launch {
            val texts = ItemData.texts
            val images = ItemData.images

            val randomTextIndex = Random.nextInt(0, texts.size)
            val randomImageIndex = Random.nextInt(0, texts.size)

            repository.addItem(Item(texts[randomTextIndex], images.getResourceId(randomImageIndex, -1)))
        }
    }

    fun resetDataset() {
        GlobalScope.launch {
            repository.deleteAll()

            val texts = ItemData.texts
            val images = ItemData.images

            texts.forEachIndexed { index, text ->
                val id = images.getResourceId(index, -1)
                repository.addItem(Item(text, id))
            }
        }
    }
}