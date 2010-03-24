package uk.ac.ox.oucs.oxpoints.gaboto.beans;


import uk.ac.ox.oucs.oxpoints.gaboto.beans.Tel;


/**
 * Gaboto generated bean.
 * @see net.sf.gaboto.generation.GabotoGenerator
 */
public class Fax extends Tel {

  @Override
  public String getType(){
    return "http://www.w3.org/2006/vcard/ns#Fax";
  }





  public String toString() {
    return this.value ;
  }
}