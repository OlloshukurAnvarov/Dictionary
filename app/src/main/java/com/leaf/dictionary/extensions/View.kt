package com.leaf.dictionary.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(resId : Int) : View = LayoutInflater.from(context).inflate(resId, this, false)