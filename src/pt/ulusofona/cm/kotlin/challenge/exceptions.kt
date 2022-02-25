package pt.ulusofona.cm.kotlin.challenge

class exceptions  {


    class AlterarPosicaoException(x : Int, y: Int) : Exception("Já se encontra na posicao: $x | $y")
    class MenorDeIdadeException(idade : Int) : Exception("Tem que aguardar mais ${18-idade} para tirar a carta.")
    class PessoaSemCartaException (nome : String) : Exception("$nome não tem carta para conduzir o veículo indicado")
    class VeiculoNaoEncontradoException(id : String) : Exception("O veiculo com o identificador $id não foi encontrado")
    class VeiculoDesligadoException () : Exception("Este veiculo já se encontra desligado")
    class VeiculoLigadoException () : Exception("Este veiculo ja se encontra ligado")
}