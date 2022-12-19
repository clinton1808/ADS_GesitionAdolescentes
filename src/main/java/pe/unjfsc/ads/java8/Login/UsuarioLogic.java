
package pe.unjfsc.ads.java8.Login;


public class UsuarioLogic {
    
   
    private static UsuarioDAO usuarioDAO = new UsuarioDAO();
    
    public static boolean autentificar(String usuario, String contraseña){
        
        if(obtener(usuario)!=null){
            
         Usuario usuarioConsulta = obtener(usuario);
         
           if(usuarioConsulta.getUsuario().equals(usuario)&&usuarioConsulta.getContraseña().equals(contraseña)){
              return true;
             }else{
               return false;
            }
        }else{
            return false;
        }
    }
    
    public static boolean insertar(Usuario usuario){
        return usuarioDAO.insertar(usuario);
    }
    public static boolean modificar(Usuario usuario){
        return usuarioDAO.modificar(usuario);
    }
    public static boolean eliminar(String usuario){
        return usuarioDAO.eliminar(usuario);
    }
    public static Usuario obtener(String usuario){
        return usuarioDAO.obtener(usuario);
   }
 
    
}
