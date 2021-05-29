package com.example.demo.mvp.model.room

import android.os.AsyncTask
import com.example.demo.CreaturemonApplication
import com.example.demo.mvp.model.Creature
import com.example.demo.mvp.model.CreatureRepository

class RoomRepository : CreatureRepository {
  private val creatureDao: CreatureDao = CreaturemonApplication.database.creatureDao()

  private class InsertAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {
      return null
    }
  }

  private class DeleteAsyncTask internal constructor(private val dao: CreatureDao) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {
      return null
    }
  }
}