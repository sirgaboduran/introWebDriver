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

    private static void navegarSitio(String url)
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

    private static void verificarLandingPage()
    {
        //verificar que existen estos elementos
//        logoTelcel:  css="[src='/content/dam/htmls/img/icons/logo-telcel.svg']"
        try
        {
        WebElement logoTelcel = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[src='/content/dam/htmls/img/icons/logo-telcel.svg']")));
        WebElement tiendaEnLinea = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-nombreboton='Tienda en linea superior']")));
        WebElement campoBusqueda = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("buscador-menu-input")));
        System.out.println("Si cargo la pagina principal de telcel");

        }
        catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }
    }

    private static void listarTelefonos()
    {
        try
        {
            WebElement linkTiendaEnLinea = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-nombreboton='Tienda en linea superior']")));
            linkTiendaEnLinea.click();
            WebElement linkTelefonosCelulares = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".shortcut-container [data-nombreboton='Telefonos y smartphones']")));
            linkTelefonosCelulares.click();

        }
        catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }

    }

    private static void seleccionarEstado(String nombreEstado)
    {
        try
        {
            WebElement ModalSeleccionEstado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".modal-dialog.modal-lg")));
            WebElement seleccionaEstadoDropdown = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-single span")));
            seleccionaEstadoDropdown.click();
            WebElement campoBusquedaEstado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-search input")));
            campoBusquedaEstado.clear();
            campoBusquedaEstado.sendKeys(nombreEstado);
            WebElement ligaEstado = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".chosen-results li")));
            ligaEstado.click();
            WebElement botonEntrar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("entrarPerfilador")));
            botonEntrar.click();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }
    }

    private static void verificarPaginaResultados()
    {
        try
        {
            WebElement listaResultados = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("seleccion-de-equipos-galeria-mosaicfilters")));
            List<WebElement> celulares = driver.findElements(By.cssSelector(".comp-telcel-mosaico-equipos li"));
            if(celulares.size() > 1)
                System.out.println("La lista se desplego correctamente.");
        }
        catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }

    }

    private static Celular capturarDatosCelular(int i)
    {
        String mm = "";
        String nombreEquipo = "";
        double pe = 0;
        int numGigas = 0;
        try
        {
            WebElement textoMarcaModelo = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".telcel-mosaico-equipos-marca")));
            mm = textoMarcaModelo.getText();

            WebElement textoNombre = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".telcel-mosaico-equipos-nombre-equipo")));
            nombreEquipo = textoNombre.getText();


            WebElement textoPrecio = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".telcel-mosaico-equipos-precio")));
            String precioEquipo = textoPrecio.getText();
            precioEquipo = precioEquipo.replace(",", "");
            precioEquipo = precioEquipo.replace("$", "");
            pe = Double.parseDouble(precioEquipo);


            WebElement textoCapacidad = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".telcel-mosaico-equipos-capacidad-numero")));
            String capacidadEquipo = textoCapacidad.getText();
            String[] datos = capacidadEquipo.split(" ");
            String capacidadString = datos[0];
            numGigas = Integer.parseInt(capacidadString);


        }
        catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }
            return new Celular(mm, nombreEquipo, pe, numGigas);
    }

    private static void seleccionarCelular(int numCelular)
    {
        try
        {
            List<WebElement> celulares = driver.findElements(By.cssSelector(".comp-telcel-mosaico-equipos li"));
            WebElement celular = celulares.get(numCelular - 1);
            celular.click();
        }
        catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }
    }

    private static void validarDatosCelular(Celular primerCelular)
    {
        try
        {
            WebElement textoMarcaModelo = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ecommerce-ficha-tecnica-modelo")));
            if (textoMarcaModelo.getText().equals(primerCelular.getMarcaModelo()))
                System.out.println("La marca/modelo es correcto");
            else
            {
                System.out.println("La marca/modelo es incorrecto");
                return;
            }

            WebElement textoNombre = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ecommerce-ficha-tecnica-nombre")));
            if (textoNombre.getText().equals(primerCelular.getNombre()))
                System.out.println("El nombre es correcto");
            else
            {
                System.out.println("El nombre es incorrecto");
                return;
            }

            WebElement textoPrecio = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ecommerce-ficha-tecnica-precio-obj")));
            String precioEquipo = textoPrecio.getText();
            precioEquipo = precioEquipo.replace(",", "");
            precioEquipo = precioEquipo.replace("$", "");
            double pe = Double.parseDouble(precioEquipo);
            if (pe == primerCelular.getPrecio())
                System.out.println("El precio es correcto");
            else
            {
                System.out.println("El precio es incorrecto");
                return;
            }

            WebElement textoCapacidad = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".ecommerce-ficha-tecnica-opciones-compra-caracteristicas-etiqueta.ng-binding")));
            String capacidadEquipo = textoCapacidad.getText();
            String[] datos = capacidadEquipo.split(" ");
            String capacidadString = datos[0];
            int numGigas = Integer.parseInt(capacidadString);
            if (numGigas == primerCelular.getCapacidadGB())
                System.out.println("La capacidad de GB es correcto");
            else
            {
                System.out.println("La capacidad de GB es incorrecto");
                return;
            }
        }
        catch (NoSuchElementException e)
        {
            System.out.println("No se cargo la Pagina de Resultados");
            driver.quit();
            System.exit(-1);
        }
    }


}
