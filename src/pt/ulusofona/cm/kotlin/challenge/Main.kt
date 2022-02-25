package pt.ulusofona.cm.kotlin.challenge

import java.util.*
import kotlin.collections.HashMap

fun main(){
    var p1 = models.Pessoa("Diogo", HashMap<String,models.Veiculo>() ,models.Data("01","02","22"),models.Carta(),models.Posicao(1,1))
    var c1 = models.Carro("keke",models.Posicao(1,2),models.Data("21","07","2011"),models.Motor(170,1400,false))
    var c2 = models.Carro("kaka",models.Posicao(1,2),models.Data("21","07","2011"),models.Motor(170,1400,false))
    p1.comprarVeiculo(c1)
    p1.comprarVeiculo(c2)
    //p1.pesquisarVeiculo(c2.identificador)


    var m1 = models.Motor(510,2900,false)
    println(m1)

    for (v in p1.veiculos.values){
        println(v)
    }


    var bicla = models.Bicicleta("kappa",models.Posicao(2,3), models.Data("01","02","22"))


}