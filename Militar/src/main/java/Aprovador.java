public abstract class Aprovador {
    protected String nome;
    protected Aprovador superior;
    protected Aprovador inferior;

    public Aprovador(String nome) {
        this.nome = nome;
        this.superior = null;
        this.inferior = null;
    }

    public void setSuperior(Aprovador superior) {
        this.superior = superior;
        if (superior != null) {
            superior.inferior = this;
        }
    }

    public void assinarDocumento(Documento documento) {
        if (podeAssinar(documento)) {
            documento.adicionarAssinatura(nome);
        } else if (superior != null && !documento.isAssinadoPor(nome)) {
            superior.assinarDocumento(documento);
        }
    }

    protected boolean podeAssinar(Documento documento) {
        if (inferior == null) {
            return !documento.isAssinadoPor(nome);
        }
        return documento.isAssinadoPor(inferior.getNome()) && !documento.isAssinadoPor(nome);
    }

    public String getNome() {
        return nome;
    }
}