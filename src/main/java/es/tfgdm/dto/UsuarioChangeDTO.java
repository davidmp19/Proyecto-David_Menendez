package es.tfgdm.dto;

//DTO utilizado para el cambio de información del usuario, nombre de usuario y contraseña
public class UsuarioChangeDTO {
	
 private String newUsername; // Nuevo nombre de usuario
	
 private String currentPassword; // Contraseña actual del usuario

 private String newPassword; // Nueva contraseña del usuario

 private String confirmNewPassword; // Confirmación de la nueva contraseña

 // Getters and Setters
 
 // Método para obtener la contraseña actual del usuario
 public String getCurrentPassword() {
     return currentPassword;
 }

 // Método para establecer la contraseña actual del usuario
	public void setCurrentPassword(String currentPassword) {
     this.currentPassword = currentPassword;
 }

 // Método para obtener el nuevo nombre de usuario
 public String getNewUsername() {
		return newUsername;
	}

 // Método para establecer el nuevo nombre de usuario
	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}

 // Método para obtener la nueva contraseña del usuario
 public String getNewPassword() {
     return newPassword;
 }

 // Método para establecer la nueva contraseña del usuario
 public void setNewPassword(String newPassword) {
     this.newPassword = newPassword;
 }

 // Método para obtener la confirmación de la nueva contraseña del usuario
 public String getConfirmNewPassword() {
     return confirmNewPassword;
 }

 // Método para establecer la confirmación de la nueva contraseña del usuario
 public void setConfirmNewPassword(String confirmNewPassword) {
     this.confirmNewPassword = confirmNewPassword;
 }


}
