package com.tailoredapps.androidapptemplate.base.ui

import at.florianschuster.control.EffectController
import kotlinx.coroutines.flow.Flow

abstract class EffectControllerViewModel<Action, State, Effect>: ControllerViewModel<Action, State>() {
    abstract override val controller: EffectController<Action, State, Effect>

    val effects: Flow<Effect> get() = controller.effects
}