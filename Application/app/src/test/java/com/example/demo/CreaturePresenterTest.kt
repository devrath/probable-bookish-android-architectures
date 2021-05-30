package com.example.demo

import com.example.demo.architectures.commonlayer.model.*
import com.example.demo.architectures.mvp.contracts.CreatureContract
import com.example.demo.architectures.mvp.presenter.CreaturePresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CreaturePresenterTest {

  private lateinit var presenter: CreaturePresenter

  @Mock
  lateinit var view: CreatureContract.View

  @Mock
  lateinit var mockGenerator: CreatureGenerator

  @Mock
  lateinit var mockRepository: CreatureRepository

  @Before
  fun setup() {
    MockitoAnnotations.initMocks(this)
    presenter = CreaturePresenter(mockGenerator, mockRepository)
    presenter.setView(view)
  }

  @Test
  fun testIntelligenceSelected() {
    val attributes = CreatureAttributes(10, 0, 0)
    val stubCreature = Creature(attributes, 50)
    `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

    presenter.attributeSelected(AttributeType.INTELLIGENCE, 3)

    verify(view, times(1)).showHitPoints("50")
  }

  @Test
  fun testStrengthSelected() {
    val attributes = CreatureAttributes(0, 3, 0)
    val stubCreature = Creature(attributes, 9)
    `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

    presenter.attributeSelected(AttributeType.STRENGTH, 1)

    verify(view, times(1)).showHitPoints("9")
  }

  @Test
  fun testEnduranceSelected() {
    val attributes = CreatureAttributes(0, 0, 7)
    val stubCreature = Creature(attributes, 28)
    `when`(mockGenerator.generateCreature(attributes)).thenReturn(stubCreature)

    presenter.attributeSelected(AttributeType.ENDURANCE, 2)

    verify(view, times(1)).showHitPoints("28")
  }
  
}