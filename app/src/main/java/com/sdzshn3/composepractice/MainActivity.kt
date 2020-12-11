package com.sdzshn3.composepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ActivityUi()
        }
    }

    @Composable
    private fun ActivityUi() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        val text: String by viewModel.string.observeAsState("Text you write appears here")
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "LiveData TextView example",
                fontSize = TextUnit.Companion.Sp(20)
            )
            Spacer()
            Text(text = text)
            Spacer()
            OutlinedTextField(
                value = text,
                onValueChange = { s ->
                    println(s)
                    viewModel.changeString(s)
                },
                label = {
                    Text(text = "Type anything")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(4.dp)
            Button(onClick = {
                viewModel.changeString("Zeeshan Syed")
            }) {
                Text(text = "Change to Zeeshan Syed")
            }
            Spacer(16.dp)
            Divider(thickness = 2.dp)
            Spacer(16.dp)
            Text(
                text = "RecyclerView example",
                fontSize = TextUnit.Companion.Sp(20)
            )
            val items = listOf(
                "One", "Two", "Three", "Four", "Five",
                "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
                "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty",
                "Twenty one", "Twenty Two", "Twenty Three", "Twenty Four", "Twenty Five",
                "Twenty Six", "Twenty Seven", "Twenty Eight", "Twenty Nine", "Thirty"
            )
            LazyColumnFor(
                items = items,
            ) { item: String ->
                Card(
                    modifier = Modifier.padding(4.dp),
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.clickable(
                            onClick = {
                                Toast.makeText(this@MainActivity, item, Toast.LENGTH_SHORT)
                                    .show()
                            })
                    ) {
                        Text(
                            text = item,
                            fontSize = TextUnit.Companion.Sp(18),
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(10.dp).fillMaxWidth()
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun Spacer(top: Dp = 4.dp) {
        Spacer(modifier = Modifier.padding(top = top))
    }
}