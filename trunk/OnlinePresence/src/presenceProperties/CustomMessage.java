/**
 * @author: Nikola Milikic
 * e-mail: nikola.milikic@gmail.com
 * @date: Jul 26, 2008
 * @version: 0.1
 */
package presenceProperties;

/**
 * @author Nikola Milikic
 * 
 */
public class CustomMessage extends PresenceProperty {

	private String message;

	/**
	 * @param message
	 */
	public CustomMessage(String message) {
		super("customMessage");
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public String getContent(){
		return getMessage();
	}
}
