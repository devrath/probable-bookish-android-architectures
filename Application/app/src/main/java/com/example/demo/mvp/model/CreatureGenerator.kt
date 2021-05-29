package com.example.demo.mvp.model

class CreatureGenerator {
    /** Formulae to generate creature hit points **/
    fun generateCreature(attributes: CreatureAttributes, name: String = "", drawable: Int = 0): Creature {
        val hitPoints = 5 * attributes.intelligence +
                3 * attributes.strength +
                4 * attributes.endurance
        return Creature(attributes, hitPoints, name, drawable)
    }
}