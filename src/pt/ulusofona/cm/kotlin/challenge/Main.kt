package pt.ulusofona.cm.kotlin.challenge

import java.util.*

fun main(){
    print("ola")
    var pos = models.Posicao()
    print(pos)
    pos.alterarPosicao(3,4)
    print(pos)
    var bicla = models.Bicicleta("kappa",models.Posicao(2,3), models.Data("01","02","22"))
    println(bicla)

}