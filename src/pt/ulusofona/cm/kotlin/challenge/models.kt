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
    class Carta{
        var id : Int = 0

    }

    data class Pessoa(
        var nome : String,
        var veiculos : HashMap<String,Veiculo>,
        var dataDeNascimento : Data,
        var carta : Carta? = null,
        var posicao: Posicao
    ) : interfaces.Movimentavel{

        fun comprarVeiculo(veiculo : Veiculo){
            if (!veiculos.containsValue(veiculo)){
                veiculo.dataDeAquisicao = Data(LocalDateTime.now().year.toString(),LocalDateTime.now().monthValue.toString(),LocalDateTime.now().dayOfMonth.toString())
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
                    newVeiculo.dataDeAquisicao = Data(LocalDateTime.now().year.toString(),LocalDateTime.now().monthValue.toString(),LocalDateTime.now().dayOfMonth.toString())
                    comprador.veiculos.put(identificador,newVeiculo)
                }else{
                    throw exceptions.VeiculoNaoEncontradoException(identificador)

            }




        }
        fun moverVeiculoPara(identificador: String , x : Int, y : Int){
            if (carta == null){
                throw exceptions.PessoaSemCartaException(nome)
            }
            veiculos[identificador]?.posicao?.alterarPosicao(x,y)

        }
        fun temCarta() : Boolean {
            if (carta != null){
                return true
            }
            return false
        }
        fun tirarCarta() : Boolean{
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.BASIC_ISO_DATE
            val formatted = current.format(formatter)

            if (formatted.removeRange(4,8) .toInt() - dataDeNascimento.ano.toInt() == 18 ){
                var mes = formatted.removeRange(0,4)

                if(mes.removeRange(6,8).toInt() > dataDeNascimento.mes.toInt() ){
                    carta = Carta()
                }
                if(mes.removeRange(6,8).toInt() == dataDeNascimento.mes.toInt()){
                    if (mes.removeRange(4,6).toInt() > dataDeNascimento.dia.toInt()){
                        carta = Carta()
                    }else{
                        throw exceptions.MenorDeIdadeException()
                    }
                }
            }
            throw exceptions.MenorDeIdadeException()

        }

        override fun moverPara(x: Int, y: Int) {
            posicao.alterarPosicao(x,y)
        }

        override fun toString(): String {
            return "Pessoa | $nome | $dataDeNascimento | $posicao"
        }
    }
    open class Veiculo(
        var identificador : String,
        var posicao : Posicao,
        var dataDeAquisicao : Data
    ) : interfaces.Movimentavel{
        fun requerCarta() : Boolean{
            return true
        }

        override fun moverPara(x: Int, y: Int) {
            posicao.alterarPosicao(x,y)
        }
    }
    data class Posicao(
        var x : Int = 0,
        var y : Int = 0
    ){
        fun alterarPosicao(xNew : Int, yNew : Int){
            if(x == xNew && y == yNew){
                throw exceptions.AlterarPosicaoException(x,y)
            }
            x = xNew
            y = yNew
        }
        override fun toString(): String {
            return "Posicao | x:${x} | y:${y}"
        }
    }

     class Carro(identificador: String, posicao: Posicao, date: Data,var motor: Motor) : Veiculo(identificador,posicao,date), interfaces.Ligavel{

         override fun ligar() {
             if (motor.ligado){
                 throw exceptions.VeiculoLigadoException()
             }
             motor.ligado = true
         }

         override fun desligar() {
             if (!motor.ligado){
                 throw  exceptions.VeiculoDesligadoException()
             }
             motor.ligado = false
         }

         override fun estaLigado(): Boolean {
            if (motor.ligado) {
                return true
            }
                return false

         }


        override fun toString(): String {
            return "Carro | $identificador | $dataDeAquisicao | $posicao"
        }
    }

    class Bicicleta(identificador: String, posicao: Posicao,date: Data) : Veiculo(identificador,posicao,date){

        override fun toString(): String {
            return "Bicicleta | $identificador | $dataDeAquisicao | $posicao"
        }
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