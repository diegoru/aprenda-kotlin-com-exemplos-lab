import Util.Companion.capitalizeWord
import java.util.*

// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel(val desc: String) { BASICO("Básico"), INTERMEDIARIO("Intermediário"), AVANCADO("Avançado") }

enum class TipoConteudo(val desc: String) { CURSO("Curso"), DESAFIO_DE_CODIGO("Desafio de código") }

class Util {
    companion object {
        fun String.capitalizeWord(): String {
            if (isEmpty()) {
                return this
            }
            return substring(0, 1).uppercase(Locale.getDefault()) + substring(1)
        }
    }
}

data class Usuario(
    val id: String = UUID.randomUUID().toString(),
    val nome: String,
    val email: String,
    val telefone: String,
    val senha: String,
) {
    private val usuarios = mutableListOf<Usuario>()
    fun cadastrarUsuario() {
        usuarios.add(this)
        println("Seja bem-vindo(a) ${this.nome.capitalizeWord()} a Dio")
    }
}

data class ConteudoEducacional(
    val id: String = UUID.randomUUID().toString(),
    var nome: String,
    val nivel: Nivel,
    val duracao: Int = 60,
    val tipoConteudo: TipoConteudo,
)

data class Formacao(
    val id: String = UUID.randomUUID().toString(),
    val nome: String,
) {
    private val conteudos = mutableListOf<ConteudoEducacional>()
    private val inscritos = mutableListOf<Usuario>()

    fun adicionarConteudo(conteudoEducacional: ConteudoEducacional) {
        conteudos.add(conteudoEducacional)
        println("${conteudoEducacional.tipoConteudo.desc} ${conteudoEducacional.nome}, adicionado com sucesso!")
    }

    fun verConteudoformacao() {
        println("\n\t## Lista de Conteúdos da Formação ${this.nome.capitalizeWord()} ##\n")
        conteudos.forEach {
            println(" - Curso ${it.nome} | Nível: ${it.nivel.desc} | Duração: ${it.duracao / 60} hrs")
        }
    }

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
        println("Matrícula realizada com sucesso!!! ${usuario.nome.capitalizeWord()} comece agora mesmo sua jornada em ${this.nome}")
    }
}

fun main() {
    val java = Formacao(nome = "Java Developer")
    val introducaoJava =
        ConteudoEducacional(
            nome = "Introdução à Plataforma Java",
            nivel = Nivel.BASICO,
            duracao = 60,
            tipoConteudo = TipoConteudo.CURSO,
        )
    java.adicionarConteudo(introducaoJava)
    val ambienteJava =
        ConteudoEducacional(
            nome = "Ambiente de Desenvolvimento Java",
            nivel = Nivel.BASICO,
            duracao = 60,
            tipoConteudo = TipoConteudo.CURSO,
        )
    java.adicionarConteudo(ambienteJava)
    val aprendendoJava =
        ConteudoEducacional(
            nome = "Aprendendo a Sintaxe Java",
            nivel = Nivel.BASICO,
            duracao = 300,
            tipoConteudo = TipoConteudo.CURSO,
        )
    java.adicionarConteudo(aprendendoJava)
    val desafioFundamentos =
        ConteudoEducacional(
            nome = "Resolvendo desafios - Fundamentos da Linguagem de Programação Java",
            nivel = Nivel.BASICO,
            tipoConteudo = TipoConteudo.DESAFIO_DE_CODIGO,
        )
    java.adicionarConteudo(desafioFundamentos)

    val js = Formacao(nome = "JavaScript Developer")
    val aprendendoVariaveisJs =
        ConteudoEducacional(
            nome = "Imagem do bootcamp Aprendendo Variáveis, Escopo e Tipos de dados no JavaScript",
            nivel = Nivel.INTERMEDIARIO,
            tipoConteudo = TipoConteudo.CURSO,
        )
    js.adicionarConteudo(aprendendoVariaveisJs)
    val op =
        ConteudoEducacional(
            nome = "Orientação a Protótipo com JavaScript",
            nivel = Nivel.AVANCADO,
            tipoConteudo = TipoConteudo.CURSO,
        )
    js.adicionarConteudo(op)

    println("\n\t ----- X -----\n")

    val diego = Usuario(
        nome = "diego",
        email = "diego@email.com",
        telefone = "1198765-4321",
        senha = "root",
    )
    diego.cadastrarUsuario()

    val joao = Usuario(
        nome = "joão",
        email = "joao@email.com",
        telefone = "1198765-4321",
        senha = "root",
    )
    joao.cadastrarUsuario()

    val maria = Usuario(
        nome = "maria",
        email = "maria@email.com",
        telefone = "1198765-4321",
        senha = "root",
    )
    maria.cadastrarUsuario()

    println("\n\t ----- X -----\n")

    java.verConteudoformacao()
    js.verConteudoformacao()

    println("\n\t ----- X -----\n")

    java.matricular(diego)
    java.matricular(joao)
    js.matricular(maria)
}
