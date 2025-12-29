package br.com.fiap.meucapital.ui.juros

import android.icu.util.Currency
import android.icu.util.CurrencyAmount
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.meucapital.ui.theme.MeuCapitalTheme
import java.text.NumberFormat
import java.util.Locale

@Composable
fun JurosScreen(viewModel: JurosViewModel = JurosViewModel()) {

    // Variáveis de estado para os campos de entrada
    var capital by remember { mutableStateOf("") }
    var taxa by remember { mutableStateOf("") }
    var tempo by remember { mutableStateOf("") }

    // Formatador para os números
    val formatador = NumberFormat
        .getNumberInstance(Locale.getDefault())
        .apply {
            maximumFractionDigits = 2
            minimumFractionDigits = 2
        }

    // Objeto para requisição de foco
    val capitalFocusRequester = remember { FocusRequester() }
    val taxaFocusRequester = remember { FocusRequester() }
    val tempoFocusRequester = remember { FocusRequester() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .safeContentPadding()
    ) {
        Text(
            text = "Cálculo de Juros Simples",
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.headlineMedium
        )
        OutlinedTextField(
            value = capital,
            onValueChange = { capital = it },
            label = { Text("Capital (R$)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .focusRequester(capitalFocusRequester)
        )

        OutlinedTextField(
            value = taxa,
            onValueChange = { taxa = it },
            label = { Text("Taxa mensal (%)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .focusRequester(taxaFocusRequester)
        )

        OutlinedTextField(
            value = tempo,
            onValueChange = { tempo = it },
            label = { Text("Tempo (meses)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(tempoFocusRequester)
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    viewModel.calcularJurosSimples(
                        capital = capital.toDoubleOrNull() ?: 0.0,
                        taxa = taxa.toDoubleOrNull() ?: 0.0,
                        tempo = tempo.toIntOrNull() ?: 0
                    )
                    viewModel.calcularMontante(
                        capital = capital.toDoubleOrNull() ?: 0.0,
                        taxa = taxa.toDoubleOrNull() ?: 0.0,
                        tempo = tempo.toIntOrNull() ?: 0
                    )
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF009688)
                )
            ) {
                Text("Calcular")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    capital = ""
                    taxa = ""
                    tempo = ""
                    capitalFocusRequester.requestFocus()
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF5722)
                )
            ) {
                Text("Limpar")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Resumo",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                HorizontalDivider()

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Capital:")
                    Text(
                        text = "R$ ${
                            formatador
                                .format(
                                    capital.toDoubleOrNull() ?: 0.0
                                )
                        }"
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Taxa:")
                    Text(
                        text = "${
                            formatador
                                .format(
                                    taxa.toDoubleOrNull() ?: 0.0)
                        } % ao mês"
                    )

                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Tempo:")
                    Text("$tempo meses")
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Juros:")
                    Text(
                        text = "R$ ${formatador
                            .format(
                                viewModel.juros ?: 0.0)
                        }"
                    )
                }

                HorizontalDivider()
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Montante",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF009688)
                    )
                    Text(
                        text = "R$ ${
                            formatador
                                .format(viewModel.montante ?: 0.0)
                        }",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF009688)
                    )
                }

            }
        }

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun JurosScreenPreview() {
    MeuCapitalTheme() {
        JurosScreen()
    }
}