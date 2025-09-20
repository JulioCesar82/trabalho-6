public class Produto {
    private String idProduto;
    private String nome;
    private String descricao;
    private String valor;
    private String estado;

    public Produto(String idProduto, String nome, String descricao, String valor, String estado) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.estado = estado;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "idProduto: " + idProduto + ", nome: " + nome + ", descricao: " + descricao + ", valor: " + valor + ", estado: " + estado;
    }
}
