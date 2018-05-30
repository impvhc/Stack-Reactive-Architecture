package com.impvhc.stack.extension

import android.app.Activity
import android.content.Intent
import org.jetbrains.anko.browse
import org.jetbrains.anko.bundleOf

/**
 * Created by victor on 2/13/18.
 *
 * Extensions functions defined for the stack module.
 */

//region Activity
/**
 * Starts another activity.
 *
 * @param extras (optional)
 */
inline fun <reified T : Activity> Activity.goTo(vararg extras: Pair<String, Any>) {
    val intent = Intent(this, T::class.java)

    intent.putExtras(bundleOf(*extras))

    startActivity(intent)
}

/**
 * Starts another activity for result.
 *
 * @param requestCode (required)
 * @param extras (optional)
 */
inline fun <reified T : Activity> Activity.goToForResult(requestCode: Int, vararg extras: Pair<String, Any>) {
    val intent = Intent(this, T::class.java)

    intent.putExtras(bundleOf(*extras))

    startActivityForResult(intent, requestCode)
}

/**
 * Starts another activity, clearing the current stack.
 *
 * @param extras (optional)
 */
inline fun <reified T : Activity> Activity.resetTo(vararg extras: Pair<String, Any>) {
    val intent = Intent(this, T::class.java)

    intent.putExtras(bundleOf(*extras))

    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK

    startActivity(intent)
}

/**
 * Sends an intent to the system to open an url.
 *
 * @param url (required)
 */
inline fun Activity.browseTo(url: String) {
    browse(url)
}

/**
 * Finishes the current activity.
 */
inline fun Activity.goBack() {
    this.finish()
}

/**
 * Finishes the current activity that was initiated for result.
 *
 * @param requestCode (required) the code to send the result to.
 * @param data (optional)
 */
inline fun Activity.goBackWithResult(requestCode: Int, vararg data: Pair<String, Any>) {
    val dataIntent = Intent()

    dataIntent.putExtras(bundleOf(*data))

    setResult(requestCode, dataIntent)

    finish()
}
//endregion