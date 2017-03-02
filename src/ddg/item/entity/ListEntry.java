package ddg.item.entity;

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
  
   public ListEntry(String value, ImageIcon icon) {
      this.value = value;
      this.icon = icon;
   }

   public ListEntry(String value) {
      this.value = value;
   }

   public String getValue() {
      return value;
   }
  
   public ImageIcon getIcon() {
      return icon;
   }
  
   public String toString() {
      return value;
   }
}