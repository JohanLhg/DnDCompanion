package com.jlahougue.dndcompanion.feature_authentication.domain.model

import com.jlahougue.dndcompanion.core.domain.util.UiText

class InvalidAuthException(val uiMessage: UiText) : Exception()