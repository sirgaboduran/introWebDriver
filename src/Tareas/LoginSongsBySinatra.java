package Tareas;

public class LoginSongsBySinatra extends SongsSinatraParent
{
    public static void main(String[] args) {
        navegar("https://evening-bastion-49392.herokuapp.com/");
        validarHomePage();
        realizarLoginCorrecto("frank", "sinatra");
        validarSongsPage("https://evening-bastion-49392.herokuapp.com/songs");
        validarMensajeBienvenida("You are now logged in as frank");
        cerrarBrowser();
    }
}
