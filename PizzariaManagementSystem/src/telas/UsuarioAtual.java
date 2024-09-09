/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package telas;

/**
 *
 * @author João Victor
 */
public class UsuarioAtual {

    private static UsuarioAtual instance;
    private int idUsuario;
    private String username;

    private UsuarioAtual(int idUsuario, String username){
        this.idUsuario = idUsuario;
        this.username = username;
    }
    
    public static void iniciarSessao(int idUsuario, String username) {
        if (instance == null) {
            instance = new UsuarioAtual(idUsuario, username);            
        }     
        
    }
    
    // Método para retornar a sessão do usuário
    public static UsuarioAtual getSession() {
        return instance;
    }

    // Método para encerrar a sessão do usuário
    public static void endSession() {
        instance = null;
    }

    // Método para pegar o ID do usuário
    public int getIdUsuario() {
        return idUsuario;
    }

    // Método para pegar o nome do usuário
    public String getUsername() {
        return username;
    }

}
