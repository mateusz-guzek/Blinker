package pl.mateusz.blinker.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController

@Composable
fun LoginTokenScreen(
    context: Context = LocalContext.current,
    onLoginNav: () -> Unit
) {

    val login = remember {
        mutableStateOf("")
    }

    val isTokenLengthValid = remember {
        mutableStateOf(true)
    }
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            modifier = Modifier
                .padding(horizontal = 32.dp)
        ) {
            Column{
                Text(
                    text = "BLinker",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.em,
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Enter your Baselinker API token below in order to see your " +
                            "sales and more!",
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = login.value,
                    onValueChange = {
                        login.value = it
                        if (login.value.length != 80) {
                            isTokenLengthValid.value = false
                        } else {
                            isTokenLengthValid.value = true
                        }
                                    },
                    minLines = 3,
                    maxLines = 3,
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Ascii,
                        autoCorrect = false
                    ),
                    isError = !isTokenLengthValid.value,
                    label = {
                        if (isTokenLengthValid.value) {
                            Text(text = "Your BLToken")
                        } else {
                            Text(text = "Your BLToken - not valid!")
                        }
                    }
                )
            }
            Button(
                onClick = {

                    // TODO just make it a login to a baselinker account with login+password, if
                    // TODO it can fetch api token then it's logged in
                    // TODO change to "login.value.length != 80"
                    if (false) {
                        isTokenLengthValid.value = false
                    } else {
                        //navigateOnLogin()
                        onLoginNav()
                    }

                },
                shape = RoundedCornerShape(10),
                colors = ButtonDefaults.buttonColors(),
                modifier = Modifier
                    .padding(horizontal = 64.dp)
                    .fillMaxWidth()
            ){
                Text(
                    text = "Login",
                    fontWeight = FontWeight.Bold)

            }
        }

    }


}


