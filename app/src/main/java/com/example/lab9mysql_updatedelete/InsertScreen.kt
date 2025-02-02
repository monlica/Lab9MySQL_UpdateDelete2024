package com.example.lab9mysql_updatedelete

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@Composable
fun InsertScreen(navController: NavHostController) {
    var textFieldID by remember { mutableStateOf("") }
    var textFieldName by remember { mutableStateOf("") }
    var textFieldAge by remember { mutableStateOf("") }
    ///// เพิ่มตัวแปร
    val contextForToast = LocalContext.current
    val createClient = StudentAPI.create()
    var gender by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Insert New Student",
            fontSize = 25.sp
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = textFieldID,
            onValueChange = { textFieldID = it },
            label = { Text("Student ID") }
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = textFieldName,
            onValueChange = { textFieldName = it },
            label = { Text("Student name") }
        )
        // ให้นักศึกษาเพิ่มคำสั่งสร้าง RadioButton และช่องกรอกอายุ
        gender =  KindRadioGroupUsage()
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            value = textFieldAge,
            onValueChange = { textFieldAge = it },
            label = { Text("Student age") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number)
        )
        //
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        { Button(modifier = Modifier
                .width(130.dp),
                onClick = {
                    //ให้นักศึกษาเพิ่มคำสั่งในการ Insert ข้อมูลเข้า database
                    createClient.insertStd(
                        textFieldID, textFieldName, gender, textFieldAge.toInt()
                    ).enqueue(object : Callback<Student> {
                        override fun onResponse(
                            call: Call<Student>,response: Response<Student>
                        ) {
                            if (response.isSuccessful) {
                                Toast.makeText(contextForToast,"Successfully Inserted",
                                    Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(contextForToast, "Inserted Failed",
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                        override fun onFailure(call: Call<Student>, t: Throwable) {
                            Toast.makeText(contextForToast,"Error onFailure " +
                                    t.message, Toast.LENGTH_LONG).show()
                        }
                    })
                    navController.navigateUp()
                }) { Text("Save")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(modifier = Modifier
                .width(130.dp),
                onClick = {
                    textFieldID=""
                    textFieldName = ""

                    navController.navigate(Screen.Home.route)

                }) {
                Text("Cancel")
            }
        }
    }
}

@Composable
fun KindRadioGroupUsage():String {
    val kinds = listOf("Male", "Female", "Other")
    val (selected, setSelected) = remember { mutableStateOf("") }
    Text(
        text = "Student Gender :",
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp, top = 10.dp)
    )
    Row (modifier = Modifier.fillMaxWidth()
        .padding(start = 16.dp)){
        MyRadioGroup(
            mItems = kinds,
            selected, setSelected
        )
    }
    return selected
}

@Composable
fun MyRadioGroup(
    mItems: List<String>,
    selected: String,
    setSelected: (selected: String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        mItems.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected == item,
                    onClick = {
                        setSelected(item)
                    },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Green
                    )
                )
                Text(text = item, modifier = Modifier.padding(start = 5.dp))
            }
        }
    }
}