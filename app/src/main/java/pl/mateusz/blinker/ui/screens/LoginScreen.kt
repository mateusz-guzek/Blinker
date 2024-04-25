package pl.mateusz.blinker.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import pl.mateusz.blinker.ui.theme.BlinkerTheme

@Composable
fun LoginScreen(
    context: Context = LocalContext.current,
    onLoginNav: () -> Unit
) {

    val login = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
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
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Login to your Baselinker account below in order to see your " +
                            "sales and more!",
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = login.value,
                    onValueChange = { login.value = it },
                    label = { Text(text = "E-mail")},
                    modifier = Modifier
                        .fillMaxWidth()
                )
                OutlinedTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text(text = "Password")},
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }
            Button(
                onClick = {

                    // TODO just make it a login to a baselinker account with login+password, if
                    // TODO navigate to main screen if it retrieves session token
                    if (false) {

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



@Preview(showSystemUi = true)
@Composable
fun LoginPreview() {
    BlinkerTheme {
        LoginScreen {

        }
    }
}