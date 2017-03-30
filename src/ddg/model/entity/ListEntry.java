package ddg.model.entity;

import javax.swing.ImageIcon;

/**
 * 
 * This class used for show icon list
 * 
 * @author Zhen Du
 * @date Feb 23, 2017
 */
public class ListEntry
{
   private String value;
   private ImageIcon icon;
  
   /**
    * 
    * Constructors for show ListEntry
    * 
    * @param value item value to show
    * @param icon image/icon to show
    */
   public ListEntry(String value, ImageIcon icon) {
      this.value = value;
      this.icon = icon;
   }

   /**
    * 
    * Constructors for show value ListEntry
    * 
    * @param value
    */
   public ListEntry(String value) {
      this.value = value;
   }

   /**
    * 
    * This method is get the entry text value
    * 
    * @return
    */
   public String getValue() {
      return value;
   }
  
   /**
    * 
    * This method is get the icon value
    * 
    * @return
    */
   public ImageIcon getIcon() {
      return icon;
   }
  
   /**
    * To get the String of this entry
    * 
    * @return String value
    */
   public String toString() {
      return value;
   }
}