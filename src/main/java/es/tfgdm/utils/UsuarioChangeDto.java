package es.tfgdm.utils;


public class UsuarioChangeDto {
	
    private String newUsername;
	
    private String currentPassword;

    private String newPassword;

    private String confirmNewPassword;

    // Getters and Setters
    
    public String getCurrentPassword() {
        return currentPassword;
    }

	public String getNewUsername() {
		return newUsername;
	}

	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}

	public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }

}
