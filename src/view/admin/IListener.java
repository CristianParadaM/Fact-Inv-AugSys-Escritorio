package view.admin;

import java.util.EventListener;

/**
 * Listener personalizado
 * @author CRISTIAN DAVID PARADA MARTINEZ
 */
public interface IListener extends EventListener{
	
	/**
	 * Evento que captura alguna accion
	 * @param source fuente que genera el evento
	 */
	public void iEvent(Object source);
	
	/**
	 * Evento que captura algun commando
	 * @param command comando 
	 */
	public void iEvent(String command);
}
