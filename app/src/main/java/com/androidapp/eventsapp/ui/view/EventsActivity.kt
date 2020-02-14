package com.androidapp.eventsapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.androidapp.eventsapp.R
import com.androidapp.eventsapp.rx.RxBus
import com.androidapp.eventsapp.ui.event.EventClickEvent
import com.androidapp.eventsapp.util.addFragment
import com.androidapp.eventsapp.util.replaceFragment
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_events.*

class EventsActivity : AppCompatActivity() {

    lateinit var subscribeEventClickEvent: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)
        if (fragmentsContainer != null) {
            if (savedInstanceState != null) {
                return
            }
            addFragment(EventsListFragment(), R.id.fragmentsContainer, true)
        }

        initSubscriptions()
    }

    private fun initSubscriptions() {

        subscribeEventClickEvent =
            RxBus.listen(EventClickEvent::class.java).subscribe {
                replaceFragment(
                    GuestListFragment.newInstance(it.event.id),
                    R.id.fragmentsContainer,
                    true
                )

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (!subscribeEventClickEvent.isDisposed) {
            subscribeEventClickEvent.dispose()
        }
    }
}
