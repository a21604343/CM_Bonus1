package pt.ulusofona.cm.kotlin.challenge
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap
class models {

    data class Data(
        var dia : String,
        var mes : String,
        var ano : String){

        override fun toString(): String {
            return "$dia-$mes-$ano"
        }
    }

    data class Pessoa(
        var nome : String,
        var veiculos : HashMap<String,Veiculo>,
        var dataDeNascimento : Data,
        var carta : Carta,
        var posicao: Posicao
    ){

        fun comprarVeiculo(veiculo : Veiculo){
            if (!veiculos.containsValue(veiculo)){
                veiculos.put(veiculo.identificador,veiculo)
            }
        }
        fun pesquisarVeiculo(identificador : String) : Veiculo {
                if(veiculos.containsKey(identificador)){
                    return veiculos.get(identificador)!!
                }

                throw exceptions.VeiculoNaoEncontradoException(identificador)


        }
        fun venderVeiculo ( identificador: String, comprador : Pessoa){


                val newVeiculo = veiculos[identificador]
                if (newVeiculo != null) {
                    veiculos.remove(identificador)
                    comprador.veiculos.put(identificador,newVeiculo)
                }else{
                    throw exceptions.VeiculoNaoEncontradoException(identificador)

            }




        }
        fun moverVeiculoPara(identificador: String , x : Int, y : Int){
            veiculos[identificador]?.posicao?.alterarPosicao(x,y)

        }
        fun temCarta(valor : Boolean) : Boolean {
            return tirarCarta()
        }
        fun tirarCarta() : Boolean{
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.BASIC_ISO_DATE
            val formatted = current.format(formatter)

            if (formatted.removeRange(4,8) .toInt() - dataDeNascimento.ano.toInt() == 18 ){
                var mes = formatted.removeRange(0,4)

                if(mes.removeRange(6,8).toInt() > dataDeNascimento.mes.toInt() ){
                    return temCarta(true)
                }
                if(mes.removeRange(6,8).toInt() == dataDeNascimento.mes.toInt()){
                    if (mes.removeRange(4,6).toInt() > dataDeNascimento.dia.toInt()){
                        return temCarta(true)
                    }
                }
            }
            return temCarta(false)

        }

        override fun toString(): String {
            return "Pessoa | $nome | $dataDeNascimento | $posicao"
        }
    }
    open class Veiculo(
        var identificador : String,
        var posicao : Posicao,
        var dataDeAquisicao : Data
    ){
        fun requerCarta() : Boolean{
            return true
        }
    }
    data class Posicao(
        var x : Int = 0,
        var y : Int = 0
    ){
        fun alterarPosicao(xNew : Int, yNew : Int){
            x = xNew
            y = yNew
        }
        override fun toString(): String {
            return "Posicao | x:${x} | y:${y}"
        }
    }

     class Carro(identificador: String, posicao: Posicao, date: Data,var motor: Motor) : Veiculo(identificador,posicao,date){


        override fun toString(): String {
            return "Carro | $identificador | $dataDeAquisicao | $posicao"
        }
    }

    class Bicicleta(identificador: String, posicao: Posicao,date: Data) : Veiculo(identificador,posicao,date){

        override fun toString(): String {
            return "Bicicleta | $identificador | $dataDeAquisicao | $posicao"
        }
    }

    class Carta{

    }

    data class Motor(
        var cavalos : Int,
        var cilindrada : Int,
        var ligado : Boolean
    ){
        override fun toString(): String {
            return "Motor | $cavalos | $cilindrada"
        }

    }






}