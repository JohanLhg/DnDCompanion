package com.jlahougue.authentication_domain.model

import com.jlahougue.core_domain.util.UiText

class InvalidAuthException(val uiMessage: UiText) : Exception()