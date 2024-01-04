package com.reactnativeddnewdemo

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableType
import com.google.firebase.messaging.RemoteMessage
import com.klaviyo.analytics.Klaviyo
import com.klaviyo.analytics.model.Event
import com.klaviyo.analytics.model.EventKey
import com.klaviyo.analytics.model.EventType
import com.klaviyo.pushFcm.KlaviyoNotification
import org.json.JSONArray

class DdNewDemoModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return "DdNewDemo"
  }

  @ReactMethod
  fun initializeKlaviyo(publicKey: String) {
    Log.d("initializeKlaviyo", "initializeKlaviyo: $publicKey")
    val application = reactApplicationContext.applicationContext as Application
    Klaviyo.initialize(publicKey, application)
    application.registerActivityLifecycleCallbacks(Klaviyo.lifecycleCallbacks)
  }

  @ReactMethod
  fun setProfile(email: String) {
    Klaviyo.setEmail(email)
  }

  @ReactMethod
  fun resetProfile() {
    Klaviyo.resetProfile()
  }

  private fun convertStringToArray(s: String): Array<String> {
    var varString = s
    varString = varString.replace("\\[".toRegex(), "")
    varString = varString.replace("]".toRegex(), "")
    return varString.split(",").toTypedArray()
  }

  @ReactMethod
  fun createEvent(eventName: String, properties: ReadableMap) {
    val event = Event(EventType.CUSTOM(eventName))
    val iterator = properties.keySetIterator()
    while (iterator.hasNextKey()) {
      val key = iterator.nextKey()
      when (properties.getType(key)) {
        ReadableType.String -> {
          val value = properties.getString(key)
          event.setProperty(EventKey.CUSTOM(key), value.toString())
        }

        ReadableType.Array -> {
          val array = properties.getArray(key)
          val list = mutableListOf<String>()
          for (i in 0 until (array?.size() ?: 0)) {
            val value = array?.getString(i) ?: ""
            list.add(value)
          }
          event.setProperty(EventKey.CUSTOM(key), list.toTypedArray())
        }

        ReadableType.Number -> {
          val value = properties.getInt(key)
          Log.d("Number: ", value.toString())
          event.setProperty(EventKey.CUSTOM(key), value.toString())
        }

        ReadableType.Boolean -> {
          val value = properties.getBoolean(key)
          event.setProperty(EventKey.CUSTOM(key), value.toString())
        }

        else -> {
          val value = properties.getDouble(key)
          event.setProperty(EventKey.CUSTOM(key), value.toString())
        }
      }
    }
    Klaviyo.createEvent(event)
  }

  @ReactMethod
  fun setPushToken(pushToken: String) {
    Klaviyo.setPushToken(pushToken)
  }
  @ReactMethod
  fun displayNotification(message: RemoteMessage) {
    KlaviyoNotification(message).displayNotification(reactApplicationContext)
  }


}
