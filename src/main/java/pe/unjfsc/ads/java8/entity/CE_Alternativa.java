package pe.unjfsc.ads.java8.entity;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pe.unjfsc.ads.java8.controlador.conexion;

public class CE_Alternativa {

    private int ID_Alternativa;
    private int ID_pregunta;
    private String valor_alternativa;
    private String Adolescentes;
    public CE_Alternativa() {
    }

    public CE_Alternativa(int ID_Alternativa, int ID_pregunta, String valor_alternativa, String Adolescentes) {
        this.ID_Alternativa = ID_Alternativa;
        this.ID_pregunta = ID_pregunta;
        this.valor_alternativa = valor_alternativa;
        this.Adolescentes = Adolescentes;
    }

    public int getID_Alternativa() {
        return ID_Alternativa;
    }

    public void setID_Alternativa(int ID_Alternativa) {
        this.ID_Alternativa = ID_Alternativa;
    }

    public int getID_pregunta() {
        return ID_pregunta;
    }

    public void setID_pregunta(int ID_pregunta) {
        this.ID_pregunta = ID_pregunta;
    }

    public String getValor_alternativa() {
        return valor_alternativa;
    }

    public void setValor_alternativa(String valor_alternativa) {
        this.valor_alternativa = valor_alternativa;
    }

     public String getAdolescentes() {
        return Adolescentes;
    }

    public void setAdolescentes(String Adolescentes) {
        this.Adolescentes = Adolescentes;
    }
    @Override
    public String toString() {
        return "CE_Alternativa{" + "ID_Alternativa=" + ID_Alternativa + ", ID_pregunta=" + ID_pregunta + ", valor_alternativa=" + valor_alternativa +  "Adolescentes=" + Adolescentes +  '}';
    }

    public void Mostrar_CE_Alternativa(JTable paramTablaAlternativa) {

        conexion objetoconexion = new conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("Id Alternativa");
        modelo.addColumn("Id Pregunta");
        modelo.addColumn("Alternativa");
        modelo.addColumn("Adolescentes");

        paramTablaAlternativa.setModel(modelo);

        sql = "SELECT * FROM g1t05_alternativa;";

        String[] datos = new String[4];

        Statement st;

        try {

            st = objetoconexion.getConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);


                modelo.addRow(datos);

            }
            paramTablaAlternativa.setModel(modelo);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, e.toString() + "Error:");

        }

    }
    public void Insertar_CE_ALTERNA(JTextField paramIDALTER,JTextField paramIDPRE, JTextField paramAlterna, JComboBox Adolescentes) {
        
        setID_Alternativa(Integer.parseInt(paramIDALTER.getText()));
        setID_pregunta(Integer.parseInt(paramIDPRE.getText()));
        setValor_alternativa(paramAlterna.getText());
        setAdolescentes(Adolescentes.getSelectedItem().toString());
        

        conexion objetoconexion = new conexion();
        String consulta = "INSERT INTO g1t05_alternativa (id_alternativa, id_pregunta, alternativa, adolescentes) VALUES (?,?,?,?);";

        try {
            CallableStatement css = objetoconexion.getConexion().prepareCall(consulta);
            
            css.setInt(1, getID_Alternativa());
            css.setInt(2, getID_pregunta());
            css.setString(3, getValor_alternativa());
            css.setString(4, getAdolescentes());
                     

            css.execute();

            JOptionPane.showMessageDialog(null, "Se insertó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
        
    }
    
     public void SelecionarTabla (JTable paramTablaAlternativa, JTextField paramIDalter,JTextField paramIDApre, JTextField paramAlternat){
        
        
        try {
        
            int fila= paramTablaAlternativa.getSelectedRow();
            
            if(fila>=0){
                paramIDalter.setText(paramTablaAlternativa.getValueAt(fila,0).toString());
                paramIDApre.setText(paramTablaAlternativa.getValueAt(fila,1).toString());
                paramAlternat.setText(paramTablaAlternativa.getValueAt(fila,2).toString());
            } else
            {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }  
    }
    
    public void Modificar_CE_Alternativa(JTextField paramIDA,JTextField paramIDP, JTextField paramAltE) {
        
         setID_Alternativa(Integer.parseInt(paramIDA.getText()));
        setID_pregunta(Integer.parseInt(paramIDP.getText()));
        setValor_alternativa(paramAltE.getText());

        conexion objetoconexion = new conexion();
        String consulta = "UPDATE g1t05_alternativa SET id_pregunta=?, alternativa=? WHERE g1t05_alternativa.id_alternativa=?;";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);
            
            
            cs.setInt(1, getID_pregunta());
            cs.setString(2, getValor_alternativa());
            cs.setInt(3, getID_Alternativa());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se modificó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
        
    }
    
   
    public void Eliminar_CE_Alternativa(JTextField paramIDAA) {
        
         setID_Alternativa(Integer.parseInt(paramIDAA.getText()));
        

        conexion objetoconexion = new conexion();
        String consulta = "DELETE FROM g1t05_alternativa WHERE g1t05_alternativa.id_alternativa=?;";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);
            

            cs.setInt(1, getID_Alternativa());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se eliminó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
        
    }
    
}
