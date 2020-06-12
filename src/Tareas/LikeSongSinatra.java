package homeworks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LikeSongSinatra extends LikeSinatraParent {
    public static void main(String[] args) {

        navegarSitio("https://evening-bastion-49392.herokuapp.com/");
        validarHomePage();
        navegarSongsPage("https://evening-bastion-49392.herokuapp.com/songs");
        navegarPrimerCancion();
        validarLikeAgregado();
        cerrarBrowser();


    }


}