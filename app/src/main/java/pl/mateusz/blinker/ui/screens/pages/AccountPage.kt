package pl.mateusz.blinker.ui.screens.pages

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import pl.mateusz.blinker.modules.storage.Account
import pl.mateusz.blinker.modules.storage.DataStorage
import pl.mateusz.blinker.modules.utils.getMainAccountID
import pl.mateusz.blinker.ui.theme.BlinkerTheme

@Composable
fun AccountPage(
    context: Context = LocalContext.current,
    storage: DataStorage = DataStorage.getInstance()
) {
    val scope = rememberCoroutineScope()

    val currId = remember { mutableIntStateOf(context.getMainAccountID()) }
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
        items(accounts.value) { account ->
            AccountItem(account = account)


        }
    }
}

@Composable
fun AccountItem(
    account: Account = Account("januszexpl@gmail.com", "token"),
    active: Boolean = false,
    onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(96.dp)

            .clip(RoundedCornerShape(12.dp))
            .background(
                color = if (active) {
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
            text = "FUH Januszex",
            fontSize = 8.em
        )
        Text(
            text = account.email
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