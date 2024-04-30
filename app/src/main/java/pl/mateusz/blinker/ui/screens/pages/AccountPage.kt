package pl.mateusz.blinker.ui.screens.pages

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kotlinx.coroutines.launch
import pl.mateusz.blinker.modules.storage.Account
import pl.mateusz.blinker.modules.storage.DataStorage
import pl.mateusz.blinker.modules.utils.getMainAccountID
import pl.mateusz.blinker.modules.utils.setMainAccountID
import pl.mateusz.blinker.ui.theme.BlinkerTheme

@Composable
fun AccountPage(
    context: Context = LocalContext.current,
    storage: DataStorage = DataStorage.getInstance(),
    navigateToLoginPage: () -> Unit
) {

    val scope = rememberCoroutineScope()

    val currMainId = remember { mutableIntStateOf(context.getMainAccountID()) }
    val accounts = remember { mutableStateOf<Array<Account>>(emptyArray()) }

    LaunchedEffect(context) {
        Thread {
            accounts.value = storage.db.accounts().getAllAccounts()

            Log.d("a",accounts.value.size.toString())
        }.start()
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Button(onClick = navigateToLoginPage) {
                Text(text = "Add new account")
            }
        }
        items(accounts.value) { account ->

            val showPopup = remember { mutableStateOf(false) }
            val expanded = remember { mutableStateOf(false) }
            val isMain = currMainId.intValue == account.uid


            // This thing is a spaghetti
            // Dropdown menu explanations on each button

            // TODO maybe add something later so dropdown is under the clicked area and not on the left
            Box {
                AccountItem(
                    highlighted = isMain,
                    account = account,
                    onClick = {
                        expanded.value = true
                    })

                DropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }) {


                    DropdownMenuItem(
                        enabled = !isMain,
                        leadingIcon = {
                                      Icon(Icons.Default.Star, contentDescription = "main")
                        },
                        text = {
                            Text(text = "Set as main account")
                        },
                        onClick = {
                            context.setMainAccountID(account.uid) // sets mainAcc in prefs
                            currMainId.intValue = account.uid // sets mainAcc in composable so it will refresh
                            expanded.value = false

                        }
                    )
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(Icons.Default.Edit, contentDescription = "edit name")
                        },
                        text = {
                            Text(text = "Change account name")
                        },
                        onClick = {
                            showPopup.value = true

                        }
                    )
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(Icons.Default.Close, contentDescription = "remove")
                        },
                        text = {
                            Text(text = "Remove account")
                        },
                        onClick = {

                            accounts.value = accounts.value.filter {   // this thing here
                                it.uid != account.uid                           // is for lazycolumn
                            }.toTypedArray()                                    // to refresh

                            currMainId.intValue = -1        // -1 means no main account
                            context.setMainAccountID(-1)    //

                            // TODO i dont know if i can do anything about it,
                            //  studia na inzynierii oprogramowania by sie przydaly chyba
                            Thread {
                                storage.db.accounts().deleteAccount(account)    // self explainatory
                                if (accounts.value.isEmpty()) {
                                    scope.launch {
                                        navigateToLoginPage()
                                    }
                                }
                            }.start()                                           // it will explode if ran on main thread

                        }
                    )

                }
                if(showPopup.value) {
                Popup(
                    alignment = Alignment.Center,
                    onDismissRequest = { showPopup.value = false},
                    properties = PopupProperties(focusable = true)
                ) {
                    val editInput = remember { mutableStateOf("") }
                    Column(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(value = editInput.value, onValueChange = {editInput.value = it})
                        Button(
                            onClick = {
                                account.name = editInput.value
                                Thread {
                                    storage.db.accounts().insertAccount(account)
                                }.start()

                                showPopup.value = false
                            }) {
                            Text(text = "Confirm")

                        }
                    }

                }
                }
            }
        }
    }
}

@Composable
fun AccountItem(
    account: Account = Account("januszexpl@gmail.com", "token"),
    highlighted: Boolean = false,
    onClick: () -> Unit = {}) {

    // TODO use outlined card here https://developer.android.com/develop/ui/compose/components/card
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(
                color = if (highlighted) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.secondary
                }
            )
            .padding(4.dp)
            .clickable(onClick = onClick)
            ,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = account.name,
            fontSize = 6.em,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = account.email,
            color = MaterialTheme.colorScheme.onPrimary
        )

    }

}

@Preview(showBackground = true)
@Composable
fun AccountItemPreview() {
    BlinkerTheme {
        AccountItem()
    }
}

// - dialog / window on clicking the accountItem
//   with options
//      - delete
//      - set as main