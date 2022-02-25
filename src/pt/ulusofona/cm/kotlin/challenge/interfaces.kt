package pt.ulusofona.cm.kotlin.challenge

class interfaces {

    interface Movimentavel {
        fun moverPara(x : Int, y : Int)
    }

    interface Ligavel {
        fun ligar()
        fun desligar()
        fun estaLigado() : Boolean
    }
}