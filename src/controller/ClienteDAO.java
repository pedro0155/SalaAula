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
import javax.swing.JOptionPane;

public class ClienteDAO {
    String cadastrarCliente = "insert into tbclientes(nomecli,endcli,fonecli,emailcli) values(?,?,?,?)";
    
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
    }


