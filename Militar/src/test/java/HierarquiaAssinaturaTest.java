import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HierarquiaAssinaturaTest {
    private Soldado soldado;
    private Sargento sargento;
    private Tenente tenente;
    private Capitao capitao;
    private Coronel coronel;
    private General general;
    private Documento documento;

    @BeforeEach
    public void setUp() {
        soldado = new Soldado("João");
        sargento = new Sargento("Pedro");
        tenente = new Tenente("Lucas");
        capitao = new Capitao("Mateus");
        coronel = new Coronel("Marcos");
        general = new General("José");

        // Configurar a hierarquia
        soldado.setSuperior(sargento);
        sargento.setSuperior(tenente);
        tenente.setSuperior(capitao);
        capitao.setSuperior(coronel);
        coronel.setSuperior(general);

        // Criar um documento
        documento = new Documento("Plano de Operações 2025");
    }

    @Test
    public void testAssinaturaNaOrdemCorreta() {
        soldado.assinarDocumento(documento);
        assertTrue(documento.isAssinadoPor("João"), "Soldado João deveria ter assinado o documento");

        sargento.assinarDocumento(documento);
        assertTrue(documento.isAssinadoPor("Pedro"), "Sargento Pedro deveria ter assinado o documento");

        tenente.assinarDocumento(documento);
        assertTrue(documento.isAssinadoPor("Lucas"), "Tenente Lucas deveria ter assinado o documento");

        capitao.assinarDocumento(documento);
        assertTrue(documento.isAssinadoPor("Mateus"), "Capitão Mateus deveria ter assinado o documento");

        coronel.assinarDocumento(documento);
        assertTrue(documento.isAssinadoPor("Marcos"), "Coronel Marcos deveria ter assinado o documento");

        general.assinarDocumento(documento);
        assertTrue(documento.isAssinadoPor("José"), "General José deveria ter assinado o documento");

        assertEquals(6, documento.getAssinaturas().size(), "O documento deveria ter 6 assinaturas");
    }

    @Test
    public void testAssinaturaForaDaOrdem() {
        general.assinarDocumento(documento);
        assertFalse(documento.isAssinadoPor("José"), "General José não deveria assinar sem assinaturas anteriores");

        soldado.assinarDocumento(documento);
        assertTrue(documento.isAssinadoPor("João"), "Soldado João deveria ter assinado o documento");

        general.assinarDocumento(documento);
        assertFalse(documento.isAssinadoPor("José"), "General José não deveria assinar sem assinaturas intermediárias");

        sargento.assinarDocumento(documento);
        tenente.assinarDocumento(documento);
        capitao.assinarDocumento(documento);
        coronel.assinarDocumento(documento);
        general.assinarDocumento(documento);
        assertTrue(documento.isAssinadoPor("José"), "General José deveria assinar após todas as assinaturas anteriores");
    }

    @Test
    public void testEncadeamentoQuandoNaoPodeAssinar() {
        // Sargento não pode assinar porque Soldado não assinou, então encaminha ao Tenente
        sargento.assinarDocumento(documento);
        assertFalse(documento.isAssinadoPor("Pedro"), "Sargento Pedro não deveria assinar sem a assinatura do Soldado");
        assertFalse(documento.isAssinadoPor("Lucas"), "Tenente Lucas não deveria assinar sem a assinatura do Sargento");

        // Soldado assina, agora Sargento pode assinar
        soldado.assinarDocumento(documento);
        sargento.assinarDocumento(documento);
        assertTrue(documento.isAssinadoPor("João"), "Soldado João deveria ter assinado o documento");
        assertTrue(documento.isAssinadoPor("Pedro"), "Sargento Pedro deveria ter assinado o documento");
    }

    @Test
    public void testEncadeamentoAteOTopo() {
        // Tenta assinar a partir do Soldado sem nenhuma assinatura prévia
        soldado.assinarDocumento(documento);
        assertTrue(documento.isAssinadoPor("João"), "Soldado João deveria ter assinado o documento");
        assertEquals(1, documento.getAssinaturas().size(), "Apenas o Soldado deveria ter assinado");

        // Tenta assinar a partir do General sem assinaturas prévias
        Documento novoDocumento = new Documento("Relatório de Missão");
        general.assinarDocumento(novoDocumento);
        assertFalse(novoDocumento.isAssinadoPor("José"), "General José não deveria assinar sem assinaturas anteriores");
        assertTrue(novoDocumento.getAssinaturas().isEmpty(), "O documento não deveria ter assinaturas");
    }

    @Test
    public void testDocumentoSemAssinaturas() {
        assertTrue(documento.getAssinaturas().isEmpty(), "O documento deveria iniciar sem assinaturas");
    }

    @Test
    public void testAssinaturaDuplicada() {
        soldado.assinarDocumento(documento);
        soldado.assinarDocumento(documento); // Tentativa de assinar novamente
        assertEquals(1, documento.getAssinaturas().size(), "O documento deveria ter apenas uma assinatura do Soldado");
        assertTrue(documento.isAssinadoPor("João"), "Soldado João deveria ter assinado exatamente uma vez");
    }
}