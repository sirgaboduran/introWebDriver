package classExercise;


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

public class TelcelMethods
{

    static WebDriver driver;
    static WebDriverWait wait;

    protected static void navegarSitio(String url)
    {
        System.setProperty("webdriver.chrome.driver", "C:\\libs\\chromedriver.exe");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);

        wait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.get(url);
    }

    protected static void verificarLandingPage()
    {
        try
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[src='/content/dam/htmls/img/icons/logo-telcel.svg']")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-nombreboton='Tienda en linea superior']")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("buscador-menu-input")));

            System.out.println("Si cargo la pagina principal de telcel");
        } catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }
    }

    protected static void listarTelefonos()
    {
        try
        {
            WebElement linkTiendaEnLinea = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-nombreboton='Tienda en linea superior']")));
            WebElement linkTelefonosCelulares = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".shortcut-container [data-nombreboton='Telefonos y smartphones']")));

            linkTiendaEnLinea.click();
            linkTelefonosCelulares.click();

        } catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }

    }

    protected static void seleccionarEstado(String nombreEstado)
    {
        try
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal-dialog.modal-lg")));
            WebElement seleccionaEstadoDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-single span")));
            WebElement campoBusquedaEstado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-search input")));
            WebElement ligaEstado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-results li")));
            WebElement botonEntrar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("entrarPerfilador")));

            seleccionaEstadoDropdown.click();
            campoBusquedaEstado.clear();
            campoBusquedaEstado.sendKeys(nombreEstado);
            ligaEstado.click();
            botonEntrar.click();

        } catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }
    }

    protected static void verificarPaginaResultados()
    {
        try
        {
            int celularesSize = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".comp-telcel-mosaico-equipos li"))).size();
            if (celularesSize > 1)
                System.out.println("La lista se desplego correctamente.");
            else
                System.out.println("No se cargo la Pagina de Resultados");
        } catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }

    }

    protected static Celular capturarDatosCelular(int i)
    {
        String mm = "";
        String nombreEquipo = "";
        double pe = 0;
        int numGigas = 0;
        try
        {
            String mm = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".telcel-mosaico-equipos-marca"))).getText();
            nombreEquipo = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".telcel-mosaico-equipos-nombre-equipo"))).getText();

            String precioEquipo = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".telcel-mosaico-equipos-precio"))).getText();
            precioEquipo = precioEquipo.replace(",", "");
            precioEquipo = precioEquipo.replace("$", "");
            pe = Double.parseDouble(precioEquipo);

            String capacidadEquipo = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".telcel-mosaico-equipos-capacidad-numero"))).getText();
            String[] datos = capacidadEquipo.split(" ");
            String capacidadString = datos[0];
            numGigas = Integer.parseInt(capacidadString);


        } catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }
        return new Celular(mm, nombreEquipo, pe, numGigas);
    }

    protected static void seleccionarCelular(int numCelular)
    {
        try
        {
            List<WebElement> celulares = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".comp-telcel-mosaico-equipos li")));
            celulares.get(numCelular - 1).click();

        } catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }
    }

    protected static void validarDatosCelular(Celular primerCelular)
    {
        try
        {
            WebElement textoMarcaModelo = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ecommerce-ficha-tecnica-modelo")));
            WebElement textoNombre = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ecommerce-ficha-tecnica-nombre")));

            WebElement textoPrecio = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ecommerce-ficha-tecnica-precio-obj")));
            String precioEquipo = textoPrecio.getText();
            precioEquipo = precioEquipo.replace(",", "");
            precioEquipo = precioEquipo.replace("$", "");
            double pe = Double.parseDouble(precioEquipo);

            WebElement textoCapacidad = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra-caracteristicas-etiqueta.ng-binding")));
            String capacidadEquipo = textoCapacidad.getText();
            String[] datos = capacidadEquipo.split(" ");
            String capacidadString = datos[0];
            int numGigas = Integer.parseInt(capacidadString);

            if (textoMarcaModelo.getText().equals(primerCelular.getMarcaModelo()))
                System.out.println("La marca/modelo es correcto");
            else
            {
                System.out.println("La marca/modelo es incorrecto");
                System.exit(-1);
            }
            if (textoNombre.getText().equals(primerCelular.getNombre()))
                System.out.println("El nombre es correcto");
            else
            {
                System.out.println("El nombre es incorrecto");
                System.exit(-1);
            }

            if (pe == primerCelular.getPrecio())
                System.out.println("El precio es correcto");
            else
            {
                System.out.println("El precio es incorrecto");
                System.exit(-1);
            }

            if (numGigas == primerCelular.getCapacidadGB())
                System.out.println("La capacidad de GB es correcto");
            else
            {
                System.out.println("La capacidad de GB es incorrecto");
                System.exit(-1);
            }
        } catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }
    }


}
