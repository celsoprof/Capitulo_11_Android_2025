package br.com.fiap.meucapital

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.com.fiap.meucapital.ui.juros.JurosScreen
import br.com.fiap.meucapital.ui.theme.MeuCapitalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeuCapitalTheme {
                JurosScreen()
            }
        }
    }
}
