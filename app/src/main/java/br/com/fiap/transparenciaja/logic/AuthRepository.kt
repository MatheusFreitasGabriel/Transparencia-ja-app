package br.com.fiap.transparenciaja.logic

data class Usuario(
    val nome: String,
    val email: String,
    val senha: String,
)

object AuthRepository {
    private val usuariosCadastrados = mutableListOf<Usuario>()

    fun singUpValidation(nome: String, email: String, senha: String): Pair<Boolean, String>{
        return when {
            nome.isBlank() || email.isBlank() || senha.isBlank() -> Pair(false, "Todos os campos são obrigatórios.")

            usuariosCadastrados.any { it.email == email } -> Pair(false, "Este e-mail já está cadastrado.")

            usuariosCadastrados.any { it.nome == nome } -> Pair(false, "Este nome de usuário já está em uso.")

            else -> {
                usuariosCadastrados.add(Usuario(nome, email, senha))
                Pair(true, "Conta criada com sucesso!")
            }
        }
    }

    fun loginValidation(email: String, senha: String): Pair<Boolean, String> {
        return if (usuariosCadastrados.any { it.email == email && it.senha == senha }) {
            Pair(true, "Login realizado com sucesso!")
        } else {
            Pair(false, "E-mail ou senha incorretos.")
        }
    }
}