/**
* @autor: Nikola Milikic
* e-mail: nikola.milikic@gmail.com
* @date: Jul 24, 2008
* @version: 0.1
*/
package presence;

/**
 * @author Nikola Milikic
 *
 */
public class Property {
	private String name;
	private boolean value;
	
	public Property(boolean value, String name){
		this.value = value;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isValue() {
		return value;
	}
	public void setValue(boolean value) {
		this.value = value;
	}
	
	public boolean equals(Object o){
		if(o instanceof Property){
			Property p = (Property) o;
			if(this.name.equals(p.getName()))
					return true;
			return false;
		}
		return false;
	}
}
