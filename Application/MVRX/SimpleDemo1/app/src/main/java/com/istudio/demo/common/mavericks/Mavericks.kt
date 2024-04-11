package com.istudio.demo.common.mavericks

import androidx.appcompat.app.AppCompatActivity
import com.airbnb.mvrx.ActivityViewModelContext
import com.airbnb.mvrx.InternalMavericksApi
import com.airbnb.mvrx.Mavericks
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelProvider
import com.airbnb.mvrx.RealMavericksStateFactory
import com.airbnb.mvrx._internal
import com.airbnb.mvrx.lifecycleAwareLazy
import kotlin.reflect.KClass

@OptIn(InternalMavericksApi::class)
inline fun <T, reified VM : MavericksViewModel<S>, reified S : MavericksState> T.activityViewModel(
    viewModelClass: KClass<VM> = VM::class,
    crossinline keyFactory: () -> String = { viewModelClass.java.name },
) where T : AppCompatActivity, T : MavericksView = lifecycleAwareLazy(this) {
    MavericksViewModelProvider.get(
        viewModelClass = viewModelClass.java,
        stateClass = S::class.java,
        viewModelContext = ActivityViewModelContext(
            activity = this,
            args = intent.extras?.get(Mavericks.KEY_ARG),
        ),
        key = keyFactory(),
        initialStateFactory = RealMavericksStateFactory(),
    ).apply { _internal(this@activityViewModel, action = { postInvalidate() }) }
}
