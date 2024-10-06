package poly.duan.fastfoodie.Model;

import com.google.gson.annotations.SerializedName;

public class Password {
    @SerializedName("email")

    private String email;
    @SerializedName("password")
    private String pasword;
    @SerializedName("newpassword")

    private String newpassword;
    @SerializedName("confirmpassword")

    private String confirmpassword;

    public String getEmail(String email) {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public Password() {
        this.email = email;
        this.pasword = pasword;
        this.newpassword = newpassword;
        this.confirmpassword = confirmpassword;
    }
}
