package ch.heigvd.iict.and.rest.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ch.heigvd.iict.and.rest.ContactsApplication
import ch.heigvd.iict.and.rest.models.Contact
import kotlinx.coroutines.launch

class ContactsViewModel(application: ContactsApplication) : AndroidViewModel(application) {

    private val repository = application.repository

    private val _selectedContact = MutableLiveData<Contact?>(null);
    val selectedContact : LiveData<Contact?> get() = _selectedContact;

    val allContacts = repository.allContacts

    fun selectContact(contact: Contact?) {
        _selectedContact.value = contact
    }

    fun enroll() {
        viewModelScope.launch {
            //TODO
        }
    }

    fun refresh() {
        viewModelScope.launch {
            //TODO
        }
    }

}

class ContactsViewModelFactory(private val application: ContactsApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactsViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}