import java.util.ArrayList;
import java.util.List;

public class Documento {
    private String conteudo;
    private List<String> assinaturas;

    public Documento(String conteudo) {
        this.conteudo = conteudo;
        this.assinaturas = new ArrayList<>();
    }

    public void adicionarAssinatura(String assinatura) {
        assinaturas.add(assinatura);
    }

    public List<String> getAssinaturas() {
        return assinaturas;
    }

    public boolean isAssinadoPor(String nome) {
        return assinaturas.contains(nome);
    }

    @Override
    public String toString() {
        return "Documento: " + conteudo + "\nAssinaturas: " + assinaturas;
    }
}