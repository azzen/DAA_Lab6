package ch.heigvd.iict.and.rest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ch.heigvd.iict.and.rest.ui.screens.AppContact
import ch.heigvd.iict.and.rest.ui.theme.MyComposeApplicationTheme
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

//        thread { withContext(Dispatchers.IO) {
//
//            val contactsDao = ContactsDatabase.getDatabase(this@MainActivity).contactsDao()
//            val repository = ContactsRepository(contactsDao)
//
//            val contacts = repository.allContacts.value
//            if(contacts == null || contacts.isEmpty()) {
//                val contact1 = Contact(0, "Doe", "John", "
//
//        }

        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme {
                AppContact(application = application as ContactsApplication)
            }
        }

    }

}