package br.com.fiap.meucapital.ui.juros

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test

class JurosScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun campoCapital_deveAceitarValorDigitado(){

        // Criar a tela que será testada
        composeTestRule.setContent {
            JurosScreen()
        }

        // Simular a digitação
        composeTestRule
            .onNodeWithTag("campoCapital")
            .performTextInput("1000")

        // Verificar o estado
        composeTestRule
            .onNodeWithTag("campoCapital")
            .assertTextContains("1000")

    }

    @Test
    fun aoClicarEmCalcular_deveExibirMontanteEJuroscorretos(){

        // Criar a tela que será testada
        composeTestRule.setContent {
            JurosScreen()
        }

        // Preenche os campos (Action)
        composeTestRule
            .onNodeWithTag("campoCapital")
            .performTextInput("1000")

        composeTestRule
            .onNodeWithTag("campoTaxa")
            .performTextInput("0.55")

        composeTestRule
            .onNodeWithTag("campoTempo")
            .performTextInput("12")

        // Clica no botão Calcular
        composeTestRule
            .onNodeWithTag("botaoCalcular")
            .performClick()

        // Verifica o resultado (Assert)
        composeTestRule
            .onNodeWithTag("textoMontante")
            .assertIsDisplayed()
            .assertTextContains("R$ 1.066,00")
    }

}