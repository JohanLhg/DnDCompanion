package com.jlahougue.money_domain.model

enum class Currency {
    COPPER {
        override fun toDisplayedValue(value: Int): Int {
            return value % 10
        }
    },
    SILVER {
        override fun toDisplayedValue(value: Int): Int {
            return (value % 100).floorDiv(10)
        }
    },
    GOLD {
        override fun toDisplayedValue(value: Int): Int {
            return (value % 1000).floorDiv(100)
        }
    },
    PLATINUM {
        override fun toDisplayedValue(value: Int): Int {
            return value.floorDiv(1000)
        }
    };

    open fun toDisplayedValue(value: Int) = value

    companion object {
        fun from(findValue: String) =
            entries.find { it.name.equals(findValue, true) } ?: COPPER
    }
}