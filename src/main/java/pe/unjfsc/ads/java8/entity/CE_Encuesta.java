package pe.unjfsc.ads.java8.entity;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pe.unjfsc.ads.java8.controlador.conexion;

public class CE_Encuesta {

    private int Nro_Encuesta;
    private int ID_Adolescente;
    private int ID_procedencia;
    private int ID_pregunta;
    private String Fecha_Encuesta;
    private String pregunta;
    private String genero;
    private String procedencia;
    private String peso;
    private String talla;
    public CE_Encuesta() {
    }

    public CE_Encuesta(int Nro_Encuesta, int ID_Adolescente, int ID_procedencia, int ID_pregunta, String Fecha_Encuesta, String pregunta, String genero, String procedencia, String peso, String talla) {
        this.Nro_Encuesta = Nro_Encuesta;
        this.ID_Adolescente = ID_Adolescente;
        this.ID_procedencia = ID_procedencia;
        this.ID_pregunta = ID_pregunta;
        this.Fecha_Encuesta = Fecha_Encuesta;
        this.pregunta = pregunta;
        this.genero = genero;
        this.procedencia = procedencia;
        this.peso = peso;
        this.talla = talla;
    }

    public int getNro_Encuesta() {
        return Nro_Encuesta;
    }

    public void setNro_Encuesta(int Nro_Encuesta) {
        this.Nro_Encuesta = Nro_Encuesta;
    }

    public int getID_Adolescente() {
        return ID_Adolescente;
    }

    public void setID_Adolescente(int ID_Adolescente) {
        this.ID_Adolescente = ID_Adolescente;
    }

    public int getID_procedencia() {
        return ID_procedencia;
    }

    public void setID_procedencia(int ID_procedencia) {
        this.ID_procedencia = ID_procedencia;
    }

    public int getID_pregunta() {
        return ID_pregunta;
    }

    public void setID_pregunta(int ID_pregunta) {
        this.ID_pregunta = ID_pregunta;
    }

    public String getFecha_Encuesta() {
        return Fecha_Encuesta;
    }

    public void setFecha_Encuesta(String Fecha_Encuesta) {
        this.Fecha_Encuesta = Fecha_Encuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    
    
    @Override
    public String toString() {
        return "CE_Encuesta{" + "nro_Encuesta=" + Nro_Encuesta + ", id_adolescente=" + ID_Adolescente + ", id_procedencia=" + ID_procedencia + ", id_pregunta=" + ID_pregunta + ", fecha_encuesta=" + Fecha_Encuesta +  ", pregunta=" + pregunta + ", genero=" + genero + ", procedencia=" + procedencia + ", peso=" + peso + ", talla=" + talla + '}';
    }

    public void Mostrar_CE_Encuesta(JTable paramTablaEncuesta) {

        conexion objetoconexion = new conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("Nro Encuesta");
        modelo.addColumn("Id Adolescente");
        modelo.addColumn("Id Procedencia");
        modelo.addColumn("Id Pregunta");
        modelo.addColumn("Fecha Encuesta");
        modelo.addColumn("Pregunta");
        modelo.addColumn("Genero");
        modelo.addColumn("Procedencia");
        modelo.addColumn("Peso");
        modelo.addColumn("Talla");
        
        

        paramTablaEncuesta.setModel(modelo);

        sql = "SELECT * FROM g1t01_encuesta;";

        String[] datos = new String[10];

        Statement st;

        try {

            st = objetoconexion.getConexion().createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);

                modelo.addRow(datos);

            }
            paramTablaEncuesta.setModel(modelo);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null,  "Error:" + e.toString() );

        }

    }
    
    public void Insertar_CE_Encuesta(JTextField paramNRO,JTextField paramidADOLESCENTE, JTextField paramidPROCEDENCIA, JTextField paramidPREGUNTA, JTextField parafecHA, JComboBox paramPregunta , JComboBox paramGenero, JComboBox paramProcedencia, JTextField paramPeso, JTextField paramTalla   ){          
        
        setNro_Encuesta(Integer.parseInt(paramNRO.getText()));
        setID_Adolescente(Integer.parseInt(paramidADOLESCENTE.getText()));
        setID_procedencia(Integer.parseInt(paramidPROCEDENCIA.getText()));
        setID_pregunta(Integer.parseInt(paramidPREGUNTA.getText()));
        setFecha_Encuesta(parafecHA.getText());
        setPregunta(paramPregunta.getSelectedItem().toString());
        setGenero(paramGenero.getSelectedItem().toString());
        setProcedencia(paramProcedencia.getSelectedItem().toString());
        setPeso(paramPeso.getText());
        setTalla(paramTalla.getText());
        
        
        
        
        
        //setFecha_Encuesta(Integer.parseInt(paramFecha.getText()));
        //setFecha_Encuesta(LocalDate.parse(paramFecha.getText()));
        
        
        

        conexion objetoconexion = new conexion();
        String consulta = "INSERT INTO g1t01_encuesta (nro_encuesta, id_adolescente, id_procedencia, id_pregunta, fecha_encuesta_ ,pregunta, genero, procedencia, peso, talla )VALUES (?,?,?,?,?,?,?,?,?,?);";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);
            cs.setInt(1, getNro_Encuesta());
            cs.setInt(2, getID_Adolescente());
            cs.setInt(3, getID_procedencia());
            cs.setInt(4, getID_pregunta());
            cs.setString(5, getFecha_Encuesta());
            cs.setString(6, getPregunta());
            cs.setString(7, getGenero());
            cs.setString(8, getProcedencia());
            cs.setString(9, getPeso());
            cs.setString(10, getTalla());
            
            

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se insertó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
        
    }
    
   public void SelecionarTablaEN (JTable paramTablaEncuesta, JTextField paramNRO,JTextField paramidADOLESCENTE, JTextField paramidPROCEDENCIA, JTextField paramidPREGUNTA, JTextField paramfecha,JComboBox paramPregunta , JComboBox paramGenero, JComboBox paramProcedencia, JTextField paramPeso, JTextField paramTalla  ){
        
        
        try {
        
            int fila= paramTablaEncuesta.getSelectedRow();
            
            if(fila>=0){
                paramNRO.setText(paramTablaEncuesta.getValueAt(fila,0).toString());
                paramidADOLESCENTE.setText(paramTablaEncuesta.getValueAt(fila,1).toString());
                paramidPROCEDENCIA.setText(paramTablaEncuesta.getValueAt(fila,2).toString());
                paramidPREGUNTA.setText(paramTablaEncuesta.getValueAt(fila,3).toString());
                paramfecha.setText(paramTablaEncuesta.getValueAt(fila,4).toString());
                paramPregunta.setSelectedItem(paramTablaEncuesta.getValueAt(fila,5).toString());
                paramGenero.setSelectedItem(paramTablaEncuesta.getValueAt(fila,6).toString());
                paramProcedencia.setSelectedItem(paramTablaEncuesta.getValueAt(fila,7).toString());
                paramPeso.setText(paramTablaEncuesta.getValueAt(fila,8).toString());
                paramTalla.setText(paramTablaEncuesta.getValueAt(fila,9).toString());
                
                
                
                
                
                
            } else
            {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }  
    } 
    
  public void Modificar_CE_Encuesta(JTextField paramNROO,JTextField paramidADOLESCENTEE, JTextField paramidPROCEDENCIAA, JTextField paramidPREGUNTAA, JTextField paramDATA,JComboBox paramPregunta , JComboBox paramGenero, JComboBox paramProcedencia, JTextField paramPeso, JTextField paramTalla  ) {
        
        setNro_Encuesta(Integer.parseInt(paramNROO.getText()));
        setID_Adolescente(Integer.parseInt(paramidADOLESCENTEE.getText()));
        setID_procedencia(Integer.parseInt(paramidPROCEDENCIAA.getText()));
        setID_pregunta(Integer.parseInt(paramidPREGUNTAA.getText()));
        setFecha_Encuesta(paramDATA.getText());
        setPregunta(paramPregunta.getSelectedItem().toString());
        setGenero(paramGenero.getSelectedItem().toString());
        setProcedencia(paramProcedencia.getSelectedItem().toString());
        setPeso(paramPeso.getText());
        setTalla(paramTalla.getText());
        
        conexion objetoconexion = new conexion();
        String consulta = "UPDATE g1t01_encuesta SET id_adolescente=?, id_procedencia=?, id_pregunta=?, fecha_encuesta_=?, pregunta=?, genero=? , procedencia=? , peso=? , talla=? WHERE g1t01_encuesta.nro_encuesta=?;";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);
            
            
            cs.setInt(1, getNro_Encuesta());
            cs.setInt(2, getID_Adolescente());
            cs.setInt(3,getID_procedencia());
            cs.setInt(4,getID_pregunta());
            cs.setString(5, getFecha_Encuesta());
            cs.setString(6, getPregunta());
            cs.setString(7, getGenero());
            cs.setString(8, getProcedencia());
            cs.setString(9, getPeso());
            cs.setString(10, getTalla());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se modificó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
        
    }
    
   
    public void ELIMINAR_CE_Encuesta(JTextField paramIDEn) {
        
        setNro_Encuesta(Integer.parseInt(paramIDEn.getText()));
     
        conexion objetoconexion = new conexion();
        String consulta = "DELETE FROM g1t01_encuesta WHERE g1t01_encuesta.nro_encuesta=?;";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);      
            
            cs.setInt(1, getNro_Encuesta());
            cs.execute();

            JOptionPane.showMessageDialog(null, "Se Eliminó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }    
        
    }  
}

