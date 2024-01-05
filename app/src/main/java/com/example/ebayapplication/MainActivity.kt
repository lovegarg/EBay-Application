package com.example.ebayapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.ebayapplication.ui.theme.EBayApplicationTheme
import com.example.ebayapplication.viewModel.EarthquakeViewModel

/**
 *
 * http://api.geonames.org/earthquakesJSON?formatted=true&north=44.1&south=-9.
 * 9&east=-22.4&west=55.2&username=mkoppelman
 *  "eqid": "c0001xgp",
 *     "magnitude": 8.8,
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EBayApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }

        val viewModel = ViewModelProvider(this).get(EarthquakeViewModel::class.java)
        viewModel.getData.observe(this, Observer {newData ->
            Log.e("Test", "newData "+newData.size)
        })

        viewModel.fetchData()

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EBayApplicationTheme {
        Greeting("Android")
    }
}