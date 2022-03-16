package com.example.retainfragment

import android.os.Bundle
import androidx.fragment.app.Fragment

class AdapterFragment : Fragment() {

    private var adapter = Adapter()

    var savedAdapter: Adapter
        get() {
            return adapter
        }
        set(value) {
            adapter = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}