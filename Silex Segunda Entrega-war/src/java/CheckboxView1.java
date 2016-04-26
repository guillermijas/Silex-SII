import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class CheckboxView1 {
         
    private String[] selectedOperarios;   
    private List<String> operarios;
     
    @PostConstruct
    public void init() {
        operarios = new ArrayList<String>();
        operarios.add("Pepe");
        operarios.add("Juan");
        operarios.add("Manolo");
        
    }
 
    public String[] getSelectedOperarios() {
        return selectedOperarios;
    }
 
    public void setSelectedOperarios(String[] selectedOperarios) {
        this.selectedOperarios = selectedOperarios;
    }
 
    public List<String> getOperarios() {
        return operarios;
    }
}