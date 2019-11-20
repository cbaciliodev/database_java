package pe.edu.urp.database.core.util;

public class DatosConexion {

	 private String username;
	    private String dirver;
	    private String password;
	    private String url;

	    public DatosConexion(String username, String dirver, String password, String url) {
	        this.username = username;
	        this.dirver = dirver;
	        this.password = password;
	        this.url = url;
	    }

	    public DatosConexion() {
	        super();
	        this.username = "sa";
	        this.dirver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	        this.password = "C44910167d";
	        this.url = "jdbc:sqlserver://localhost:1433;databaseName=db_reunion";
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getDirver() {
	        return dirver;
	    }

	    public void setDirver(String dirver) {
	        this.dirver = dirver;
	    }

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getUrl() {
	        return url;
	    }

	    public void setUrl(String url) {
	        this.url = url;
	    }
}
