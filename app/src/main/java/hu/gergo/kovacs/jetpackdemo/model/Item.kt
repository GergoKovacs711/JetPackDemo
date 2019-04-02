package hu.gergo.kovacs.jetpackdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Gergo Kovacs
 *
 * @version 1.0
 *
 * @date 2019.03.29.
 */


@Entity(tableName = "item_table")
data class Item(

    @ColumnInfo(name = "text")
    var text: String,

    @ColumnInfo(name = "resourceid")
    var imageResourceId: Int
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    var uid: Long? = null
}

