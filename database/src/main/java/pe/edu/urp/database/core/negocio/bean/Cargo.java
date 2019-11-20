package pe.edu.urp.database.core.negocio.bean;

public class Cargo {

	private Integer ICodigo_Cargo;
	private String VConcepto_Cargo;
	
	public Cargo() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Cargo(Integer iCodigo_Cargo, String vConcepto_Cargo) {
		super();
		ICodigo_Cargo = iCodigo_Cargo;
		VConcepto_Cargo = vConcepto_Cargo;
	}



	public Integer getICodigo_Cargo() {
		return ICodigo_Cargo;
	}
	public void setICodigo_Cargo(Integer iCodigo_Cargo) {
		ICodigo_Cargo = iCodigo_Cargo;
	}
	public String getVConcepto_Cargo() {
		return VConcepto_Cargo;
	}
	public void setVConcepto_Cargo(String vConcepto_Cargo) {
		VConcepto_Cargo = vConcepto_Cargo;
	}
	
	
}
