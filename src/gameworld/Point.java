package gameworld;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A representation of a 2D point. Serves to make persistence work because the native java.awt.Point
 * doesn't play nice with JAXB.
 * 
 * @author wanja
 *
 */
@XmlRootElement
public class Point {
  int x;
  int y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Point() {
    this.x = 0;
    this.y = 0;
  }

  public int getX() {
    return x;
  }

  @XmlElement
  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  @XmlElement
  public void setY(int y) {
    this.y = y;
  }

}
