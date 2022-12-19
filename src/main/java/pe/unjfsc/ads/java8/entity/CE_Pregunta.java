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

public class CE_Pregunta {

    private int id_pregunta;
    private String txt_descripcion;

    public int getId_pregunta() {
        return id_pregunta;
    }

    public void setId_pregunta(int id_pregunta) {
        this.id_pregunta = id_pregunta;
    }

    public String getTxt_descripcion() {
        return txt_descripcion;
    }

    public void setTxt_descripcion(String txt_descripcion) {
        this.txt_descripcion = txt_descripcion;
    }

    @Override
    public String toString() {
        return "CEPregunta{" + "id_pregunta=" + id_pregunta + ", txt_descripcion=" + txt_descripcion + '}';
    }

    public void Mostrar_CE_Pregunta(JTable paramTablaPregunta) {

        conexion objetoconexion = new conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("Id");
        modelo.addColumn("Pregunta");

        paramTablaPregunta.setModel(modelo);

        sql = "SELECT * FROM g1t04_preguntas";

        String[] datos = new String[2];

        Statement st;

        try {

            st = objetoconexion.getConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                 datos[0] = rs.getString(1);
                 datos[1] = rs.getString(2);
                
                //datos[1] = rs.getString(2);

                modelo.addRow(datos);

            }
            paramTablaPregunta.setModel(modelo);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error:" + e.toString());

        }
    }

    public void Insertar_CE_Pregunta(JTextField paramID,JTextField paramPregunta) {
        
        setId_pregunta(Integer.parseInt(paramID.getText()));
        setTxt_descripcion(paramPregunta.getText());
        

        conexion objetoconexion = new conexion();
        String consulta = "INSERT INTO g1t04_preguntas (id_pregunta,txt_pregunta) VALUES (?,?) ";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);
            cs.setInt(1, getId_pregunta());
            cs.setString(2, getTxt_descripcion());
            

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se insertó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
        
    }
    
    public void SelecionarTabla (JTable paramTablaPregunta, JTextField paramIDPregunta, JTextField paramtextoPregunta){
        
        
        try {
        
            int fila= paramTablaPregunta.getSelectedRow();
            
            if(fila>=0){
                paramIDPregunta.setText(paramTablaPregunta.getValueAt(fila,0).toString());
                paramtextoPregunta.setText(paramTablaPregunta.getValueAt(fila,1).toString());
            } else
            {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }  
    }
    
    public void Modificar_CE_Pregunta(JTextField paramID,JTextField paramPregunta) {
        
        setId_pregunta(Integer.parseInt(paramID.getText()));
        setTxt_descripcion(paramPregunta.getText());
        

        conexion objetoconexion = new conexion();
        String consulta = "UPDATE g1t04_preguntas SET txt_pregunta=? WHERE g1t04_preguntas.id_pregunta =?; ";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);
            
            cs.setString(1, getTxt_descripcion());
            cs.setInt(2, getId_pregunta());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se modificó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
        
    }
    
   
    public void ELIMINAR_CE_Pregunta(JTextField paramID) {
        
        setId_pregunta(Integer.parseInt(paramID.getText()));
     
        conexion objetoconexion = new conexion();
        String consulta = "DELETE FROM g1t04_preguntas WHERE g1t04_preguntas.id_pregunta=? ";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);      
            
            cs.setInt(1, getId_pregunta());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Se Eliminó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
        
    }
    
}
