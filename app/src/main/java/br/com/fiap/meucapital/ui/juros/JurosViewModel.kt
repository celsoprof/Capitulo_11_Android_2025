package br.com.fiap.meucapital.ui.juros

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.com.fiap.meucapital.domain.juros.CalculadoraJurosSimples

class JurosViewModel(
    private val calculadora: CalculadoraJurosSimples = CalculadoraJurosSimples()
) : ViewModel() {

    var montante by mutableStateOf<Double?>(null)
        private set

    var juros by mutableStateOf<Double?>(null)
        private set

    var erro by mutableStateOf<String?>(null)
        private set

    fun calcularJurosSimples(capital: Double, taxa: Double, tempo: Int) {
        try {
            juros = calculadora.calcularJurosSimples(capital, taxa, tempo)
            erro = null
        } catch (e: IllegalArgumentException) {
            erro = e.message
        }
    }

    fun calcularMontante(capital: Double, taxa: Double, tempo: Int) {
        try {
            montante = calculadora.calcularMontante(capital, taxa, tempo)
            erro = null
        } catch (e: IllegalArgumentException) {
            erro = e.message
        }
    }

}