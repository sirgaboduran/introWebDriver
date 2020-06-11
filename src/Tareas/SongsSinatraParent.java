package Tareas;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SongsSinatraParent
{
    public static WebDriver driver;
    public static WebDriverWait wait;

    public static void navegar(String url)
    {
        System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);

        wait = new WebDriverWait(driver, 10);

        driver.navigate().to(url); //abre la url solicitada
        driver.manage().window().maximize();
    }

    public static void validarHomePage()
    {
        try
        {
            WebElement linkLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("log in")));
            WebElement imgSinatra = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[src='/images/sinatra.jpg']")));
            if (imgSinatra.isDisplayed() && linkLogin.isDisplayed() && linkLogin.isEnabled())
            {
                System.out.println("Se cargó la página prinncipal correctamente");
            } else
            {
                System.out.println("NO se encontro la imagen");
                System.exit(-1);
            }
        } catch (NoSuchElementException e)
        {
            System.out.println("NO se cargo la página correctamente");
            driver.quit();
            System.exit(-1);
        }
    }

    public static void realizarLoginCorrecto(String usuario, String password)
    {
        try
        {
            WebElement linkLogin = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("log in")));
            if (linkLogin.isDisplayed() && linkLogin.isEnabled())
            {
                linkLogin.click();
            } else
            {
                System.out.println("El link de login no existe");
                System.exit(-1);
            }
            WebElement usernameTxt = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            usernameTxt.sendKeys(usuario);
            WebElement passwordTxt = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password")));
            passwordTxt.sendKeys(password);
            WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[value='Log In']")));
            loginBtn.click();
            System.out.println("Se introdujeron los datos correctamente");
        } catch (NoSuchElementException e)
        {
            System.out.println("NO se introdujeron los datos correctamente");
            driver.quit();
            System.exit(-1);
        }
    }
    public static void validarSongsPage(String url)
    {
        try
        {
            WebElement songsText = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section h1")));
            WebElement songsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[href='/songs']")));
            List<WebElement> listaDeCanciones = driver.findElements(By.cssSelector(("#songs li")));
            String currentClass = songsLink.getAttribute("class");
            String currentURL = driver.getCurrentUrl();

            if (songsText.isDisplayed() && songsLink.isDisplayed() && (url.equals(currentURL)) && (currentClass.equals("current")) && (listaDeCanciones.size() > 0))
            {
                System.out.println("Se cargó la página de Songs correctamente");
            } else
            {
                System.out.println("NO cargó la página de Songs correctamente");
                System.exit(-1);
            }
        } catch (NoSuchElementException e)
        {
            System.out.println("NO se cargo la página correctamente");
            driver.quit();
            System.exit(-1);
        }
    }

    public static void validarMensajeBienvenida(String mensajeBienvenida)
    {
//    HomePage:
//    mensajeBienvenida: id="flash"
//    linkLogout: href="/logout"
        try
        {
            WebElement mensajeDeBienvenida = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("flash")));
            WebElement linkLogout = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[href='/logout']")));

            if ((mensajeDeBienvenida.getText().equals(mensajeBienvenida)) && linkLogout.isDisplayed() && linkLogout.isEnabled())
            {
                System.out.println("Se realizó el login correctamente");
            } else
            {
                System.out.println("NO se realizó el login correctamente");
                System.exit(-1);
            }

        } catch (NoSuchElementException e)
        {
            System.out.println("NO se realizó el login correctamente");
            driver.quit();
            System.exit(-1);
        }
    }

    public static void cerrarBrowser()
    {
        driver.quit();
    }
}
