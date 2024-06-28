/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.HeadlessException;
import model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ClienteDAO {
    String cadastrarCliente = "insert into tbclientes(nomecli,endcli,fonecli,emailcli) values(?,?,?,?)";
    String consultarClientesNome = "select idcli as id, nomecli as nome, endcli as endereço, fonecli as fone, emailcli as email from tbclientes where nomecli like ?";
    String listarClientes = "SELECT * FROM tbclientes";
    
    Connection conexao = null;
    PreparedStatement ps;
    ResultSet rs;
    
    public void cadastrarUsuario(Cliente obj) {
        try {
            //String cadastrarCliente = "insert into tbclientes(nomecli,endcli,fonecli,emailcli) values(?,?,?,?)";
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(cadastrarCliente);
           
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getEndereco());
            ps.setString(3, obj.getTelefone());
            ps.setString(4, obj.getEmail());
            
           
            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com Sucesso!");
            
  //      } catch (SQLIntegrityConstraintViolationException e1) {
    //        JOptionPane.showMessageDialog(null, "Email já existente.\nEscolha outro email.");
            
      
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }
    
    public List<Cliente> consultarClientes(String nome){
        try {
            List<Cliente> lista = new ArrayList<>();
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(consultarClientesNome);
            ps.setString(1, nome);
            
            rs = ps.executeQuery();
            Cliente obj = new Cliente();
            
            
            if(rs.next()){
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setEndereco(rs.getString(3));
                obj.setTelefone(rs.getString(4));
                obj.setEmail(rs.getString(5));
                
                
            }
            return lista;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado!!!");
            return null;
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
   
    
}
    
    public List<Cliente> listarClientes(){
        try {
            List<Cliente> lista = new ArrayList<>();
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(listarClientes);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Cliente obj = new Cliente();
                
                obj.setId(rs.getInt("idcli"));
                obj.setNome(rs.getString("nomecli"));
                obj.setEndereco(rs.getString(3));
                obj.setTelefone(rs.getString(4));
                obj.setEmail(rs.getString(5));
                
                lista.add(obj);
            }
            return lista;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Cliente não cadastrado!!!");
            return null;
       } 
        finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
   
    }
    
    
    
    
    
    }


