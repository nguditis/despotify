package se.despotify.domain.media;

import org.hibernate.annotations.CollectionOfElements;
import se.despotify.util.XMLElement;

import javax.persistence.*;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * @author kalle
 * @since 2009-jun-08 04:13:16
 */
@Entity
public class Restriction implements Serializable {

  private static final long serialVersionUID = 1l;
  
  @Id
  @GeneratedValue
  private Long id;
  
  /**
   * AT,BE,CH,CN,CZ,DK,ES,FI,GB,HK,HU,IE,IL,IN,IT,MY,NL,NO,NZ,PL,PT,RU,SE,SG,SK,TR,TW,ZA
   */
  @CollectionOfElements
  @Column(length = 3)
  private Set<String> allowed;

  /**
   * AT,BE,CH,CN,CZ,DK,ES,FI,GB,HK,HU,IE,IL,IN,IT,MY,NL,NO,NZ,PL,PT,RU,SE,SG,SK,TR,TW,ZA
   */
  @CollectionOfElements
  @Column(length = 3)
  private Set<String> forbidden;

  /**
   * free,daypass,premium
   */
  @CollectionOfElements
  @Column(length = 50)
  private Set<String> catalogues;


  public static void fromXMLElement(XMLElement restrictionsNode, Restriction restriction) {

    String tmp;
    if ((tmp = restrictionsNode.getAttribute("allowed")) != null) {
      restriction.setAllowed(new LinkedHashSet<String>(new ArrayList<String>(Arrays.asList(tmp.split(",")))));
    }
    if ((tmp = restrictionsNode.getAttribute("forbidden")) != null) {
      restriction.setForbidden(new LinkedHashSet<String>(new ArrayList<String>(Arrays.asList(tmp.split(",")))));
    }
    if ((tmp = restrictionsNode.getAttribute("catalouges")) != null) {
      restriction.setCatalogues(new LinkedHashSet<String>(new ArrayList<String>(Arrays.asList(tmp.split(",")))));
    }

    // todo enumarate all attributes and warn if there are any unknown
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Set<String> getAllowed() {
    return allowed;
  }

  public void setAllowed(Set<String> allowed) {
    this.allowed = allowed;
  }

  public Set<String> getForbidden() {
    return forbidden;
  }

  public void setForbidden(Set<String> forbidden) {
    this.forbidden = forbidden;
  }

  public Set<String> getCatalogues() {
    return catalogues;
  }

  public void setCatalogues(Set<String> catalogues) {
    this.catalogues = catalogues;
  }
}