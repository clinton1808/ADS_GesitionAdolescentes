package pe.unjfsc.ads.java8.entity;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pe.unjfsc.ads.java8.controlador.conexion;

public class CE_LugarDeProcedencia {

    private int ID_LDeProcedencia;
    private int COD_postal;
    private String Nomb_LDeProcedencia;

    public CE_LugarDeProcedencia() {
    }

    public CE_LugarDeProcedencia(int ID_LDeProcedencia, int COD_postal, String Nomb_LDeProcedencia) {
        this.ID_LDeProcedencia = ID_LDeProcedencia;
        this.COD_postal = COD_postal;
        this.Nomb_LDeProcedencia = Nomb_LDeProcedencia;
    }

    public int getID_LDeProcedencia() {
        return ID_LDeProcedencia;
    }

    public void setID_LDeProcedencia(int ID_LDeProcedencia) {
        this.ID_LDeProcedencia = ID_LDeProcedencia;
    }

    public int getCOD_postal() {
        return COD_postal;
    }

    public void setCOD_postal(int COD_postal) {
        this.COD_postal = COD_postal;
    }

    public String getNomb_LDeProcedencia() {
        return Nomb_LDeProcedencia;
    }

    public void setNomb_LDeProcedencia(String Nomb_LDeProcedencia) {
        this.Nomb_LDeProcedencia = Nomb_LDeProcedencia;
    }

    //@Override
    // public String toString() {
    //   return "CE_LugarDeProcedencia{" + "ID_LDeProcedencia=" + ID_LDeProcedencia + ", COD_postal=" + COD_postal + ", Nomb_LDeProcedencia=" + Nomb_LDeProcedencia + '}';
    // }
    public void Mostrar_CE_LugarDeProcedencia(JTable paramTablaLugarDeProcedencia) {

        conexion objetoconexion = new conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("Id");
        modelo.addColumn("Codigo Postal");
        modelo.addColumn("Nombre");

        paramTablaLugarDeProcedencia.setModel(modelo);

        sql = "SELECT * FROM g1t03_procedencia;";

        String[] datos = new String[3];

        Statement st;

        try {

            st = objetoconexion.getConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                modelo.addRow(datos);

            }
            paramTablaLugarDeProcedencia.setModel(modelo);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error:" + e.toString());

        }

    }
    
    public void Insertar_CE_LUGAR(JTextField paramID_proce,JTextField paramCodP, JTextField paramNombre) {
        
        setID_LDeProcedencia(Integer.parseInt(paramID_proce.getText()));
        setCOD_postal(Integer.parseInt(paramCodP.getText()));
        setNomb_LDeProcedencia(paramNombre.getText());
        

        conexion objetoconexion = new conexion();
        String consulta = "INSERT INTO g1t03_procedencia (id_ldeprocedencia,cod_postal,nomb_ldeprocedencia) VALUES (?,?,?); ";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);
            cs.setInt(1, getID_LDeProcedencia());
            cs.setInt(2, getCOD_postal());
            cs.setString(3, getNomb_LDeProcedencia());
            

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se insertó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
        
    }
    public void SelecionarTABL (JTable paramTablaPROCE, JTextField paramIDProce, JTextField paramCODIGO, JTextField paramNom){
        
        
        try {
        
            int fila= paramTablaPROCE.getSelectedRow();
            
            if(fila>=0){
                paramIDProce.setText(paramTablaPROCE.getValueAt(fila,0).toString());
                paramCODIGO.setText(paramTablaPROCE.getValueAt(fila,1).toString());
                paramNom.setText(paramTablaPROCE.getValueAt(fila,2).toString());
            } else
            {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }  
    }
     
   public void Modificar_CE_Procedencia(JTextField paramIDP,JTextField paramPCOD, JTextField paramNOMBRES) {
        
          setID_LDeProcedencia(Integer.parseInt(paramIDP.getText()));
          setCOD_postal(Integer.parseInt(paramPCOD.getText()));
          setNomb_LDeProcedencia(paramNOMBRES.getText());
        

        conexion objetoconexion = new conexion();
        String consulta = "UPDATE g1t03_procedencia SET cod_postal=?,nomb_ldeprocedencia=? WHERE g1t03_procedencia.id_ldeprocedencia=?;";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);
            
            
            cs.setInt(1, getCOD_postal());
            cs.setString(2, getNomb_LDeProcedencia());
            cs.setInt(3, getID_LDeProcedencia());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se modificó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
   }
   public void ELIMINAR_CE_Procedencia(JTextField paramID) {
        
        setID_LDeProcedencia(Integer.parseInt(paramID.getText()));
     
        conexion objetoconexion = new conexion();
        String consulta = "DELETE FROM g1t03_procedencia WHERE g1t03_procedencia.id_ldeprocedencia=?; ";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);      
            
            cs.setInt(1, getID_LDeProcedencia());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Se Eliminó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
        
    }
        
    }