package classExercise;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Telcel
{
    public static void main(String[] args)
    {
        //INICIALIZACION DE SYSTEM.SETPROPERTY()
        System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe");
        WebDriver driver;
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        //driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS);

        try
        {
            driver.get("http://www.telcel.com");
            WebElement logoTelcel = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[src='/content/dam/htmls/img/icons/logo-telcel.svg']")));
            WebElement linkTiendaEnLinea = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-nombreboton='Tienda en linea superior']")));
            WebElement campoBusqueda = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("buscador-menu-input")));

            linkTiendaEnLinea.click();
            WebElement linkTelefonosCelulares = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".shortcut-container [data-nombreboton='Telefonos y smartphones']")));
            linkTelefonosCelulares.click();

            WebElement ModalSeleccionEstado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal-dialog.modal-lg")));
            WebElement seleccionaEstadoDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-single span")));
            seleccionaEstadoDropdown.click();
            WebElement campoBusquedaEstado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-search input")));
            campoBusquedaEstado.clear();
            campoBusquedaEstado.sendKeys("Jalisco");
            WebElement ligaEstado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-results li")));
            ligaEstado.click();
            WebElement botonEntrar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("entrarPerfilador")));
            botonEntrar.click();

            WebElement listaResultados = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("seleccion-de-equipos-galeria-mosaicfilters")));
            WebElement estadoSeleccionado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".estado-Gluo.text-option")));
            //if (estadoSeleccionado.getText() == "Jalisco")
            WebElement marcaCelular = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//p[@class='telcel-mosaico-equipos-marca ng-binding'])[1]")));

            //System.out.println(marcaCelular.getText().substring(0,4));
            //(//p[@class="telcel-mosaico-equipos-precio ng-binding"])[1] Precio
        }
        catch ( ElementNotInteractableException e)
        {
            System.out.println(e);
            e.printStackTrace();
            driver.quit();
        }
        catch (InvalidSelectorException e)
        {
            System.out.println(e);
            e.printStackTrace();
            driver.quit();
        }
        catch (TimeoutException e)
        {
            System.out.println(e);
            e.printStackTrace();
            driver.quit();
        }
        driver.quit();
    }

}
