package com.istudio.demo.common

import com.istudio.demo.features.feature_network.NetworkScreenFragment
import com.istudio.demo.features.screen_four.ScreenFourFragment
import com.istudio.demo.features.feature_counter.CounterScreenFragment
import com.istudio.demo.features.screen_three.ScreenThreeFragment

interface ScreenProvider {
    val screenName : String
}

enum class Screen(val pageName : String) {
    SPLASH("page_splash"),
    MAIN_FEATURE("main_feature"),
    COUNTER_FEATURE("counter_feature"),
    NETWORK_FEATURE("network_feature"),
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
    ScreenFive("ScreenFive", NetworkScreenFragment::class.java)
}

val navigationIdsUsesNavbar = listOf(
    NavbarPage.ScreenOne.navigationId,
    NavbarPage.ScreenTwo.navigationId,
    NavbarPage.ScreenThree.navigationId,
    NavbarPage.ScreenFour.navigationId,
    NavbarPage.ScreenFive.navigationId,
)