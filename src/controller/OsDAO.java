/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Os;
import java.awt.HeadlessException;

/**
 *
 * @author Pedro
 */
public class OsDAO {
    String consultarClientesNome = "select idcli as id, nomecli as nome, fonecli as fone from tbclientes where nomecli like ?";
    String listarClientes = "SELECT * FROM tbclientes";

    
    String emitirOS = "insert into tbos(tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcli) values(?,?,?,?,?,?,?,?)";
    
    
        Connection conexao = null;
    PreparedStatement ps;
    ResultSet rs;
    
        public List<Cliente> listarClientes(){
        try {
            List<Cliente> lista = new ArrayList<>();
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(listarClientes);
            rs = ps.executeQuery();
            
            while(rs.next()){
                Cliente obj = new Cliente();
                
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setTelefone(rs.getString(4));
                
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

    public List<Cliente> consultarClientes(String nome){
        try {
            List<Cliente> lista = new ArrayList<>();
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(consultarClientesNome);
            ps.setString(1, nome);
            
            rs = ps.executeQuery();
            
            
            
            while(rs.next()){
                Cliente obj = new Cliente();
                obj.setId(rs.getInt(1));
                obj.setNome(rs.getString(2));
                obj.setTelefone(rs.getString(3));
                lista.add(obj);
                
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

public void emitirOS(Os obj) {
        try {
            
            conexao = ModuloConexao.conectar();
            ps = conexao.prepareStatement(emitirOS);

            ps.setString(1, obj.getTipo());
            ps.setString(2, obj.getSituacao());
            ps.setString(3, obj.getEquipamento());
            ps.setString(4, obj.getDefeito());
            ps.setString(5, obj.getServico());
            ps.setString(6, obj.getTecnico());
            ps.setDouble(7, obj.getValor());
            ps.setInt(8, obj.getCliente().getId());

            ps.execute();
            ps.close();
            JOptionPane.showMessageDialog(null, "OS emitida com Sucesso!");
        
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
