package pe.unjfsc.ads.java8.entity;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pe.unjfsc.ads.java8.controlador.conexion;

public class CE_Adolescente {

    private int ID_adolescente;
    private String Apellidos;
    private String Nombre;
    private String Fecha_Nacimiento;
    private String sexo;
    private String Talla;
    private int Nro_Edad;
    private int Peso;

    public CE_Adolescente() {
    }

    public CE_Adolescente(int ID_adolescente, String Apellidos, String Nombre, String Fecha_Nacimiento, String sexo, String Talla, int Nro_Edad, int Peso) {
        this.ID_adolescente = ID_adolescente;
        this.Apellidos = Apellidos;
        this.Nombre = Nombre;
        this.Fecha_Nacimiento = Fecha_Nacimiento;
        this.sexo = sexo;
        this.Talla = Talla;
        this.Nro_Edad = Nro_Edad;
        this.Peso = Peso;
    }

    public int getID_adolescente() {
        return ID_adolescente;
    }

    public void setID_adolescente(int ID_adolescente) {
        this.ID_adolescente = ID_adolescente;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getFecha_Nacimiento() {
        return Fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(String Fecha_Nacimiento) {
        this.Fecha_Nacimiento = Fecha_Nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTalla() {
        return Talla;
    }

    public void setTalla(String Talla) {
        this.Talla = Talla;
    }

    public int getNro_Edad() {
        return Nro_Edad;
    }

    public void setNro_Edad(int Nro_Edad) {
        this.Nro_Edad = Nro_Edad;
    }

    public int getPeso() {
        return Peso;
    }

    public void setPeso(int Peso) {
        this.Peso = Peso;
    }

    @Override
    public String toString() {
        return "CE_Adolescente{" + "ID_adolescente=" + ID_adolescente + ", Apellidos=" + Apellidos + ", Nombre=" + Nombre + ", Fecha_Nacimiento=" + Fecha_Nacimiento + ", sexo=" + sexo + ", Talla=" + Talla + ", Nro_Edad=" + Nro_Edad + ", Peso=" + Peso + '}';
    }

    public void Mostrar_CE_Adolescent(JTable paramTablaAdolescente) {

        conexion objetoconexion = new conexion();

        DefaultTableModel modelo = new DefaultTableModel();

        String sql = "";

        modelo.addColumn("Id");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Nombre");
        modelo.addColumn("Fecha Nac");
        modelo.addColumn("Sexo");
        modelo.addColumn("Talla");
        modelo.addColumn("Edad");
        modelo.addColumn("Peso");

        paramTablaAdolescente.setModel(modelo);

        sql = "SELECT*FROM G1t02_Adolescent";

        String[] datos = new String[8];

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

                modelo.addRow(datos);

            }
            paramTablaAdolescente.setModel(modelo);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error:" + e.toString());

        }

    }

    public void Insertar_CE_ADOLESCENT(JTextField paramId, JTextField paramPellidos, JTextField paraNombre, JComboBox paramSexo, JTextField paraTalla, JTextField paraEdad, JTextField paraPeso,
            JTextField paraFecha) {

        setID_adolescente(Integer.parseInt(paramId.getText()));
        setApellidos(paramPellidos.getText());
        setNombre(paraNombre.getText());
        setSexo(paramSexo.getSelectedItem().toString());
        setTalla(paraTalla.getText());
        setNro_Edad(Integer.parseInt(paraEdad.getText()));
        setPeso(Integer.parseInt(paraPeso.getText()));
        setFecha_Nacimiento(paraFecha.getText());

        conexion objetoConexion = new conexion();

        String consulta = "INSERT INTO g1t02_adolescente (id_adolescente, apellidos, nombre,sexo, talla, nro_edad, peso, fecha_nacimiento_) VALUES (?,?,?,?,?,?,?,?);";

        try {

            CallableStatement cs = objetoConexion.getConexion().prepareCall(consulta);

            cs.setInt(1, getID_adolescente());
            cs.setString(2, getApellidos());
            cs.setString(3, getNombre());
            cs.setString(4, getSexo());
            cs.setString(5, getTalla());
            cs.setInt(6, getNro_Edad());
            cs.setDouble(7, getPeso());
            cs.setString(8, getFecha_Nacimiento());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se insertó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }

    }

    public void SelecionarTabla(JTable paramTablaAdolescente, JTextField paramIdd, JTextField paramaPellido, JTextField paraNombres, JComboBox paraSex, JTextField paraTa, JTextField paraEd, JTextField paraPeso,
            JTextField paraFechaN) {

        try {

            int fila = paramTablaAdolescente.getSelectedRow();

            if (fila >= 0) {
                paramIdd.setText(paramTablaAdolescente.getValueAt(fila, 0).toString());
                paramaPellido.setText(paramTablaAdolescente.getValueAt(fila, 1).toString());
                paraNombres.setText(paramTablaAdolescente.getValueAt(fila, 2).toString());
                paraSex.setSelectedItem(paramTablaAdolescente.getValueAt(fila, 4).toString());
                paraTa.setText(paramTablaAdolescente.getValueAt(fila, 5).toString());
                paraEd.setText(paramTablaAdolescente.getValueAt(fila, 6).toString());
                paraPeso.setText(paramTablaAdolescente.getValueAt(fila, 7).toString());
                paraFechaN.setText(paramTablaAdolescente.getValueAt(fila, 3).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }
    }

    public void Modificar_CE_ADOLESCENT(JTextField paramIDD, JTextField paramPellido, JTextField paraNombr, JComboBox paraSe, JTextField paraTaL, JTextField paraEdadD, JTextField paraPesoO,
            JTextField paraFechaNN) {

        setID_adolescente(Integer.parseInt(paramIDD.getText()));
        setApellidos(paramPellido.getText());
        setNombre(paraNombr.getText());
        setSexo(paraSe.getSelectedItem().toString());
        setTalla(paraTaL.getText());
        setNro_Edad(Integer.parseInt(paraEdadD.getText()));
        setPeso(Integer.parseInt(paraPesoO.getText()));
        setFecha_Nacimiento(paraFechaNN.getText());

        conexion objetoconexion = new conexion();
        String consulta = "UPDATE g1t02_adolescente SET apellidos=? ,nombre=?, sexo=?, talla=?, nro_edad=?, peso=?, fecha_nacimiento_=? WHERE g1t02_adolescente.id_adolescente=?;";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);

            cs.setInt(1, getID_adolescente());
            cs.setString(2, getApellidos());
            cs.setString(3, getNombre());
            
           cs.setString(4, getFecha_Nacimiento());
              
            cs.setString(5, getSexo());
            cs.setString(6, getTalla());
            cs.setInt(7, getNro_Edad());
            cs.setInt(8, getPeso());
          

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se modificó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }

    }

    public void Eliminar_CE_ADOLESCENT(JTextField paramID_ADO) {

        setID_adolescente(Integer.parseInt(paramID_ADO.getText()));

        conexion objetoconexion = new conexion();
        String consulta = "DELETE FROM g1t02_adolescente WHERE g1t02_adolescente.id_adolescente=?;";

        try {
            CallableStatement cs = objetoconexion.getConexion().prepareCall(consulta);

            cs.setInt(1, getID_adolescente());

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se eliminó correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error:" + e.toString());
        }

    }

    public void graficar(JTextField paramIDD, JTextField paramPellido, JTextField paraNombr, JComboBox paraSe, JTextField paraTaL, JTextField paraEdadD, JTextField paraPesoO,
            JTextField paraFechaNN) {
        setID_adolescente(Integer.parseInt(paramIDD.getText()));
        setApellidos(paramPellido.getText());
        setNombre(paraNombr.getText());
        setSexo(paraSe.getSelectedItem().toString());
        setTalla(paraTaL.getText());
        setNro_Edad(Integer.parseInt(paraEdadD.getText()));
        setPeso(Integer.parseInt(paraPesoO.getText()));
        setFecha_Nacimiento(paraFechaNN.getText());

    }

}
