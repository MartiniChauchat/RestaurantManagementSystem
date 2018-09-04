package ca.mcgill.ecse223.resto.application;

import ca.mcgill.ecse223.resto.model.RestoApp;
import ca.mcgill.ecse223.resto.persistence.PersistenceObjectStream;
import ca.mcgill.ecse223.resto.view.RestoPage;
/**
 *  main application class, Do not instantiate
 */
public class RestoApplication {
	private static RestoApp resto;
	private static String filename = "menu.resto";
	/**
	 * If you cannot compile, please read the README file carefully!
	 * @return if resto is null, call load function of the persistence layer, otherwise return existing one
	 * @throws RuntimeException: Exception due to fail to load Resto
	 */
	public static void main(String[] args) {
		try {
			RestoPage window = new RestoPage();
			window.open();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 
	 * @return resto object to be used for all objects
	 * @throws RuntimeException
	 */
	public static RestoApp getResto()throws RuntimeException {
		if(resto == null) {
			PersistenceObjectStream.setFilename(filename);
			resto = (RestoApp)PersistenceObjectStream.deserialize();
			//if resto is null, create a new resto
			if(resto == null) {
				resto = new RestoApp();
			}else {
				resto.reinitialize();
			}
		}
		
		return resto;
		
	}
	/**
	 * 	Use this code only when testing! setResto
	 */
	public static void setResto(RestoApp aResto) {
		resto = aResto;
	}
	
	/**
	 *  change the filename to be saved
	 * @param name: new file name
	 */
	public static void setFilename(String name) {
		filename = name;
	}
	
	/**
	 * save existing resto data
	 * @throws RuntimeException: Exception due to fail to load Resto
	 */
	public static void save()throws RuntimeException {
		PersistenceObjectStream.serialize(resto);
	}

}
