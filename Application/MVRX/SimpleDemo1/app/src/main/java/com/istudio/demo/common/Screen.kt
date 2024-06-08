package com.istudio.demo.common

import com.istudio.demo.features.screen_five.ScreenFiveFragment
import com.istudio.demo.features.screen_four.ScreenFourFragment
import com.istudio.demo.features.feature_counter.CounterScreenFragment
import com.istudio.demo.features.screen_three.ScreenThreeFragment

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

enum class NavbarPage(val navigationId : String, val jClass : Class<*>) {
    ScreenOne("ScreenOne", CounterScreenFragment::class.java),
    ScreenTwo("ScreenTwo", CounterScreenFragment::class.java),
    ScreenThree("ScreenThree", ScreenThreeFragment::class.java),
    ScreenFour("ScreenFour", ScreenFourFragment::class.java),
    ScreenFive("ScreenFive", ScreenFiveFragment::class.java)
}

val navigationIdsUsesNavbar = listOf(
    NavbarPage.ScreenOne.navigationId,
    NavbarPage.ScreenTwo.navigationId,
    NavbarPage.ScreenThree.navigationId,
    NavbarPage.ScreenFour.navigationId,
    NavbarPage.ScreenFive.navigationId,
)