package mayckgomes.com.projectgym.Screens.Training

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import mayckgomes.com.projectgym.R
import mayckgomes.com.projectgym.funcs.System.MediaDaysTraining
import mayckgomes.com.projectgym.funcs.SystemTimer
import mayckgomes.com.projectgym.funcs.SystemTimer.time
import mayckgomes.com.projectgym.funcs.UserData.AddMediaTimeTraining
import mayckgomes.com.projectgym.funcs.UserData.GetMediaTimeTraining
import mayckgomes.com.projectgym.ui.Components.StyledText
import mayckgomes.com.projectgym.ui.theme.Black
import mayckgomes.com.projectgym.ui.theme.Yellow

@Composable
fun FinalTrainingScreen(navController: NavController,nomeTraining: String){

    var timeTraining by rememberSaveable {
        mutableStateOf(0)
    }

    var isClicked by rememberSaveable {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {

        timeTraining = time.value

        SystemTimer.clear()

    }

    Scaffold(

        bottomBar = {

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(0.dp,0.dp,5.dp,50.dp)
                    .fillMaxWidth()
            ) {

                Button(
                    onClick = {

                        isClicked = true

                        navController.popBackStack()
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Yellow
                    )
                    ) {
                    StyledText("voltar ao menu", color = Black)
                }

            }

        }

    ) {paddingValues ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(color = Black)
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            Text("Parabéns!!", fontWeight = FontWeight.Bold, fontSize = 25.sp, color = MaterialTheme.colorScheme.surface)

            Spacer(Modifier.size(15.dp))

            StyledText("Você chegou ao fim do treino: $nomeTraining !")

            Spacer(Modifier.size(10.dp))

            StyledText("Você terminou em \n${timeTraining / 3600} horas, ${(timeTraining % 3600) / 60} minutos e ${timeTraining % 60} segundos")

            Spacer(Modifier.size(30.dp))

            Image(painter = painterResource(R.drawable.personruning), contentDescription = null,Modifier.size(400.dp))

        }

        if (isClicked){

            isClicked = false

            AddMediaTimeTraining(timeTraining)

            MediaDaysTraining = GetMediaTimeTraining()

        }

    }
}