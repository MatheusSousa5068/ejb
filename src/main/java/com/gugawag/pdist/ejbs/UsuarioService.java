package com.gugawag.pdist.ejbs;

import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import java.util.Arrays;
import java.util.List;

@Stateless(name = "usuarioService")
@Remote
public class UsuarioService {

    @EJB
    private UsuarioDAO usuarioDao;

    @EJB
    private MensagemDAO mensagemDao;

    private static final List<String> PALAVROES = Arrays.asList("bobao", "feio", "lerdo");


    public List<Usuario> listar() {
        return usuarioDao.listar();
    }

    public List<Mensagem> listarMensagens() {
        return mensagemDao.listar();
    }

    public void inserir(long id, String nome, long idMensagem, String mensagem) {
        Usuario novoUsuario = new Usuario(id, nome);
        usuarioDao.inserir(novoUsuario);

        Mensagem novaMensagem = new Mensagem(idMensagem, mensagem);
        mensagemDao.inserir(novaMensagem);
        for (String palavrao : PALAVROES) {
            if (mensagem.toLowerCase().contains(palavrao.toLowerCase())) {
                throw new IllegalArgumentException("Mensagem contém palavras inadequadas!");
            }
        }
        if (id==3L) {
            throw new RuntimeException("Menor de idade não permitido!");
        }
        if (id == 4L) {
            novoUsuario.setNome(nome + " alterado");
        }
    }
}
