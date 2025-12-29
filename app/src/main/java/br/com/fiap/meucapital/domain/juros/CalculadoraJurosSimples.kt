package br.com.fiap.meucapital.domain.juros

class CalculadoraJurosSimples {

    fun calcularJurosSimples(capital: Double, taxa: Double, tempo: Int): Double {

        if (capital < 0 || taxa < 0 || tempo < 0) {
            throw IllegalArgumentException("Os valores de capital, taxa e tempo devem ser positivos.")
        }
        return capital * (taxa / 100) * tempo
    }

    fun calcularMontante(capital: Double, taxa: Double, tempo: Int): Double {
        if (capital < 0 || taxa < 0 || tempo < 0) {
            throw IllegalArgumentException("Os valores de capital, taxa e tempo devem ser positivos.")
        }
        return capital + calcularJurosSimples(capital, taxa, tempo)
    }

}