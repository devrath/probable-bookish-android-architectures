package com.istudio.demo.features.feature_network.di.coroutines

import com.istudio.demo.features.feature_network.di.coroutines.dispatcher.DefaultDispatcher
import com.istudio.demo.features.feature_network.di.coroutines.dispatcher.ImmediateDispatcher
import com.istudio.demo.features.feature_network.di.coroutines.dispatcher.IoDispatcher
import com.istudio.demo.features.feature_network.di.coroutines.dispatcher.MainDispatcher
import com.istudio.demo.features.feature_network.di.coroutines.scope.GlobalCoroutineScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesModule {

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @ImmediateDispatcher
    @Provides
    fun providesImmediateDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate

    @DelicateCoroutinesApi
    @GlobalCoroutineScope
    @Provides
    fun provideGlobalScope(): CoroutineScope {
        return GlobalScope
    }
}