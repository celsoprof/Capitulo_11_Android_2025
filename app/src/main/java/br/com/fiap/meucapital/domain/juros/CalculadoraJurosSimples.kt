package br.com.fiap.meucapital.domain.juros

class CalculadoraJurosSimples {

    // Função para calcular juros simples
    fun calcularJurosSimples(capital: Double, taxa: Double, tempo: Int): Double {

        if (capital < 0 || taxa < 0 || tempo < 0) {
            throw IllegalArgumentException(
                "Os valores de capital, taxa e tempo devem ser positivos."
            )
        }
        return capital * (taxa / 100) * tempo
    }

    // Função para calcular montante
    fun calcularMontante(capital: Double, taxa: Double, tempo: Int): Double {
        if (capital < 0 || taxa < 0 || tempo < 0) {
            throw IllegalArgumentException(
                "Os valores de capital, taxa e tempo devem ser positivos."
            )
        }
        return capital + calcularJurosSimples(capital, taxa, tempo)
    }

}