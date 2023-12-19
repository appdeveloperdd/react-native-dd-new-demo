package com.reactnativeddnewdemo

import android.app.Application
import android.util.Log
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReadableMap
import com.klaviyo.analytics.Klaviyo
import com.klaviyo.analytics.model.Event
import com.klaviyo.analytics.model.EventKey
import com.klaviyo.analytics.model.EventType

class DdNewDemoModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

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

  @ReactMethod
  fun createEvent(eventName: String, properties: ReadableMap) {
    val event = Event(EventType.CUSTOM(eventName))
    val iterator = properties.keySetIterator()
    while (iterator.hasNextKey()) {
      val key = iterator.nextKey()
      val value = properties.getString(key)
      event.setProperty(EventKey.CUSTOM(key), value.toString())
    }
    Klaviyo.createEvent(event)
  }

  @ReactMethod
  fun setPushToken(pushToken: String) {
    Klaviyo.setPushToken(pushToken)
  }

}
