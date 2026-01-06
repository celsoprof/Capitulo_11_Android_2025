package br.com.fiap.meucapital.ui.juros

import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class JurosViewModelTest {

    private lateinit var viewModel: JurosViewModel

    @Before
    fun setup(){
        viewModel = JurosViewModel()
    }

    @Test
    fun calcularJurosSimples_deveCalcularJurosCorretamente() {
        // Dados de entrada (Arrange)
        val capital = 1000.0
        val taxa = 10.0
        val tempo = 12

        // Executar a função (Act)
        viewModel.calcularJurosSimples(
            capital = capital,
            taxa = taxa,
            tempo = tempo
        )

        // Verificar se resultados estão corretos (Assert)
        assertEquals(1200.0, viewModel.juros)
        assertNull(viewModel.erro)

    }

}