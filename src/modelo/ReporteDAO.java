package modelo;

import java.util.HashMap;
import java.util.Map;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Adin Rubio
 */
public class ReporteDAO extends Conexion {
    public void reporteFactura (int codigo) {
        try {
            this.conectar();
            Map idVentas = new HashMap();
            idVentas.put("idVentas", codigo);
            JasperReport reporte = null;
            String ubicacionReporte = "src\\reportes\\reporteFactura.jasper";
            reporte = (JasperReport)JRLoader.loadObjectFromFile(ubicacionReporte);
            JasperPrint impresion = JasperFillManager.fillReport(reporte, idVentas, this.getMiConexion());
            JasperViewer vista = new JasperViewer(impresion, false);
            vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            vista.setVisible(true);
        } catch (JRException e) {
            System.out.println(e.getMessage());
        } finally {
            
        }
    }
}
