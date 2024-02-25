package com.jlahougue.dndcompanion.data_authentication.domain.model

import com.jlahougue.core_domain.util.UiText

class InvalidAuthException(val uiMessage: UiText) : Exception()