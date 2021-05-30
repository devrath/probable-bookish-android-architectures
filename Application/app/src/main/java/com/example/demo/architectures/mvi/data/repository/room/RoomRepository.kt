package com.example.demo.architectures.mvi.data.repository.room

import android.os.AsyncTask
import com.example.demo.CreaturemonApplication
import com.example.demo.architectures.mvi.data.repository.CreatureRepository
import com.example.demo.architectures.mvi.data.model.Creature
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RoomRepository : CreatureRepository {

  private val creatureDao: CreatureMviDao = CreaturemonApplication.databaseMvi.creatureDao()

  private val allCreatures: Observable<List<Creature>> = creatureDao.getAllCreatures()

  private var saveSubject: PublishSubject<Boolean> = PublishSubject.create()
  private val clearSubject: PublishSubject<Boolean> = PublishSubject.create()

  override fun saveCreature(creature: Creature): Observable<Boolean> {
    saveSubject = PublishSubject.create()
    if (canSaveCreature(creature)) {
      InsertAsyncTask(creatureDao) {
        saveSubject.onNext(true)
      }.execute(creature)
    } else {
      saveSubject.onError(Error("Please fill in all fields."))
    }
    return saveSubject
  }

  private fun canSaveCreature(creature: Creature): Boolean {
    return creature.drawable != 0 &&
        creature.name.isNotEmpty() &&
        creature.attributes.intelligence != 0 &&
        creature.attributes.strength != 0 &&
        creature.attributes.endurance !=0
  }

  override fun getAllCreatures() = allCreatures

  override fun clearAllCreatures(): Observable<Boolean> {
    DeleteAsyncTask(creatureDao) {
      clearSubject.onNext(true)
    }.execute()
    return clearSubject
  }

  private class InsertAsyncTask internal constructor(private val dao: CreatureMviDao,
                                                     private val completed: () -> Unit) : AsyncTask<Creature, Void, Void>() {
    override fun doInBackground(vararg params: Creature): Void? {
      dao.insert(params[0])
      return null
    }

    override fun onPostExecute(result: Void?) {
      super.onPostExecute(result)
      completed()
    }
  }

  private class DeleteAsyncTask internal constructor(private val dao: CreatureMviDao,
                                                     private val completed: () -> Unit) : AsyncTask<Void, Void, Void>() {
    override fun doInBackground(vararg params: Void): Void? {
      dao.clearAllCreatures()
      return null
    }

    override fun onPostExecute(result: Void?) {
      super.onPostExecute(result)
      completed()
    }
  }
}