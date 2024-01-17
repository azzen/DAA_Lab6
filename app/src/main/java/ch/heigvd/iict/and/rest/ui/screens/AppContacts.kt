package ch.heigvd.iict.and.rest.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import ch.heigvd.iict.and.rest.ContactsApplication
import ch.heigvd.iict.and.rest.R
import ch.heigvd.iict.and.rest.models.Contact
import ch.heigvd.iict.and.rest.viewmodels.ContactsViewModel
import ch.heigvd.iict.and.rest.viewmodels.ContactsViewModelFactory

@Composable
fun AppContact(application: ContactsApplication, contactsViewModel : ContactsViewModel = viewModel(factory= ContactsViewModelFactory(application))) {
    val context = LocalContext.current
    val contacts : List<Contact> by contactsViewModel.allContacts.observeAsState(initial = emptyList())
    val selectedContact : Contact? by contactsViewModel.selectedContact.observeAsState(initial = null)

    // create a mutable state to store if the user is currently editing a contact with a boolean
    // set it to false by default
    var editionMode by rememberSaveable { mutableStateOf(false) };
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.app_name)) },
                actions = {
                    if (!editionMode) {
                        IconButton(onClick = {
                            contactsViewModel.enroll()
                        }) { Icon(painter = painterResource(R.drawable.populate), contentDescription = null) }
                        IconButton(onClick = {
                            contactsViewModel.refresh()
                        }) { Icon(painter = painterResource(R.drawable.synchronize), contentDescription = null) }
                    }
                },
                navigationIcon = {
                    if (editionMode) {
                        IconButton(onClick = {
                            editionMode = !editionMode;
                        }) { Icon(painter = painterResource(com.google.android.material.R.drawable.ic_arrow_back_black_24), contentDescription = null) }
                    }
                }
            )
        },

        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (!editionMode) {
                FloatingActionButton(onClick = {
                    editionMode = true;
                    contactsViewModel.selectContact(null);
                }){
                    Icon(Icons.Default.Add, contentDescription = null)
                }
            }
        },
    )
    { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (editionMode) {
                ScreenContactEditor(selectedContact);
            } else {
                ScreenContactList(contacts) { selectedContact ->
                    editionMode = !editionMode;
                    contactsViewModel.selectContact(selectedContact);
                }
            }
        }
    }

}

