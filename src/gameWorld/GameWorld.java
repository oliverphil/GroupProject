package gameWorld;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
public class GameWorld {

	public boolean equals(Object o) {
		return o instanceof GameWorld;
	}
}
