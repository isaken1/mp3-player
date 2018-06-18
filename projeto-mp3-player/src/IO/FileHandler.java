package IO;

import App.Usuario;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 *
 * @author Isaac Kennedy
 * @author Ian Honorato
 * @version 1.0
 * Esta classe tem como função ler e/ou inicializar os arquivos e pastas requisitados pela aplicação.
 */
public class FileHandler {

    /**  String que guardará o caminho para a pasta home do usuário */
    private String userHome;
    /** Pasta base da aplicação */
    private File baseFolder;
    /** Arquivo que contém os endereços das músicas */
    private File songs;
    /** Arquivo que contém as informações dos usuários */
    private File users;
    /** Arquivo que guarda as playlists */
    private File playlists;

    private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public FileHandler() throws IOException {
        userHome = System.getProperty("user.home");
        baseFolder = new File(userHome + "\\mp3-player");
        if (!baseFolder.exists()) {
            if (baseFolder.mkdir()) {
                System.out.println("Diretório criado!");
            } else {
                throw new IOException("Não foi possível criar o diretório.");
            }
        }

        songs = new File(baseFolder.getPath() + "\\musicas.xml");
        users = new File(baseFolder.getPath() + "\\usuarios.xml");
        playlists = new File(baseFolder.getPath() + "\\playlists.xml");
        checkBasicFiles();
    }

    /**
     * Função responsável por checar se os arquivos básicos necessários para o funcionamento da aplicação existem e,
     * em caso negativo, os cria.
     */
    private void checkBasicFiles() {

        try {

            DocumentBuilder builder = factory.newDocumentBuilder();

            if (!songs.exists()) {
                songs.createNewFile();
                System.out.println("Arquivo de músicas criado!");
            }

            if (!users.exists()) {
                users.createNewFile();
                Usuario usuario = new Usuario("admin", "admin");
                System.out.println("Arquivo de usuários criado!");
            }

            if (!playlists.exists()) {
                playlists.createNewFile();
                System.out.println("Arquivo de playlists criado!");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível criar o arquivo.");
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
            System.out.println("");
        }
    }

    /**
     * Função responsável por inserir um novo usuário no XML de usuários.
     * @param u Usuário a ser inserido.
     */
    private void inserirUsuario(Usuario u) {
        try {

            //Instancia um Document para que seja escrito um XML
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            //Cria a tag raiz e as tags que serão inseridas nas raízes
            Element tagUsuario = doc.createElement("Usuario");
            Element tagNome = doc.createElement("nome");
            Element tagSenha = doc.createElement("senha");

            //Insere o conteúdo da classe usuário nas tags
            tagNome.setTextContent(u.getNome());
            tagSenha.setTextContent(u.getSenha());

            //Insere as subtags nome e senha dentro da tag de usuario
            tagUsuario.appendChild(tagNome);
            tagUsuario.appendChild(tagSenha);

            //Converte o documento para string e escreve no arquivo
            String arquivo = converterDocument(doc);
            salvarArquivo(arquivo, users);
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
            System.err.println("Falha no processamento do XML.");
        } catch (TransformerException ex) {
            ex.printStackTrace();
            System.err.println("Falha na conversão do XML.");
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Falha na escrita do arquivo.");
        }

    }

    /**
     * Função responsáavel por converter um objeto da classe Document para String.
     * @param doc Document a ser convertido.
     * @return String formatada no estilo XML gerada a partir do Document;
     * @throws TransformerException
     */
    private String converterDocument(Document doc) throws TransformerException {
        //Instancia o objeto da classe Transformer, responsável por converter o documento DOM e inserir
        // o resultado desse processamento na StreamResult.
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamResult result = new StreamResult(new StringWriter());
        DOMSource source = new DOMSource(doc);
        transformer.transform(source, result);

        //Pega a string resultante do processamento anterior, que no caso é o próprio XML, e a imprime.
        String xmlString = result.getWriter().toString();
        System.out.println(xmlString);

        return xmlString;
    }

    /**
     * Função responsável por escrever no arquivo proopriamente dito, através de um FileOutputStream.
     * @param documento String formatada que será escrita no arquivo.
     * @param f Arquivo-destino no qual a string será escrita.
     * @throws IOException
     */
    private void salvarArquivo(String documento, File f) throws IOException {
        //Abre a stream no modo de concatenação.
        FileOutputStream outputStream = new FileOutputStream(f, true);
        //Escreve na stream.
        outputStream.write(documento.getBytes());
        //Limpa e fecha a stream
        outputStream.flush();
        outputStream.close();
    }
}
