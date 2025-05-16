public class Capitao extends Aprovador {
    public Capitao(String nome) {
        super(nome);
    }

    public void assinarDocumento(Documento documento) {
        if (podeAssinar(documento)) {
            documento.adicionarAssinatura(nome);
        }
    }
}