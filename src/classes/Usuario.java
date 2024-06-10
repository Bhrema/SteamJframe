package classes;


public abstract class Usuario {
    public String nome;
    public String email;
    protected CarrinhoDeCompras carrinho;
    protected Biblioteca biblioteca;

    protected Usuario(String nome, String email, CarrinhoDeCompras carrinho, Biblioteca biblioteca) {
        this.nome = nome;
        this.email = email;
        this.carrinho = carrinho;
        this.biblioteca = biblioteca;
    }

    public abstract void enviarEmail();
}