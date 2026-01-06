package br.com.fiap.meucapital.domain.juros

import org.junit.Test
import org.junit.Assert.*

class CalculadoraJurosSimplesTest {

    @Test
    fun deveCalcularJurosSimplesCorretamente(){

        // Criamos o objeto que iremos testar
        val calculadoraJurosSimples = CalculadoraJurosSimples()

        // Executamos a ação que queremos testar
        val resultado = calculadoraJurosSimples.calcularJurosSimples(
            capital = 1000.0,
            taxa = 10.0,
            tempo = 12
        )

        // Aqui verificamos o resultado
        assertEquals(1200.0, resultado, 0.01)

    }

    @Test
    fun deveCalcularMontanteCorretamente(){

        // Criamos o objeto que iremos testar
        val calculadoraJurosSimples = CalculadoraJurosSimples()

        // Executamos a ação que queremos testar
        val resultado = calculadoraJurosSimples.calcularMontante(
            capital = 1000.0,
            taxa = 10.0,
            tempo = 12
        )

        // Aqui verificamos o resultado
        assertEquals(2200.0, resultado, 0.01)

    }

    @Test
    fun deveLancarExcecaoQuandoCapitalForNegativo(){

        // Criamos o objeto que iremos testar
        val calculadoraJurosSimples = CalculadoraJurosSimples()

        assertThrows(IllegalArgumentException::class.java){
            calculadoraJurosSimples.calcularJurosSimples(
                capital = -1000.0,
                taxa = 10.0,
                tempo = 12
            )
        }
    }

}