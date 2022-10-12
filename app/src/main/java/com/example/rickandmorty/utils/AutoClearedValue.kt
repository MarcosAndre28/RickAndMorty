package com.example.rickandmorty.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class AutoClearedValue<T : Any>(val fragment: Fragment) : ReadWriteProperty<Fragment, T> {

    private var value: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifeCucleOwner ->
                    viewLifeCucleOwner?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            value = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return value ?: throw  java.lang.IllegalArgumentException(
            "nunca deve ser chamando quando não estiver disponivel"
        )
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, _value: T) {
        value = _value
    }
}

fun <T : Any> Fragment.autoCleared() = AutoClearedValue<T>(this)