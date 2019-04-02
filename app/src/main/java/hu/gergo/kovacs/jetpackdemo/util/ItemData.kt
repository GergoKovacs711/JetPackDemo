package hu.gergo.kovacs.jetpackdemo.util

import android.content.res.TypedArray
import hu.gergo.kovacs.jetpackdemo.R

/**
 * @author Gergo Kovacs
 *
 * @version 1.0
 *
 * @date 2019.04.02.
 */

object ItemData{
    val texts: Array<String> = ApplicationHelper.context.resources.getStringArray(R.array.item_texts)
    val images: TypedArray = ApplicationHelper.context.resources.obtainTypedArray(R.array.item_images)
}

