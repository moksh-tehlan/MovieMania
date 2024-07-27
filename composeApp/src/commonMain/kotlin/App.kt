import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import navigation.NavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import org.koin.core.context.KoinContext

@Composable
@Preview
fun App() {
    MaterialTheme {
       KoinContext {
           NavGraph()
       }
    }
}