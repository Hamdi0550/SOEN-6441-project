package ddg.view;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import ddg.Config;
import ddg.map.entity.Map;
import ddg.model.MapEditorModel;
import ddg.utils.ValidationTool;
import org.junit.*;
/**
 * This class is test for MapEditor
 *
 * @author Qin yi
 * @date Mar 1, 2017
 */
public class MapEditorTest{
    private MapEditorModel mapModel;
    private Map map;
    private ValidationTool validationTool;

    @Before
    public void testBefore() throws Exception {
        try
	      {
	         FileInputStream fileIn = new FileInputStream(Config.MAP_FILE);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         mapModel = (MapEditorModel) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	      }catch(ClassNotFoundException c)
	      {
	         c.printStackTrace();
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
        
        if(mapModel==null){
        	mapModel = new MapEditorModel();
        	mapModel.add(new Map());
        }
        
        map = mapModel.getMapByIndex(0);
        validationTool = new ValidationTool(map);

    }

    @Test
    public void testHasValidPath() throws Exception {
        for (int i = 0; i < map.getRow() ; i ++)
            for (int j =0; j < map.getColumn(); j++ ){
                if(map.getLocation()[i][j] == 'i'){
                    validationTool.hasValidPath(i , j);
                    assertTrue(validationTool.isHasvaildpath());
                }

            }

    }
    
    @Test
    public void testHasEntryDoor() throws Exception {
        assertTrue(validationTool.hasEntryDoor());
    }
    
    @Test
    public void testHasExitDoor() throws Exception {
        assertTrue(validationTool.hasExitDoor());
    }

}