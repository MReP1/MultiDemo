package little.goose.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import little.goose.common.model.Cat
import little.goose.common.network.CatSource

@Composable
fun App() {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Get请求", style = TextStyle(fontSize = 24.sp))
        var catForGet by remember { mutableStateOf(Cat("没有猫咪", "快Get获取猫咪", "没有URL")) }
        Button(onClick = {
            scope.launch {
                CatSource.getRandomCat().onSuccess { catForGet = it }
            }
        }) {
            Text("Get随机猫咪")
        }
        Text(catForGet.toString())

        Spacer(Modifier.height(32.dp))

        Text("Post请求", style = TextStyle(fontSize = 24.sp))
        var catForPost by remember { mutableStateOf(Cat("没有猫咪", "快Post获取猫咪", "没有URL")) }
        var catNumber by remember { mutableStateOf("1") }
        OutlinedTextField(
            value = catNumber,
            onValueChange = {
                catNumber = it
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Button(onClick = {
            scope.launch {
                CatSource.postSpecialCat(catNumber.toIntOrNull() ?: 0).onSuccess {
                    catForPost = it
                }.onFailure {
                    println("获取猫咪失败！请检查参数。")
                }
            }
        }) {
            Text("Post特定猫咪")
        }
        Text(catForPost.toString())
    }
}
