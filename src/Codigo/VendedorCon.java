package Codigo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class VendedorCon {
    PreparedStatement ps;
    ResultSet rs;
    
    //Usuario us = new Usuario();
    Conexion con = new Conexion();
    Connection acceso;
    
    public Usuario ValidarVendedor(String dni, String user) {
        Usuario u = new Usuario();
        String sql = "select * from vendedor where Dni=? and User=?";
        try {
            acceso = con.Conectar();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, dni);
            ps.setString(2, user);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setDni(rs.getString(2));
                u.setNombres(rs.getString(3));
                u.setTelefono(rs.getString(4));
                u.setEstado(rs.getString(5));
                u.setUsuario(rs.getString(6));
                
            }
            
        } catch (Exception e) {
            
        }
        return u;
    }
    
    public Vendedor listarVendedorId(String dni) {
        Vendedor v = new Vendedor();
        String sql = "select * from vendedor where Dni=" + dni;
        try {
            acceso = con.Conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                v.setId(rs.getInt(1));
                v.setDni(rs.getString(2));
                v.setNom(rs.getString(3));
                v.setTel(rs.getString(4));
                v.setEstado(rs.getString(5));
                v.setUser(rs.getString(6));
            }
        } catch (Exception e) {
        }
        return v;
    }
    
    //CRUD
    public List listarVendedor() {
        String sql = "select * from vendedor";
        List<Vendedor> listaVendedor = new ArrayList<>();
        try {
            acceso = con.Conectar();
            ps = acceso.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Vendedor ven = new Vendedor();
                ven.setId(rs.getInt(1));
                ven.setDni(rs.getString(2));
                ven.setNom(rs.getString(3));
                ven.setTel(rs.getString(4));
                ven.setEstado(rs.getString(5));
                ven.setUser(rs.getString(6));
                listaVendedor.add(ven);
            }
        } catch (Exception e) {
        }
        return listaVendedor;
    }
    
    public int agregar(Vendedor v) {
        int r=0;
        String sql = "insert into vendedor(Dni,Nombres,Telefono,Estado,User)values(?,?,?,?,?)";
        try {
            acceso = con.Conectar();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, v.getDni());
            ps.setString(2, v.getNom());
            ps.setString(3, v.getTel());
            ps.setString(4, v.getEstado());
            ps.setString(5, v.getUser());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
    
    public int actualizar(Vendedor v) {
        int r = 0;
        String sql = "update vendedor set Dni=?, Nombres=?,Telefono=?,Estado=? Where IdVendedor=?";
        try {
            acceso = con.Conectar();
            ps = acceso.prepareStatement(sql);
            ps.setString(1, v.getDni());
            ps.setString(2, v.getNom());
            ps.setString(3, v.getTel());
            ps.setString(4, v.getEstado());
            ps.setInt(5, v.getId());
            r = ps.executeUpdate();
            if (r == 1) {
                r = 1;
            } 
            else {
                r = 0;
            }
        } catch (Exception e) {
            //System.err.println("" + e);
        }
        return r;
    }
    
    public int delete(int id) {
        int r=0;
        String sql = "delete from vendedor where IdVendedor=?";
        try {
            acceso = con.Conectar();
            ps = acceso.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }
}
