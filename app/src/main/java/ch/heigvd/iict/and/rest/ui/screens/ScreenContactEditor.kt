package ch.heigvd.iict.and.rest.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ch.heigvd.iict.and.rest.R
import ch.heigvd.iict.and.rest.models.Contact
import ch.heigvd.iict.and.rest.models.PhoneType
import ch.heigvd.iict.and.rest.ui.theme.MyComposeApplicationTheme
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Calendar

@Composable
fun ContactTextField(label: String, value: String) {
    var text by rememberSaveable { mutableStateOf(value) }
    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(modifier = Modifier.fillMaxWidth(),
            label = { Text(label) },
            maxLines = 1,
            value = text,
            onValueChange =  { text = it }
        )
    }
}

@Composable
fun PhoneTypeRadio(radioOptions: List<PhoneType>, chosenOption: PhoneType?) {
    var selectedOption by rememberSaveable {
        mutableStateOf(chosenOption ?: radioOptions[0])
    }
    Text(modifier = Modifier.padding(top=2.dp), text = "Phone type")
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        radioOptions.forEach { option ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = (option == selectedOption),
                    onClick  = { selectedOption = option }
                )
                Text(text = when(option) {
                    PhoneType.HOME -> "Home"
                    PhoneType.OFFICE -> "Office"
                    PhoneType.MOBILE -> "Mobile"
                    PhoneType.FAX -> "Fax"
                })
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun ScreenContactEditor(contact: Contact?) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 12.dp, end = 12.dp)
        .verticalScroll(rememberScrollState())) {
        if (contact == null) {
            Text(text = stringResource(R.string.add_contact), fontSize = 24.sp, modifier = Modifier.padding(top = 12.dp, bottom = 12.dp))
        } else {
            Text(text = stringResource(R.string.edit_contact), fontSize = 24.sp, modifier = Modifier.padding(top = 12.dp, bottom = 12.dp))
        }
        // form to add a contact
        ContactTextField(
            label = "Name",
            value = contact?.name ?: ""
        )
        ContactTextField(
            label = "Firstname",
            value = contact?.firstname ?: ""
        )
        ContactTextField(
            label = "E-mail",
            value = contact?.email ?: ""
        )
        ContactTextField(
            label = "Birthday",
            value = SimpleDateFormat("dd.MM.yyyy").format(contact?.birthday?.time ?: Calendar.getInstance().time)
        )
        ContactTextField(
            label = "Address",
            value = contact?.address ?: ""
        )
        ContactTextField(
            label = "Zip",
            value = contact?.zip ?: ""
        )
        ContactTextField(
            label = "City",
            value = contact?.city ?: ""
        )

        PhoneTypeRadio(
            radioOptions = listOf(PhoneType.HOME, PhoneType.OFFICE, PhoneType.MOBILE, PhoneType.FAX),
            chosenOption = contact?.type
        )

        ContactTextField(
            label = "Phone number",
            value = contact?.phoneNumber ?: ""
        )

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.cancel))
            }
            if (contact != null) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(R.string.delete))
                }
            }
            Button(onClick = { /*TODO*/ }) {
                if (contact == null) {
                    Text(text = stringResource(R.string.create))
                } else {
                    Text(text = stringResource(R.string.save))
                }
            }
        }

    }
}