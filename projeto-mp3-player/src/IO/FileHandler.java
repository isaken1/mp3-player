package IO;

import App.Musica;
import App.Usuario;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
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
import java.util.ArrayList;
import java.util.Random;

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

    /**
     * Construtor padrão da classe FileHandler. Quando criado um objeto desta classe, o mesmo resgata do SO o diretório
     * home do usuário e cria a pasta base de dados do aplicativo lá, junto dos arquivos de músicas, usuários e
     * playlists.
     */
    public FileHandler() {
        userHome = System.getProperty("user.home");
        baseFolder = new File(userHome + "\\mp3-player");
        if (!baseFolder.exists()) {
            baseFolder.mkdir();
            System.out.println("Não foi possível criar o diretório.");
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

            if (!songs.exists()) {
                songs.createNewFile();
                inicializarMusicas();
                System.out.println("Arquivo de músicas criado!");
            }

            if (!users.exists()) {
                inicializarUsuarios();
                inserirUsuario("admin", "admin", true);
                System.out.println("Arquivo de usuários criado!");
            }

            if (!playlists.exists()) {
                playlists.createNewFile();
                System.out.println("Arquivo de playlists criado!");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Não foi possível criar o arquivo.");
        }

    }

    /**
     * Função responsável por inserir um novo usuário no XML de usuários e gerar seu ID.
     * @param nome Nome do usuário.
     * @param senha Senha do usuário.
     * @param vip Boolean que define se um usuário é vip ou não.
     * @return Retorna true se o usuário for inserido com sucesso, caso contrário, false.
     */
    public boolean inserirUsuario(String nome, String senha, boolean vip) {
        try {
            //Instancia um Document para que seja escrito um XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;
            try {
                doc = builder.parse(users);
            } catch (SAXException ex) {
                ex.printStackTrace();
                return false;
            }
            doc.setXmlStandalone(true);

            //Inicializa uma instancia de Random para gerar um id aleatorio;
            Random idGenerator = new Random();
            int id;

            //Garante que o id não exista;
            while (idExiste(id = idGenerator.nextInt(1000000), doc.getDocumentElement()));

            if (nomeExiste(nome, doc.getDocumentElement())) {
                return false;
            }

            Usuario u = new Usuario(nome, senha, vip, id);

            //Cria a tag raiz e as tags que serão inseridas nas raízes
            Element tagUsuario = doc.createElement("usuario");
            tagUsuario.setAttribute("id", String.valueOf(id));
            Element tagNome = doc.createElement("nome");
            Element tagSenha = doc.createElement("senha");
            Element tagVip = doc.createElement("vip");

            //Insere o conteúdo da classe usuário nas tags
            tagNome.setTextContent(u.getNome());
            tagSenha.setTextContent(u.getSenha());
            tagVip.setTextContent((u.isVip()) ? "1" : "0");

            //Insere as subtags nome e senha dentro da tag de usuario
            tagUsuario.appendChild(tagNome);
            tagUsuario.appendChild(tagSenha);
            tagUsuario.appendChild(tagVip);

            doc.getDocumentElement().appendChild(tagUsuario);

            doc.getDocumentElement().normalize();

            //Converte o documento para string e escreve no arquivo
            String arquivo = converterDocument(doc);

            salvarArquivo(arquivo, users);

            return true;
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

        return false;
    }

    /**
     * Função responsável por inserir um novo usuário no XML de usuários e gerar seu ID.
     * @param musica Arquivo da música
     * @return Retorna true se o usuário for inserido com sucesso, caso contrário, false.
     */
    public boolean inserirMusica(File musica) {
        try {
            //Instancia um Document para que seja escrito um XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc;
            try {
                doc = builder.parse(songs);
            } catch (SAXException ex) {
                ex.printStackTrace();
                return false;
            }
            doc.setXmlStandalone(true);

            Musica m = new Musica(musica);


            //Cria a tag raiz e as tags que serão inseridas nas raízes
            Element tagMusica = doc.createElement("musica");
            Element tagNome = doc.createElement("usuario");
            Element tagCaminho = doc.createElement("caminho");
            //Insere o conteúdo da classe usuário nas tags
            tagNome.setTextContent(m.getNome());
            tagCaminho.setTextContent(m.getCaminho());

            //Insere as subtags nome e senha dentro da tag de usuario
            tagMusica.appendChild(tagNome);
            tagMusica.appendChild(tagCaminho);

            tagMusica.normalize();

            doc.getDocumentElement().appendChild(tagMusica);

            doc.getDocumentElement().normalize();

            //Converte o documento para string e escreve no arquivo
            String arquivo = converterDocument(doc);

            salvarArquivo(arquivo, songs);

            return true;
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

        return false;
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
        return result.getWriter().toString();
    }

    /**
     * Função responsável por escrever no arquivo proopriamente dito, através de um FileOutputStream.
     * @param documento String formatada que será escrita no arquivo.
     * @param f Arquivo-destino no qual a string será escrita.
     * @throws IOException
     */
    private void salvarArquivo(String documento, File f) throws IOException {
        //Abre a stream no modo de concatenação.
        FileOutputStream outputStream = new FileOutputStream(f, false);
        //Escreve na stream.
        outputStream.write(documento.getBytes());
        //Limpa e fecha a stream
        outputStream.flush();
        outputStream.close();
    }

    /**
     * Função responsável por ler o arquivo XML e retornar um ArrayList de usuarios contendos todos os usuários cadastrados.
     * @return Um ArrayList contendo todos os usuários cadastrados.
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws TransformerException
     * @throws IOException
     */
    public ArrayList<Usuario> resgatarUsuarios() throws ParserConfigurationException, SAXException,
            TransformerException, IOException {
        ArrayList<Usuario> usuarios = new ArrayList<>();

        //Inicializa e lê o XML.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(users);
        doc.setXmlStandalone(true);

        //Lê o elemento raiz.
        Element raiz = doc.getDocumentElement();

        //Gera um objeto do tipo NodeList com todas as tags que contem Usuario.
        NodeList listaUsuarios = raiz.getElementsByTagName("usuario");
        //System.out.println(listaUsuarios.item(0).getFirstChild().getTextContent());

        //Percorre a listaUsuarios, cria um objeto do tipo Usuario para cada elemento nessa lista e depois os insere
        //em outra lista que será retornada no fim da função.
        for (int i = 0; i < listaUsuarios.getLength(); i++) {
            Element usuario = (Element) listaUsuarios.item(i).getChildNodes();

            String nome = usuario.getElementsByTagName("nome").item(0).getTextContent();
            String senha = usuario.getElementsByTagName("senha").item(0).getTextContent();
            int id = Integer.parseInt(usuario.getAttribute("id"));
            String sVip = usuario.getElementsByTagName("vip").item(0).getTextContent();
            boolean vip = sVip.equals("1");

            Usuario u = new Usuario(nome, senha, vip, id);
            usuarios.add(u);
        }

        return usuarios;
    }

    public ArrayList<Musica> resgatarMusicas() throws ParserConfigurationException, SAXException, IOException {
        ArrayList<Musica> musicas = new ArrayList<>();

        //Inicializa e lê o XML.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(songs);
        doc.setXmlStandalone(true);

        //Lê o elemento raiz.
        Element raiz = doc.getDocumentElement();

        //Gera um objeto do tipo NodeList com todas as tags que contem Usuario.
        NodeList listaMusicas = raiz.getElementsByTagName("musica");
        //System.out.println(listaUsuarios.item(0).getFirstChild().getTextContent());

        //Percorre a listaUsuarios, cria um objeto do tipo Usuario para cada elemento nessa lista e depois os insere
        //em outra lista que será retornada no fim da função.
        for (int i = 0; i < listaMusicas.getLength(); i++) {
            Element musica = (Element) listaMusicas.item(i).getChildNodes();

            File m = new File(musica.getElementsByTagName("caminho").item(0).getTextContent());
            Musica s = new Musica(m);

            musicas.add(s);
        }

        return musicas;
    }

    /**
     * Função responsável por criar e configuar o arquivo XML de usuários pela primeira vez para que as funções de
     * leitura e escrita funcionem adequadamente.
     */
    private void inicializarUsuarios() {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            Element root = doc.createElement("Usuarios");
            doc.appendChild(root);

            //Converte o documento para string e escreve no arquivo
            String arquivo = converterDocument(doc);

            salvarArquivo(arquivo, users);
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
    }

    private void inicializarMusicas() {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.setXmlStandalone(true);
            Element root = doc.createElement("Musicas");
            doc.appendChild(root);

            //Converte o documento para string e escreve no arquivo
            String arquivo = converterDocument(doc);

            salvarArquivo(arquivo, songs);
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
    }

    private boolean idExiste(int id, Element root) {

        //Gera um objeto do tipo NodeList com todas as tags que contem usuario.
        NodeList listaUsuarios = root.getElementsByTagName("usuario");

        //Percorre a lista e retorna caso ache o ID passado como parâmentro.
        for (int i = 0; i < listaUsuarios.getLength(); i++) {
            Element usuario = (Element) listaUsuarios.item(i);
            if (Integer.parseInt(usuario.getAttribute("id")) ==  id) {
                return true;
            }
        }

        return false;
    }

    private boolean nomeExiste(String nome, Element root) {
        NodeList listaUsuarios = root.getElementsByTagName("usuario");

        for (int i = 0; i < listaUsuarios.getLength(); i++) {
            Element usuario = (Element) listaUsuarios.item(i);
            if (usuario.getAttribute("nome").equals(nome)) {
                return true;
            }
        }

        return false;
    }

}