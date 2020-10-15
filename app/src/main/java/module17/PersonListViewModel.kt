package module17

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import jp.wasabeef.util.SingleLiveEvent

class PersonListViewModel: ViewModel() {

    private val personRepository = PersonRepository()
    private val _personLiveData = MutableLiveData<List<Person>>(personRepository.generatePersons(10))

    val personLiveData: LiveData<List<Person>> = _personLiveData

    private val _showToastLiveData = SingleLiveEvent<Unit>()

    val showToastLiveData: LiveData<Unit> = _showToastLiveData

    fun addPerson(){
        val updatedList = listOf(personRepository.createPerson()) + _personLiveData.value.orEmpty()
        _personLiveData.postValue(updatedList)
    }

    fun deletePerson(position: Int) {
        val updatedList = personRepository.deletePerson(_personLiveData.value.orEmpty(), position)
        _personLiveData.postValue(updatedList)
        _showToastLiveData.postValue(Unit)
    }
}