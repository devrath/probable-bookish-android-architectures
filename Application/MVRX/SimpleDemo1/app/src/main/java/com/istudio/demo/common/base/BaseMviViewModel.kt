package com.istudio.demo.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * This is the base class that handles the core of Mvi - interaction between [UiState], [UiAction]
 * and [UiEvent].
 * [UiAction] happens -> [handleActions] is called -> base on the type of action, it can [setState]
 * or [emitEvent]. Note that [setState] and [emitEvent] are not mutually exclusive, depending on
 * the requirement, it can have both.
 */
abstract class BaseMviViewModel<Action : UiAction, State : UiState, Event : UiEvent> :
    ViewModel() {
    abstract fun createInitialState(): State

    private val _viewState: MutableStateFlow<State> by lazy { MutableStateFlow(createInitialState()) }
    val viewState: StateFlow<State>
        get() = _viewState.asStateFlow()

    private val _action: MutableSharedFlow<Action> = MutableSharedFlow()

    private val _events: MutableSharedFlow<Event> = MutableSharedFlow()
    val events = _events.asSharedFlow()

    init {
        subscribeToActions()
    }

    /**
     * Emit an [UiAction] for [UiState] change or emit [UiEvent].
     * The direction will be from UI/business logic to business logic.
     */
    fun emitAction(action: Action) {
        viewModelScope.launch { _action.emit(action) }
    }

    /**
     * Method to set a new state.
     * [UiState] should only change after an [UiAction] happens.
     * The direction will be from business logic to UI.
     */
    protected fun setState(reducer: State.() -> State) {
        val newState = viewState.value.reducer()
        _viewState.value = newState
    }

    private fun subscribeToActions() {
        viewModelScope.launch {
            _action.collect {
                handleActions(it)
            }
        }
    }

    abstract fun handleActions(action: Action)

    /**
     * Method to emit an [UiEvent] after an [UiAction] happens.
     * This direction will be from business logic to UI.
     */
    protected fun emitEvent(event: Event) {
        viewModelScope.launch { _events.emit(event) }
    }
}

/**
 * [UiState] represent the current state of the screen. Here are some examples:
 * - Loading
 * - Result Ready -> after some data is received from backend
 * - Error
 * Depending on the page structure, class implements [UiState] can be sealed class or data class
 */
interface UiState

/**
 * [UiAction] represent any action that leads to state change or emit event. Here are some example:
 * - Retry clicked - corresponding to an user action that change the ui to Loading state
 * - Api result ready - api response received from backend, and turn the ui into Result Ready state
 * - Article card tapped - an user action that emit an event to launch article details page
 */
interface UiAction

/**
 * [UiEvent] represent one time event triggered by [UiAction], i.e. show toast or show dialog will
 * be classified as an event.
 * Class implements [UiEvent] should be a sealed class.
 * Here are some example:
 * - ShowErrorToast(errorMessage) - One time event for UI to show a error message in form of toast
 * - NavigateToBrowse - One time event that navigate to Browse
 */
interface UiEvent