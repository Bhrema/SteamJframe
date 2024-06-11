package classes;


public abstract class Usuario {
    public String nome;
    public String email;
    protected CarrinhoDeCompras carrinho;
    protected Biblioteca biblioteca;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CarrinhoDeCompras getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoDeCompras carrinho) {
        this.carrinho = carrinho;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    protected Usuario(String nome, String email, CarrinhoDeCompras carrinho, Biblioteca biblioteca) {
        this.nome = nome;
        this.email = email;
        this.carrinho = carrinho;
        this.biblioteca = biblioteca;
    }

    public abstract void enviarEmail();
}

