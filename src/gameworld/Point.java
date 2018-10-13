package gameworld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A representation of a 2D point. Serves to make persistence function because the native
 * java.awt.Point class doesn't get marshalled properly with JAXB.
 * 
 * @author Wanja Leuthold - 300424190
 *
 */
@XmlRootElement
public class Point {
  int valueX;
  int valueY;

  public Point(int x, int y) {
    this.valueX = x;
    this.valueY = y;
  }

  public Point() {
    this.valueX = 0;
    this.valueY = 0;
  }

  public int getX() {
    return valueX;
  }

  @XmlElement
  public void setX(int x) {
    this.valueX = x;
  }

  public int getY() {
    return valueY;
  }

  @XmlElement
  public void setY(int y) {
    this.valueY = y;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + valueX;
    result = prime * result + valueY;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Point other = (Point) obj;
    if (valueX != other.valueX) {
      return false;
    }
    if (valueY != other.valueY) {
      return false;
    }
    return true;
  }

}
