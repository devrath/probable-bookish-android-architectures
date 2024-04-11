package com.istudio.demo.common

interface ScreenProvider {
    val screenName : String
}

enum class Screen(val pageName : String) {
    SPLASH("page_splash"),
    MAIN("page_home"),
    SCREEN_ONE("page_screen_one"),
    SCREEN_TWO("page_screen_two"),
    SCREEN_THREE("page_screen_three"),
    SCREEN_FOUR("page_screen_four"),
    SCREEN_FIVE("page_screen_five"),
}